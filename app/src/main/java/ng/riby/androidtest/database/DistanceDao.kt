package ng.riby.androidtest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Defines methods for using the Distance class with Room.
 */

@Dao
interface DistanceDao {

    @Insert
    fun insert(distance: Distance)


    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param distance new value to write
     */
    @Update
    fun update(distance: Distance)

    /**
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key startTimeMilli to match
     */
    @Query("SELECT * from distance_covered_table WHERE distanceId = :key")
    fun get(key: Long): Distance?

    /**
    * Deletes all values from the table.
    *
    * This does not delete the table, only its contents.
    */
    @Query("DELETE FROM distance_covered_table")
    fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM distance_covered_table ORDER BY distanceId DESC")
    fun getAllDistances(): LiveData<List<Distance>>

    /**
     * Selects and returns the latest distance.
     */
    @Query("SELECT * FROM distance_covered_table ORDER BY distanceId DESC LIMIT 1")
    fun getLastDistance(): Distance?
}