package ng.riby.androidtest.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "distance_table")
data class Distance(
        var img: Bitmap? = null,
        //timestamp is used store date so we can be able to sort our distance by date. we save in a Long value instead of date format
        var timestamp: Long? = 0L,
        var averageSpeedInKMH: Float = 0f,
        var distanceInMeters: Int = 0,
        // timeInMillis is how long it took to cover a distance
        var timeInMillis: Long = 0L
) {
    //primary key - we wont put it in constructor because we want to create run object without us needing a primary key
    //we want room to create id when we create run
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}