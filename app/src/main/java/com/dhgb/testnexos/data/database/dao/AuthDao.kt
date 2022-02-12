package com.dhgb.testnexos.data.database.dao

import androidx.room.*
import com.dhgb.testnexos.data.database.entities.AuthenticationEntity

@Dao
interface AuthDao {

    @Query("SELECT * FROM authentication_table")
    suspend fun getApproveAuth(): List<AuthenticationEntity>

    @Query("SELECT * FROM authentication_table WHERE receiptId = :receiptId")
    suspend fun getAuthByReceiptId(receiptId: String): List<AuthenticationEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertApproveAuth(authEntity: AuthenticationEntity)

    @Delete()
    suspend fun deleteApproveAuth(authEntity: AuthenticationEntity)
}