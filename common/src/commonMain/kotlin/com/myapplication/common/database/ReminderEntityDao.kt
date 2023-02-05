package com.myapplication.common.database

import com.myapplication.common.demoDatabase
import com.myapplication.common.model.Reminder
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class ReminderEntityDao(private val db: demoDatabase) {

    /**
     * Get all reminders in the database
     * This is reactive stream. It will publish data for as long as you are subscribed to it
     */
    fun selectAll(): Flow<List<Reminder>> =
        db.demoDatabaseQueries
            .selectAll(mapper = reminderMapper)
            .asFlow().mapToList(context = Dispatchers.IO)

    /**
     * On insert success it will return `Result.success`,
     * On insert error it will return `Result.failure`
     * Failure likely means the primary key (id: String) already exists or some data corrupted
     * Read the exception provided for more information.
     */
    fun insert(reminder: Reminder): Result<Unit> = try {
        db.demoDatabaseQueries
            .insertReminder(reminder.name, reminder.password, reminder.identification, reminder.data)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}


/**
 * Mapper to convert DemoEntity from DB to Reminder data model
 */
private val reminderMapper: (
    id: Long,
    name: String,
    password: String,
    identification: String,
    data: ByteArray?
) -> Reminder =
    { id: Long, name: String, password: String, identification: String, data: ByteArray? ->
        Reminder(
            id = id,
            name = name,
            password = password,
            identification = identification,
            data = data
        )
    }
