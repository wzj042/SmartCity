package cn.xqher.smartcity.utilities.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PageSection(
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
