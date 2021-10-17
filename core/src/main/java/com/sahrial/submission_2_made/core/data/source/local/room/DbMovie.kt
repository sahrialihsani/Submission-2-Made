package com.sahrial.submission_2_made.core.data.source.local.room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.sahrial.submission_2_made.core.data.source.local.entity.MovEntity

@Database(entities = [MovEntity::class], version = 1, exportSchema = false)
abstract class DbMovie : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}