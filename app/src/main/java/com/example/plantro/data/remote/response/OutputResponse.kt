package com.example.plantro.data.remote.response

import com.google.gson.annotations.SerializedName

data class OutputResponse(

	@field:SerializedName("input")
	val input: Input? = null,

	@field:SerializedName("soil_quality")
	val soilQuality: String? = null,

	@field:SerializedName("predictions")
	val predictions: Predictions? = null
)

data class Rotasi1Item(

	@field:SerializedName("confidence")
	val confidence: Double? = null,  // Ganti Any menjadi Double

	@field:SerializedName("crop")
	val crop: String? = null
)

data class Input(

	@field:SerializedName("pH_Value")
	val pHValue: Float? = null,  // Ganti Any menjadi Float

	@field:SerializedName("Temperature")
	val temperature: Float? = null,  // Ganti Any menjadi Float

	@field:SerializedName("Humidity")
	val humidity: Float? = null,  // Ganti Any menjadi Float

	@field:SerializedName("Rainfall")
	val rainfall: Float? = null,  // Ganti Any menjadi Float

	@field:SerializedName("Phosphorus")
	val phosphorus: Int? = null,

	@field:SerializedName("Nitrogen")
	val nitrogen: Int? = null,

	@field:SerializedName("Potassium")
	val potassium: Int? = null
)

data class Predictions(

	@field:SerializedName("rotasi_1")
	val rotasi1: List<Rotasi1Item?>? = null
)

