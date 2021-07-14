package ng.riby.androidtest.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import java.util.List;

import ng.riby.androidtest.database.database.LocationDataBase;
import ng.riby.androidtest.database.dao.LocationDao;
import ng.riby.androidtest.database.entities.Location;

public class LocationRepository {

    private LocationDao locationDao;
    private LiveData<List<Location>> getLocations;

    public LocationRepository(Application application) {
        LocationDataBase db = LocationDataBase.getDatabase(application);
        locationDao = db.locationDao();
        getLocations = locationDao.getLocations();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Location>> getLocation() {
        return getLocations;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Location location) {
        LocationDataBase.databaseWriteExecutor.execute(() -> {
            locationDao.insert(location);
        });
    }
}
