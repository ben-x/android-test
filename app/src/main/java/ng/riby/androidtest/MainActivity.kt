package ng.riby.androidtest

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.distance_item.*
import ng.riby.androidtest.database.Distance

class MainActivity : AppCompatActivity() {

    val PERMISSION_ID = 42
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var distanceViewModel: DistanceViewModel? = null
    lateinit var Geo: Geo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)
        distanceViewModel = ViewModelProviders.of(this).get(DistanceViewModel::class.java)
        val adapter = DistanceAdapter(this.applicationContext!!, distanceViewModel)
        recyclerView.adapter = adapter


        distanceViewModel!!.allDistances.observe(this,
                Observer<List<Distance>> { distances ->
                    // Update recycler view
                    if (distances.isEmpty()) {
                        recyclerView.visibility = View.GONE
                    } else {
                        recyclerView.visibility = View.VISIBLE
                        adapter.setDistances(distances)
                    }
                })



        val start = start
        start.setOnClickListener{
            getLastLocation()
            start.visibility = View.GONE
            stop.visibility = View.VISIBLE
        }
        val stop = stop
        stop.visibility = View.GONE
        stop.setOnClickListener{
            getLastLocation2()
            saveTask()
            start.visibility = View.VISIBLE
            stop.visibility = View.GONE
        }
        val clear = clear_button
        clear.setOnClickListener {
            clear()

        }

    }


    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        findViewById<TextView>(R.id.latTextView).text = location.latitude.toString()
                        findViewById<TextView>(R.id.lonTextView).text = location.longitude.toString()
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }


    @SuppressLint("MissingPermission")
    private fun getLastLocation2() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        findViewById<TextView>(R.id.latTextView2).text = location.latitude.toString()
                        findViewById<TextView>(R.id.lonTextView2).text = location.longitude.toString()
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient!!.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            findViewById<TextView>(R.id.latTextView).text = mLastLocation.latitude.toString()
            findViewById<TextView>(R.id.lonTextView).text = mLastLocation.longitude.toString()
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_ID
        )
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
                getLastLocation2()
            }
        }
    }



    private fun saveTask() {
        val startLat = latTextView.text.toString().trim { it <= ' ' }
        val startLon = lonTextView.text.toString().trim { it <= ' ' }
        val endLat = latTextView2?.text.toString().trim { it <= ' ' }
        val endLon = lonTextView2?.text.toString().trim { it <= ' ' }
        //val distanceCovered = distanceCovered().toString()


 //            val earthRadiusKm: Double = 6372.8

//        val destinationLat = endLat.toDouble()
//        val thisLat = startLat.toDouble()
//        val destinationLon = endLon.toDouble()
//        val thisLon = startLon.toDouble()
//        val dLat = Math.toRadians(destinationLat - thisLat)
//        val dLon = Math.toRadians(destinationLon - thisLon)
//        val originLat = Math.toRadians(thisLat)
//        val desLat = Math.toRadians(destinationLat)
//        val a = Math.pow(Math.sin(dLat / 2), 2.toDouble()) + Math.pow(Math.sin(dLon / 2), 2.toDouble()) * Math.cos(originLat) * Math.cos(desLat)
//        val c = 2 * Math.asin(Math.sqrt(a))





        val dataTask = Intent()
        dataTask.putExtra(Constants.EXTRA_STARTLAT, startLat)
        dataTask.putExtra(Constants.EXTRA_ENDLAT, endLat)
        dataTask.putExtra(Constants.EXTRA_STARTLON, startLon)
        dataTask.putExtra(Constants.EXTRA_ENDLON, endLon)
        //dataTask.putExtra(Constants.EXTRA_DISTANCETRAVELLED, distanceCovered)

        val distance = Distance(startLat, startLon, endLat, endLon)
        distanceViewModel!!.insert(distance)

    }

    private fun clear(){
        distanceViewModel!!.deleteAllDistances()
       // Snackbar.make(contextView, "all location cordinates deleted", Snackbar.LENGTH_LONG).show()
    }


    private fun distanceCovered(): Double {
        var old = getLastLocation()
        var new = getLastLocation2()

                    val earthRadiusKm = 6372.8

        val destinationLat: Double = endLat.toString().toDouble()
        val thisLat = startLat.toString().toDouble()
        val destinationLon = endLon.toString().toDouble()
        val thisLon = startLon.toString().toDouble()
        val dLat = Math.toRadians(destinationLat - thisLat)
        val dLon = Math.toRadians(destinationLon - thisLon)
        val originLat = Math.toRadians(thisLat)
        val desLat = Math.toRadians(destinationLat)
        val a = Math.pow(Math.sin(dLat / 2), 2.toDouble()) + Math.pow(Math.sin(dLon / 2), 2.toDouble()) * Math.cos(originLat) * Math.cos(desLat)
        val c = 2 * Math.asin(Math.sqrt(a))
        return (earthRadiusKm * c)
    }
}