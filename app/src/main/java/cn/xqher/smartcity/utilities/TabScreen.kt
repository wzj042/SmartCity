package cn.xqher.smartcity.utilities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import cn.xqher.smartcity.R
import cn.xqher.smartcity.ui.theme.SmartCityTheme

data class TabInfo(
    val title: String,
    val icon: Any? = null,
    val content: @Composable (() -> Unit)? = null
)

@Composable
fun TabScreen(
    modifier: Modifier = Modifier,
    tabs: List<TabInfo>,
    onTabSelected: (Int) -> Unit = {}
) {
    var tabIndex by remember { mutableIntStateOf(0) }


    Column(modifier = modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(text = tab.title) },
                    selected = tabIndex == index,
                    onClick = {
                        tabIndex = index
                        onTabSelected(index)
                    },
                    icon = {
                        with(tab.icon) {
                            if (this is Int) {
                                Icon(
                                    painter = painterResource(id = this),
                                    contentDescription = null
                                )
                            }
                            if (this is ImageVector) {
                                Icon(
                                    imageVector = this,
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = Color.Gray
                )
            }
        }
        tabs[tabIndex].content?.invoke()
    }
}

@Preview
@Composable
fun PreviewTabScreen() {
    SmartCityTheme {
        TabScreen(
            tabs = listOf(
                TabInfo(
                    title = "icon",
                    icon = Icons.Rounded.LocationOn
                ),
                TabInfo(
                    title = "res",
                    icon = R.drawable.home
                ),
                TabInfo(title = "Tab3"),
            )
        )
    }
}