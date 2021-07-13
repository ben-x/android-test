package ng.riby.androidtest.database.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ng.riby.androidtest.database.dao.LocationDao;
import ng.riby.androidtest.database.entities.Location;

@Database(entities = {Location.class}, version = 1, exportSchema = false)
public abstract class LocationDataBase extends RoomDatabase {
    public abstract LocationDao locationDao();

    private static volatile LocationDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
   public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static LocationDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocationDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocationDataBase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
