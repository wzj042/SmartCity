package cn.xqher.smartcity.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import cn.xqher.smartcity.R
import cn.xqher.smartcity.compose.screen.IntroScreen
import cn.xqher.smartcity.ui.theme.SmartCityTheme

// 不用SplashActivity命名，避免自定义的SplashActivity和系统的SplashActivity冲突
class IntroActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        为了解决 postSplashScreenTheme 无法跳转相应 Theme 的问题
//        不想弄沉浸状态栏的无奈之举
        setTheme(
            R.style.Theme_Banner
        )
        setContent {
            SmartCityTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IntroScreen()
                }
            }
        }
    }
}
