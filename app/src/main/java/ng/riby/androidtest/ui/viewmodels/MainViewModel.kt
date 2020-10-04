package ng.riby.androidtest.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import ng.riby.androidtest.repositories.MainRepository

class MainViewModel @ViewModelInject constructor(
        val mainRepository: MainRepository
): ViewModel() {
}
//Job of MainViewModel is to collect data from repository and provide it for those fragments
// that will need mainViewModel

//we need an instance of our main repo inside our main viewModel
