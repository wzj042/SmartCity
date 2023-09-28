package cn.xqher.smartcity.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import cn.xqher.smartcity.R
import cn.xqher.smartcity.compose.screen.home.HomeScreen
import cn.xqher.smartcity.compose.screen.news.NewsScreen
import cn.xqher.smartcity.compose.screen.personal.PersonalScreen
import cn.xqher.smartcity.compose.screen.restrict.RestrictScreen
import cn.xqher.smartcity.compose.screen.service.ServiceScreen
import cn.xqher.smartcity.ui.theme.SmartCityTheme
import cn.xqher.smartcity.utilities.compose.SmartCityAppBar


@Composable
fun SmartCityApp() {
    AppPortrait()
}

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    onItemSelected: (Int) -> Unit
) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {

        var selectedIndex by remember { mutableIntStateOf(0) }

        bottomBarData.forEachIndexed { index, pair ->
            val isSelected = (index == selectedIndex)
            val onClick: () -> Unit = {
                selectedIndex = index
                onItemSelected(index)
            }
            val contentColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = pair.drawable),
                        contentDescription = pair.text,
                        tint = contentColor
                    )
                },
                label = {
                    Text(
                        text = pair.text,
                        color = contentColor
                    )
                },
                selected = isSelected,
                onClick = onClick
            )
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppPortrait() {
    SmartCityTheme {

        var appBarText by remember { mutableStateOf("智慧城市") }

        Scaffold(
            topBar = {
                SmartCityAppBar(
                    title = appBarText,
                    sizeIcon = if (appBarText == "智慧城市") Icons.Filled.Menu else null,
                    needBack = false,
                    onSizeTodo = {
                        //                    jump subway roadmap act
                    }
                )
            },
            bottomBar = {
                BottomNavigation(
                    onItemSelected = {
                        appBarText = if (it == 0) "智慧城市" else bottomBarData[it].text

                    }
                )
            }

        ) { padding ->
//            该实现不规范，但是赶时间，先这样吧
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Column(
                    Modifier.verticalScroll(rememberScrollState())
                    ){
                    when (appBarText) {
                        "智慧城市" -> HomeScreen()
                        "全部服务" -> ServiceScreen()
                        "精准扶贫" -> RestrictScreen()
                        "新闻" -> NewsScreen()
                        "个人中心" -> PersonalScreen()
                    }
                }

            }

        }
    }

}


private val bottomBarData = listOf(
    R.drawable.home to "首页",
    R.drawable.serve to "全部服务",
    R.drawable.restrict to "精准扶贫",
    R.drawable.news to "新闻",
    R.drawable.per to "个人中心",
).map { DrawableStringPair(it.first, it.second) }


private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    val text: String
)

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun AppPortraitPreview() {
    SmartCityTheme {
        AppPortrait()
    }
}
