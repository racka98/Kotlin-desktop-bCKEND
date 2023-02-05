package com.myapplication.common.database

import com.myapplication.common.ContextProvider
import com.myapplication.common.demoDatabase

/**
 * Call this to get an instance of the database.
 * This should be preferably called once (creating a singleton).
 * You don't need to call it multiple times as that will open more database connections
 */
expect fun getDatabase(contextProvider: ContextProvider): demoDatabase

internal const val DB_NAME = "demoDatabase.db"
