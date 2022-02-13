package com.dhgb.testnexos.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseAnnulment (
    @Expose @SerializedName("statusCode") val statusCode: String,
    @Expose @SerializedName("statusDescription") val statusDescription: String,
)