package ng.riby.androidtest.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_distances.*
import ng.riby.androidtest.R
import ng.riby.androidtest.ui.viewmodels.MainViewModel

@AndroidEntryPoint
class DistancesFragment: Fragment(R.layout.fragment_distances) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_distancesFragment_to_trackingFragment)
        }
    }
}