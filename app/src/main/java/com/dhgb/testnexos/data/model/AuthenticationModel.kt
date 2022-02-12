package com.dhgb.testnexos.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthenticationModel (
    @Expose @SerializedName("receiptId") val receiptId: String,
    @Expose @SerializedName("rrn") val rrn: String,
    @Expose @SerializedName("statusCode") val statusCode: String,
    @Expose @SerializedName("statusDescription") val statusDescription: String,
): Parcelable