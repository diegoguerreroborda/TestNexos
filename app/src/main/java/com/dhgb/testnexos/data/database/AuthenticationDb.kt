package com.dhgb.testnexos.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dhgb.testnexos.data.database.dao.AuthDao
import com.dhgb.testnexos.data.database.entities.AuthenticationEntity

@Database(
    entities = [AuthenticationEntity::class],
    version = 2,
)
abstract class AuthenticationDb: RoomDatabase(){

    abstract fun authDao(): AuthDao

    object DatabaseProvider {
        private lateinit var dbInstance: AuthenticationDb

        fun getDataBase(context: Context): AuthenticationDb {
            if (!this::dbInstance.isInitialized){
                dbInstance = Room.databaseBuilder(context.applicationContext,
                    AuthenticationDb::class.java,"authDatabase")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return dbInstance
        }
    }
}