import android.app.Application
import java.util.logging.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.)
        }
    }
}