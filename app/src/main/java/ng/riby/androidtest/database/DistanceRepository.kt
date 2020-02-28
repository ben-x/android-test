package ng.riby.androidtest.database


import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DistanceRepository(application: Application, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    private val distanceDao: DistanceDao
    val allDistances: LiveData<List<Distance>>

    init {
        val distanceDatabase = DistanceDatabase.getInstance(application)
        distanceDao = distanceDatabase.distanceDatabaseDao
        allDistances = distanceDao.getAllDistances()
    }

    suspend fun insert(distance: Distance) = withContext(ioDispatcher) {
        distanceDao.insert(distance)
    }

    suspend fun update(distance: Distance) = withContext<Unit>(ioDispatcher) {
        distanceDao.update(distance)
    }

//    suspend fun delete(distanceId: Int) = withContext<Unit>(ioDispatcher) {
//        distanceDao.delete(distanceId)
//    }

    suspend fun deleteAllDistances() = withContext(ioDispatcher) {
        distanceDao.clear()
    }
}
