package ng.riby.androidtest.database.entities;

import androidx.annotation.NonNull;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "location_table")

public class Location {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @NonNull
    private Double latitude;
    @NonNull
    private Double longitude;

    public Location(int id, @NonNull Double latitude, @NonNull Double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(@NonNull Double latitude) {
        this.latitude = latitude;
    }

    @NonNull
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(@NonNull Double longitude) {
        this.longitude = longitude;
    }
}
