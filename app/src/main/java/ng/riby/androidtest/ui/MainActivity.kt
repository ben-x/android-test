package ng.riby.androidtest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import ng.riby.androidtest.R
import ng.riby.androidtest.db.DistanceDAO
import ng.riby.androidtest.others.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateToTrackingFragmentIfNeeded(intent)

        setSupportActionBar(toolBar)
        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())


        //we have 5 fragments and want to show bottom nav in 3 distance, statistics and settings and not in setup fragment and tracking fragment
        navHostFragment.findNavController()
                .addOnDestinationChangedListener { _, destination, _ ->
                    when(destination.id){
                        R.id.settingsFragment, R.id.distancesFragment, R.id.statisticsFragment ->
                            bottomNavigationView.visibility = View.VISIBLE
                        else -> bottomNavigationView.visibility = View.GONE
                    }
                }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }
    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?){
        if(intent?.action == ACTION_SHOW_TRACKING_FRAGMENT){
            navHostFragment.findNavController().navigate(R.id.action_global_tracking_fragment)
        }
    }
}