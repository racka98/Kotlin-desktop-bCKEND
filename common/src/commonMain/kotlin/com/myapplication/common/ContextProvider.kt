package com.myapplication.common

/**
 * Some Android specific things like Android SQLite driver creation need Android Context.
 * This wrapper class provides a convenient way to provide Android Context to code that
 * will be called in commonMain. (i.e. when creating the database instance)
 */
expect class ContextProvider