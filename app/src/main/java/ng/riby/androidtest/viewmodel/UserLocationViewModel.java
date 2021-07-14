package ng.riby.androidtest.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ng.riby.androidtest.database.entities.UserLocation;
import ng.riby.androidtest.repository.LocationRepository;

public class LocationViewModel extends AndroidViewModel {

    private LocationRepository locationRepository;

    private final LiveData<List<UserLocation>> getlocations;

    public LocationViewModel(Application application) {
        super(application);
        locationRepository = new LocationRepository(application);
        this.getlocations = locationRepository.getLocation();
    }

    LiveData<List<UserLocation>> getAllLocations() { return getlocations; }

    public void insert(UserLocation userLocation) { locationRepository.insert(userLocation); }
}
