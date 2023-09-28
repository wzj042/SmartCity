package cn.xqher.smartcity

import android.app.Application
import android.util.Log
import cn.xqher.smartcity.data.PrefStore
import cn.xqher.smartcity.utilities.GsonConverter
import com.drake.net.NetConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SmartCityApplication: Application() {
    private val prefStore: PrefStore by lazy { PrefStore(applicationContext) }
    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            prefStore.getBaseUrl.collect { baseUrl ->
                NetConfig.host = baseUrl
                Log.i("YourApplication", "setup BASE_URL: $baseUrl")
            }
        }
        NetConfig.converter = GsonConverter()

    }

}