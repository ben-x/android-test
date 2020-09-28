package ng.riby.androidtest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ng.riby.androidtest.db.DistanceDatabase
import ng.riby.androidtest.others.Constants
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDistanceDatabase(
            @ApplicationContext app: Context
    ) = Room.databaseBuilder(
            app,
            DistanceDatabase::class.java,
            Constants.DISTANCE_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDistanceDao(db:DistanceDatabase) = db.getDistanceDao()


}