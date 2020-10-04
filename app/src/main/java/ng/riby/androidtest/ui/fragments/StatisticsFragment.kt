package ng.riby.androidtest.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ng.riby.androidtest.R
import ng.riby.androidtest.ui.viewmodels.StatisticsViewModel

@AndroidEntryPoint
class StatisticsFragment:Fragment(R.layout.fragment_statistics) {

    private val viewModel: StatisticsViewModel by viewModels()

}