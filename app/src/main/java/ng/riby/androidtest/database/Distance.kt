package ng.riby.androidtest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "distance_covered_table")
data class Distance(
        var startLat: String = "",
        var startLon: String = "",
        var endLat: String = "",
        var endLon: String = "",
        var distanceCovered: String = ""
){
        @PrimaryKey(autoGenerate = true)
        var distanceId: Long = 0L
}