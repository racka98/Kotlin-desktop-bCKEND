package com.myapplication.common.database

import com.myapplication.common.ContextProvider
import com.myapplication.common.demoDatabase
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import java.io.File

/**
 * Call this to get an instance of the database.
 * This should be preferably called once (creating a singleton).
 * You don't need to call it multiple times as that will open more database connections
 */
actual fun getDatabase(contextProvider: ContextProvider): demoDatabase {
    val dbPath = File(System.getProperty("java.io.tmpdir"), DB_NAME)
    val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${dbPath.absolutePath}")
        .also { demoDatabase.Schema.create(it) }
    return demoDatabase(driver = driver)
}
