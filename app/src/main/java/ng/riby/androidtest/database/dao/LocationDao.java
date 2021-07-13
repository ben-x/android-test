package ng.riby.androidtest.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ng.riby.androidtest.database.entities.Location;

public interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Location location);

    @Query("SELECT * FROM location_table ORDER BY latitude ASC")
    LiveData<List<Location>> getLocations();
}
