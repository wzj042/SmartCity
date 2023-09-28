package cn.xqher.smartcity.compose.screen.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.xqher.smartcity.R
import cn.xqher.smartcity.data.BannerBean
import cn.xqher.smartcity.data.BannerRow
import cn.xqher.smartcity.data.NewsBean
import cn.xqher.smartcity.data.NewsRow
import cn.xqher.smartcity.data.ServiceBean
import cn.xqher.smartcity.data.ServiceRow
import cn.xqher.smartcity.utilities.Constants
import com.drake.net.Get
import com.drake.net.NetConfig
import com.drake.net.utils.scopeNet
import com.zj.banner.BannerPager
import com.zj.banner.utils.ImageLoader


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    Surface(
        modifier = modifier
    ) {
        val context = LocalContext.current

        Column {

            SearchBar(
                modifier = Modifier.padding(8.dp)
            ) { searchTarget ->

            }

            Banner()

            HomeSection(stringResource(id = R.string.service_entrance)) {
                MenuIconGrid(context = context)
            }

            HomeSection(stringResource(id = R.string.hot_topic)) {
                HotTopicGrid()
            }
            HomeSection(title = "新闻列表") {
                
            }
        }

    }

}

@Composable
fun Banner(
    modifier: Modifier = Modifier,
) {
//            获取Banner List
    var bannerList by remember {
        mutableStateOf(
            listOf(BannerRow(data = R.drawable.introa))
        )
    }

    scopeNet {
        bannerList = Get<BannerBean>("${Constants.BANNER_LIST}?type=2")
            .await()
            .rows
        bannerList.forEach {
//                加上前缀
            it.data = NetConfig.host + it.advImg
        }
    }

    BannerPager(
        modifier = modifier.padding(8.dp),
        items = bannerList,
        onBannerClick = { row ->

        }
    )
}

@Composable
fun MenuIconGrid(
    modifier: Modifier = Modifier,
    context: Context,
) {

//            获取Service List
    var serviceList by remember {
        mutableStateOf(
            listOf(ServiceRow(data = R.drawable.more_serve))
        )
    }

    scopeNet {
        serviceList = Get<ServiceBean>(Constants.SERVICE_LIST)
            .await()
            .rows
//                以sort为键排序
        serviceList = serviceList
            .sortedBy { it.sort }
            .subList(0, 9)
            .plus(
                ServiceRow(
                    data = R.drawable.more_serve,
                    serviceName = context.getString(R.string.more_service)
                )
            )

        serviceList.forEachIndexed { index, serviceRow ->
//                加上前缀
            if (index != serviceList.lastIndex) {
                serviceRow.data = NetConfig.host + serviceRow.imgUrl
            }

        }

    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        modifier = modifier
            .height(180.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentPadding = PaddingValues(all = 8.dp)
    ) {
        items(serviceList) {
            ServeIcon(
                title = it.serviceName,
                data = it.data
            )
        }
    }

}


@Composable
fun HotTopicGrid(
    modifier: Modifier = Modifier,
) {

//            获取Service List
    var newsList by remember {
        mutableStateOf(
            listOf(NewsRow(data = R.drawable.test_news))
        )
    }

    scopeNet {
        newsList = Get<NewsBean>(Constants.NEWS_LIST) {
            param("hot", "Y")
        }
            .await()
            .rows


        newsList.forEach {
            it.data = NetConfig.host + it.cover
        }

    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .height(140.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(newsList) {
            NewsCard(
                title = it.title,
                data = it.data
            )
        }
    }

}

@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.news_title),
    data: Any?
) {
    if (data == null) {
        return
    }
    Column(
        modifier = modifier
            .padding(vertical = 4.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageLoader(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth(),
            data = data
        )
        Text(
            text = title,
            modifier = Modifier
                .padding(4.dp)
                .height(30.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (TextFieldValue) -> Unit
) {
    val searchText = remember { mutableStateOf("") }
    val isSearching = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val solveSearch = {
        onSearch(TextFieldValue(searchText.value))
        isSearching.value = false
        keyboardController?.hide()
    }
    TextField(
        value = searchText.value,
        onValueChange = { searchText.value = it },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text(stringResource(id = R.string.news_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,

            ),
        keyboardActions = KeyboardActions(
            onSearch = {
                solveSearch()
            },
            onDone = {
                solveSearch()
            }
        ),
    )
}

@Composable
fun ServeIcon(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.more_service),
    data: Any? = null
) {
    if (data == null) {
        return
    }
    Column(
        modifier = modifier.padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageLoader(
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp),
            data = data
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun HomeSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}


@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
