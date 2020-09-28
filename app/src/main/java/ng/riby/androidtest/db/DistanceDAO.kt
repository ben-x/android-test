package ng.riby.androidtest.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DistanceDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDistance(distance: Distance)

    @Delete
    suspend fun deleteDistance(distance: Distance)

    @Query("SELECT * FROM distance_table ORDER BY timestamp DESC")
    fun getAllDistancesSortedByDate():LiveData<List<Distance>>


    @Query("SELECT * FROM distance_table ORDER BY timeInMillis DESC")
    fun getAllRunsSortedByTimeInMillis():LiveData<List<Distance>>


    @Query("SELECT * FROM distance_table ORDER BY averageSpeedInKMH DESC")
    fun getAllRunsSortedByAverageSpeed():LiveData<List<Distance>>

    @Query("SELECT * FROM distance_table ORDER BY distanceInMeters DESC")
    fun getAllRunsSortedByDistance():LiveData<List<Distance>>

    //to get total time for all distances
    @Query("SELECT SUM(timeInMillis) FROM distance_table")
    fun getTotalTimeInMillis(): LiveData<Long>


    //to get total distance covered
    @Query("SELECT SUM(distanceInMeters) FROM distance_table")
    fun getTotalDistance(): LiveData<Int>

    //to get average speed for all distances covered
    @Query("SELECT AVG(averageSpeedInKMH) FROM distance_table")
    fun getTotalAverageSpeed(): LiveData<Float>


}