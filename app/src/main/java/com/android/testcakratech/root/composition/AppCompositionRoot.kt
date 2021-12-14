package com.android.testcakratech.root.composition

import android.content.Context
import com.android.testcakratech.room.FormDatabase

class AppCompositionRoot(
    private val context: Context
) {

    val dao by lazy {
        FormDatabase.getInstance(context)
            .formDao()
    }

}