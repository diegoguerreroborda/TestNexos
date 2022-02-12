package com.dhgb.testnexos.data.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "authentication_table")
data class AuthenticationEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "receiptId") val receiptId: String,
    @ColumnInfo(name ="rrn") val rrn: String,
    @ColumnInfo(name ="statusCode") val statusCode: String,
    @ColumnInfo(name ="statusDescription") val statusDescription: String,
    @ColumnInfo(name ="id") val id: String,
    @ColumnInfo(name ="commerceCode") val commerceCode: String,
    @ColumnInfo(name ="terminalCode") val terminalCode: String,
    @ColumnInfo(name ="amount") val amount: String,
    @ColumnInfo(name ="card") val card: String,
):Parcelable