package com.android.testcakratech.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Form::class], version = 1)
abstract class FormDatabase : RoomDatabase() {

    abstract fun formDao(): FormDao

    companion object {

        private var ISTANCE: FormDatabase? = null

        fun getInstance(context: Context): FormDatabase {
            synchronized(this) {
                var istance = ISTANCE
                if (istance == null) {
                    istance = Room.databaseBuilder(
                        context.applicationContext,
                        FormDatabase::class.java,
                        "form_table"
                    ).build()
                }

                return istance
            }
        }

    }

}