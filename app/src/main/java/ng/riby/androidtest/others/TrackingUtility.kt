package ng.riby.androidtest.others

import android.content.Context
import android.os.Build
import pub.devrel.easypermissions.EasyPermissions
import java.util.jar.Manifest


//object because it will only have functions we dnt need an instance of this class

object TrackingUtility {


    //ANDROID Q NOT PRESENT
    fun hasLocationPermissions(context: Context) =
            if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){
                EasyPermissions.hasPermissions(
                        context,
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
            }else{
                EasyPermissions.hasPermissions(
                        context,
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION)
            }
}