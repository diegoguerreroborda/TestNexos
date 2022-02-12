package com.dhgb.testnexos.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionModel (
    @Expose @SerializedName("id") val id: String,
    @Expose @SerializedName("commerceCode") val commerceCode: String,
    @Expose @SerializedName("terminalCode") val terminalCode: String,
    @Expose @SerializedName("amount") val amount: String,
    @Expose @SerializedName("card") val card: String,
): Parcelable