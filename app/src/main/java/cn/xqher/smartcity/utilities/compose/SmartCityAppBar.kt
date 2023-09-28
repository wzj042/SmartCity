package cn.xqher.smartcity.utilities.compose

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import cn.xqher.smartcity.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartCityAppBar(
    modifier: Modifier = Modifier,
    context: Activity? = null,
    needBack: Boolean = true,
    sizeIcon: ImageVector? = null,
    title: String = stringResource(id = R.string.app_name),
    onSizeTodo: () -> Unit = {},
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = Color.White,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = contentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = containerColor
        ),
        navigationIcon = {
            if (needBack) {
                IconButton(onClick = {
                    context?.finish()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = contentColor
                    )
                }
            }
        },
        actions = {
            if (sizeIcon != null) {
                IconButton(onClick = {
                    onSizeTodo()
                }) {
                    Icon(
                        imageVector = sizeIcon,
                        contentDescription = null,
                        tint = contentColor
                    )
                }
            }
        },
        modifier = modifier
    )
}
