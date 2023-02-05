package com.myapplication.common.model

/**
 * You data model that will be used throughout the app.
 */
data class Reminder(
    val id: Long,
    val name: String,
    val password: String,
    val identification: String,
    val data: ByteArray?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Reminder

        if (id != other.id) return false
        if (name != other.name) return false
        if (password != other.password) return false
        if (identification != other.identification) return false
        if (data != null) {
            if (other.data == null) return false
            if (!data.contentEquals(other.data)) return false
        } else if (other.data != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + identification.hashCode()
        result = 31 * result + (data?.contentHashCode() ?: 0)
        return result
    }
}
