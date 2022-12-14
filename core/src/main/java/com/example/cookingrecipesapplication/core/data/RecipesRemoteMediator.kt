//package com.example.cookingrecipesapplication.core.data
//
//import android.util.Log
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import androidx.room.withTransaction
//import com.example.cookingrecipesapplication.core.data.source.local.LocalDataSource
//import com.example.cookingrecipesapplication.core.data.source.local.entity.RecipesEntity
//import com.example.cookingrecipesapplication.core.data.source.local.entity.RemoteKeys
//import com.example.cookingrecipesapplication.core.data.source.remote.RemoteDataSource
//import com.example.cookingrecipesapplication.core.data.source.remote.network.ApiResponse
//import com.example.cookingrecipesapplication.core.data.source.remote.network.ApiService
//import com.example.cookingrecipesapplication.core.utils.DataMapper
//import kotlinx.coroutines.flow.first
//
//
//@OptIn(ExperimentalPagingApi::class)
//class RecipesRemoteMediator(
//    private val remoteDataSource: RemoteDataSource,
//    private val localDataSource: LocalDataSource,
//    private val type: String
//): RemoteMediator<Int, RecipesEntity>() {
//
//    companion object{
//        const val INITIAL_PAGE_INDEX = 1
//    }
//
//    override suspend fun initialize(): InitializeAction {
//        return InitializeAction.LAUNCH_INITIAL_REFRESH
//    }
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, RecipesEntity>
//    ): MediatorResult {
//        val page = when(loadType){
//            LoadType.REFRESH -> {
//                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
//                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
//            }
//            LoadType.PREPEND -> {
//                val remoteKeys = getRemoteKeyForFirstItem(state)
//                val prevKey = remoteKeys?.prevKey
//                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
//                prevKey
//            }
//            LoadType.APPEND -> {
//                val remoteKeys = getRemoteKeyForLastItem(state)
//                Log.e("remote keys", remoteKeys.toString())
//                val nextKey = remoteKeys?.nextKey?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
//                nextKey
//            }
//        }
//
//        Log.d("Load Type", loadType.name)
//
//        try {
//            val responseData = remoteDataSource.searchRecipes(type = type,
//                offset = page * state.config.pageSize, number = state.config.pageSize)
//
//            val listRecipesResult = responseData.results
//
//            val endOfPaginationReached = listRecipesResult.isEmpty()
//
//            localDataSource.getDatabase().withTransaction {
//                if(loadType == LoadType.REFRESH) {
//                    localDataSource.deleteRemoteKeys()
//                    localDataSource.deleteAllRecipes(type)
//                }
//
//                val prevKey = if(page == INITIAL_PAGE_INDEX) null else page - 1
//                val nextKey = if(endOfPaginationReached) null else page + 1
//                val keys = listRecipesResult.map {
//                    RemoteKeys(it.id.toString(), prevKey, nextKey)
//                }
//
//                val listRecipesData = DataMapper.mapResponsesToEntities(listRecipesResult, type)
//                localDataSource.insertRecipes(listRecipesData)
//                localDataSource.insertAllRemoteKeys(keys)
//            }
//            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
//
//        }
//        catch (e: Exception){
//            return MediatorResult.Error(e)
//        }
//    }
//
//    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, RecipesEntity>): RemoteKeys?{
//        return state.pages.lastOrNull {
//            it.data.isNotEmpty()
//        }?.data?.lastOrNull()?.let { data ->
//            localDataSource.getRemoteKeysById(data.id)
//        }
//    }
//
//    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, RecipesEntity>): RemoteKeys?{
//        return state.pages.firstOrNull {
//            it.data.isNotEmpty()
//        }?.data?.firstOrNull()?.let { data ->
//            localDataSource.getRemoteKeysById(data.id)
//        }
//    }
//
//    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, RecipesEntity>): RemoteKeys?{
//        return state.anchorPosition?.let { position ->
//            state.closestItemToPosition(position)?.id?.let { id ->
//                localDataSource.getRemoteKeysById(id)
//            }
//        }
//    }
//}