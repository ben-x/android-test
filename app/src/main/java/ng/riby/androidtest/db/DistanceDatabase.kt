package ng.riby.androidtest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Distance::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DistanceDatabase: RoomDatabase() {
    abstract fun getDistanceDao():DistanceDAO
}