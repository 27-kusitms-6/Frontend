//import android.app.Application
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.kustims.a6six.data.network.APIS
//import com.kustims.a6six.data.util.PreferenceManager
//
//class HomeViewModelFactory(
//    private val application: Application,
//    private val retService: APIS,
//    private val preferenceManager: PreferenceManager
//) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//            return HomeViewModel(application, retService, preferenceManager) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
