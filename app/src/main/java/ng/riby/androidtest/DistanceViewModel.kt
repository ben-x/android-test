package ng.riby.androidtest


import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ng.riby.androidtest.database.Distance
import ng.riby.androidtest.database.DistanceRepository


class DistanceViewModel(@NonNull application: Application) : AndroidViewModel(application) {

    private  var viewModelJob = Job()



    private val repository: DistanceRepository = DistanceRepository(application)
    val allDistances: LiveData<List<Distance>>



    init {
        allDistances = repository.allDistances
    }

    fun insert(distance: Distance) {
        viewModelScope.launch {
            repository.insert(distance)
        }
    }

    fun update(distance: Distance) {
        viewModelScope.launch {
            repository.update(distance)
        }
    }


    fun deleteAllDistances() {
        viewModelScope.launch {
            repository.deleteAllDistances()
        }
    }}
