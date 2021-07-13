package ng.riby.androidtest.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ng.riby.androidtest.database.entities.Location;
import ng.riby.androidtest.repository.LocationRepository;

public class LocationViewModel extends AndroidViewModel {

    private LocationRepository locationRepository;

    private final LiveData<List<Location>> getlocations;

    public LocationViewModel(Application application, LiveData<List<Location>> getlocations) {
        super(application);
        locationRepository = new LocationRepository(application);
        this.getlocations = locationRepository.getLocation();
    }

    LiveData<List<Location>> getAllLocations() { return getlocations; }

    public void insert(Location location) { locationRepository.insert(location); }
}
