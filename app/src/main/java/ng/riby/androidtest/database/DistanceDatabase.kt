package ng.riby.androidtest.database

import android.content.Context
import android.location.Location
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * A database that stores Distance information.
 * And a global method to get access to the database.
 *
 * This pattern is pretty much the same for any database,
 * so you can reuse it.
 */
@Database(entities = [Distance::class], version = 6, exportSchema = false)
abstract class DistanceDatabase : RoomDatabase(){

    /**
     * Connects the database to the DAO.
     */
    abstract val distanceDatabaseDao: DistanceDao

    /**
     * Define a companion object, this allows us to add functions on the DistanceDatabase class.
     *
     * For example, clients can call `DistaceDatabase.getInstance(context)` to instantiate
     * a new DistanceDatabase.
     */
    companion object {
        /**
         * INSTANCE will keep a reference to any database returned via getInstance.
         *
         * This will help us avoid repeatedly initializing the database, which is expensive.
         *
         *  The value of a volatile variable will never be cached, and all writes and
         *  reads will be done to and from the main memory. It means that changes made by one
         *  thread to shared data are visible to other threads.
         */

        @Volatile
        private var INSTANCE: DistanceDatabase? = null

        /**
         * Helper function to get the database.
         *
         * If a database has already been retrieved, the previous database will be returned.
         * Otherwise, create a new database.
         *
         * This function is threadsafe, and callers should cache the result for multiple database
         * calls to avoid overhead.
         *
         * This is an example of a simple Singleton pattern that takes another Singleton as an
         * argument in Kotlin.
         *
         * To learn more about Singleton read the wikipedia article:
         * https://en.wikipedia.org/wiki/Singleton_pattern
         *
         * @param context The application context Singleton, used to get access to the filesystem.
         */

        fun getInstance(context:Context): DistanceDatabase {
            synchronized(this){

                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            DistanceDatabase::class.java,
                            "distance_history_database"
                    )

                            .fallbackToDestructiveMigration()
                            .build()

                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}