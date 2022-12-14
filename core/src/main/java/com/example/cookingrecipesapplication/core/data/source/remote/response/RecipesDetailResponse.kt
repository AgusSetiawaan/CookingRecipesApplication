package com.example.cookingrecipesapplication.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RecipesDetailResponse(

	@field:SerializedName("instructions")
	val instructions: String,

	@field:SerializedName("sustainable")
	val sustainable: Boolean,

	@field:SerializedName("analyzedInstructions")
	val analyzedInstructions: List<Any>,

	@field:SerializedName("glutenFree")
	val glutenFree: Boolean,

	@field:SerializedName("veryPopular")
	val veryPopular: Boolean,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("healthScore")
	val healthScore: Int,

	@field:SerializedName("diets")
	val diets: List<Any>,

	@field:SerializedName("aggregateLikes")
	val aggregateLikes: Int,

	@field:SerializedName("readyInMinutes")
	val readyInMinutes: Int,

	@field:SerializedName("sourceUrl")
	val sourceUrl: String,

	@field:SerializedName("creditsText")
	val creditsText: String,

	@field:SerializedName("dairyFree")
	val dairyFree: Boolean,

	@field:SerializedName("servings")
	val servings: Int,

	@field:SerializedName("vegetarian")
	val vegetarian: Boolean,

	@field:SerializedName("whole30")
	val whole30: Boolean,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("imageType")
	val imageType: String,

	@field:SerializedName("winePairing")
	val winePairing: WinePairing,

	@field:SerializedName("summary")
	val summary: String,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("veryHealthy")
	val veryHealthy: Boolean,

	@field:SerializedName("vegan")
	val vegan: Boolean,

	@field:SerializedName("cheap")
	val cheap: Boolean,

	@field:SerializedName("dishTypes")
	val dishTypes: List<String>,

	@field:SerializedName("extendedIngredients")
	val extendedIngredients: List<ExtendedIngredientsItem>,

	@field:SerializedName("gaps")
	val gaps: String,

	@field:SerializedName("cuisines")
	val cuisines: List<Any>,

	@field:SerializedName("lowFodmap")
	val lowFodmap: Boolean,

	@field:SerializedName("license")
	val license: String,

	@field:SerializedName("weightWatcherSmartPoints")
	val weightWatcherSmartPoints: Int,

	@field:SerializedName("occasions")
	val occasions: List<Any>,

	@field:SerializedName("spoonacularScore")
	val spoonacularScore: Int,

	@field:SerializedName("pricePerServing")
	val pricePerServing: Double,

	@field:SerializedName("sourceName")
	val sourceName: String,

	@field:SerializedName("spoonacularSourceUrl")
	val spoonacularSourceUrl: String,

	@field:SerializedName("ketogenic")
	val ketogenic: Boolean
)

data class ProductMatchesItem(

	@field:SerializedName("score")
	val score: Double,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("averageRating")
	val averageRating: Double,

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("ratingCount")
	val ratingCount: Int
)

data class Measures(

	@field:SerializedName("metric")
	val metric: Metric,

	@field:SerializedName("us")
	val us: Us
)

data class WinePairing(

	@field:SerializedName("productMatches")
	val productMatches: List<ProductMatchesItem>,

	@field:SerializedName("pairingText")
	val pairingText: String,

	@field:SerializedName("pairedWines")
	val pairedWines: List<String>
)

data class Metric(

	@field:SerializedName("amount")
	val amount: Double,

	@field:SerializedName("unitShort")
	val unitShort: String,

	@field:SerializedName("unitLong")
	val unitLong: String
)

data class Us(

	@field:SerializedName("amount")
	val amount: Double,

	@field:SerializedName("unitShort")
	val unitShort: String,

	@field:SerializedName("unitLong")
	val unitLong: String
)

data class ExtendedIngredientsItem(

	@field:SerializedName("originalName")
	val originalName: String,

	@field:SerializedName("image")
	val image: String?,

	@field:SerializedName("amount")
	val amount: Double,

	@field:SerializedName("measures")
	val measures: Measures,

	@field:SerializedName("unit")
	val unit: String,

	@field:SerializedName("original")
	val original: String,

	@field:SerializedName("consitency")
	val consitency: String?,

	@field:SerializedName("meta")
	val meta: List<String>,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("aisle")
	val aisle: String
)
