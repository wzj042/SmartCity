package cn.xqher.smartcity.compose.screen

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.xqher.smartcity.R
import cn.xqher.smartcity.activity.MainActivity
import cn.xqher.smartcity.data.PrefStore
import cn.xqher.smartcity.utilities.Constants
import com.drake.net.NetConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private val imageList = listOf(
    R.drawable.introa,
    R.drawable.introb,
    R.drawable.introc,
    R.drawable.introd,
    R.drawable.introe,
)

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun IntroScreen(
    modifier: Modifier = Modifier
) {
//    pref
    val context = LocalContext.current
    val prefStore = PrefStore(context)
    val firstInstall = prefStore.getFirstInstall.collectAsState(initial = "")


//    检测是否已设置网络设置
    if (firstInstall.value.isNotEmpty()) {
        context.startActivity(Intent(context, MainActivity::class.java))
        return
    }

//    banner
    val pagerState = rememberPagerState { imageList.size }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize()
    ) { pageIndex ->
        val imageId = imageList[pageIndex]

        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

    }

    // 指示器
    Box(Modifier.fillMaxSize()) {
        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
        ) {
            imageList.forEachIndexed { index, _ ->
                val color = if (index == pagerState.currentPage) Color.White else Color.Gray
                Box(
                    Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(color)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }

//    最后一页显示网络设置按钮

    val baseUrl = prefStore.getBaseUrl.collectAsState(initial = "")

    val baseUrlValue = remember {
        mutableStateOf(TextFieldValue())
    }

    val openDialog = remember {
        mutableStateOf(false)
    }

//    用对话框展示网络设置
    if (openDialog.value) {
//        更新 TextFieldValue
        baseUrlValue.value = TextFieldValue(text = baseUrl.value)

        AlertDialog(
            onDismissRequest = { },
            title = { Text(text = stringResource(id = R.string.net_config)) },
            text = {
                Column {
//                                    两个编辑框，内容为 baseUrl 和 serverPort
                    Text(
                        text = stringResource(id = R.string.base_url),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    TextField(
                        value = baseUrlValue.value,
                        onValueChange = { baseUrlValue.value = it },
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            },
            confirmButton = {
                Button(
                    onClick = {
//                    写入 pref
                        val input = baseUrlValue.value.text.trim()
                        if (input.isNotEmpty()) {

                            CoroutineScope(Dispatchers.IO).launch {
                                prefStore.setBaseUrl(input)
                            }
//                            UPDATE NET CONFIG HOST
                            NetConfig.host = input
                            Log.i("Intro Screen", "NetConfig update host = $input")
                            Constants.showToast(context, R.string.success)

                        } else {
                            Constants.showToast(context, R.string.input_empty)
                        }
                        openDialog.value = false

                    }
                ) {
                    Text(text = stringResource(id = R.string.save))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            }
        )
    }


    if (pagerState.currentPage == imageList.lastIndex) {

        Box(
            Modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.net_config),
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 25.dp, end = 25.dp)
                    .clickable {
                        openDialog.value = true
                    },
            )
            Button(
                onClick = {
//                          判断两个值是否为空
                    if (baseUrl.value.isNotEmpty()) {
//                        TODO 参数校验(懒得做)
//                        设置firstInstall为非空值
                        CoroutineScope(Dispatchers.IO).launch {
                            prefStore.setFirstInstall("1")
                        }
                        context.startActivity(Intent(context, MainActivity::class.java))
                    } else {
                        Constants.showToast(context, R.string.need_net_config)
                    }

                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 100.dp, start = 50.dp, end = 50.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.enter_home)
                )

            }


        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun PreviewIntroScreen() {
    IntroScreen()
}
