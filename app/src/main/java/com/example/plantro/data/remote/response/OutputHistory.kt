package com.example.plantro.data.remote.response

import com.google.gson.annotations.SerializedName

data class OutputHistory(

	@field:SerializedName("total_predictions")
	val totalPredictions: Int? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("history")
	val history: List<HistoryItem>
)

data class PredictionsHistory(

	@field:SerializedName("rotasi_1")
	val rotasi1: List<RotasiItem?>? = null
)

data class HistoryItem(

	@field:SerializedName("input")
	val input: Input? = null,

	@field:SerializedName("soil_quality")
	val soilQuality: String? = null,

	@field:SerializedName("doc_id")
	val docId: String? = null,

	@field:SerializedName("predictions")
	val predictions: Predictions? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class RotasiItem(

	@field:SerializedName("confidence")
	val confidence: Any? = null,

	@field:SerializedName("crop")
	val crop: String? = null
)

data class InputHistory(

	@field:SerializedName("pH_Value")
	val pHValue: Any? = null,

	@field:SerializedName("Temperature")
	val temperature: Any? = null,

	@field:SerializedName("Humidity")
	val humidity: Any? = null,

	@field:SerializedName("Rainfall")
	val rainfall: Any? = null,

	@field:SerializedName("Phosphorus")
	val phosphorus: Int? = null,

	@field:SerializedName("Nitrogen")
	val nitrogen: Int? = null,

	@field:SerializedName("Potassium")
	val potassium: Int? = null
)
