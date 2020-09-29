package ng.riby.androidtest.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ng.riby.androidtest.R
import ng.riby.androidtest.ui.viewmodels.MainViewModel

@AndroidEntryPoint
class DistancesFragment: Fragment(R.layout.fragment_distances) {

    private val viewModel: MainViewModel by viewModels()
}