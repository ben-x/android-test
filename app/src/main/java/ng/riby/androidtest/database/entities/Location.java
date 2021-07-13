package ng.riby.androidtest.database.entities;

import android.support.annotation.NonNull;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "location_table")
@Getter
@Setter
@AllArgsConstructor
public class Location {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private Double latitude;
    @NonNull
    private Double longitude;
}
