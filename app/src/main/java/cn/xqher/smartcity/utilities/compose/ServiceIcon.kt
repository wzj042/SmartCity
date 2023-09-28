package cn.xqher.smartcity.utilities.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cn.xqher.smartcity.R
import com.zj.banner.utils.ImageLoader


@Composable
fun ServiceIcon(
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