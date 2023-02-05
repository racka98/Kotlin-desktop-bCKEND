package com.myapplication.common.database

import com.myapplication.common.ContextProvider
import com.myapplication.common.demoDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver

/**
 * Call this to get an instance of the database.
 * This should be preferably called once (creating a singleton).
 * You don't need to call it multiple times as that will open more database connections
 */
actual fun getDatabase(contextProvider: ContextProvider): demoDatabase {
    val driver = AndroidSqliteDriver(
        schema = demoDatabase.Schema,
        context = contextProvider.context,
        name = DB_NAME
    )
    return demoDatabase(driver = driver)
}