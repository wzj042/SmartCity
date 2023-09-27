package cn.xqher.smartcity.screen

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.xqher.smartcity.R


private val imageList = listOf(
    R.drawable.introa,
    R.drawable.introb,
    R.drawable.introc,
    R.drawable.introd,
    R.drawable.introe,
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { imageList.size }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize()
    ) { pageIndex ->
        val imageId = imageList[pageIndex]

        Image(
            painter = painterResource(id = imageId),
            contentDescription = "intro",
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

    if (pagerState.currentPage == imageList.lastIndex) {

        Box(
            Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "网络设置",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 25.dp, end = 25.dp)
                    .clickable {

                    },
            )
            Button(
                onClick = {
//                        check if net config setup yet

//                    context.startActivity(Intent(context, MainActivity::class.java))
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 100.dp, start = 50.dp, end = 50.dp)
            ) {
                Text(
                    text = "进入主页"
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