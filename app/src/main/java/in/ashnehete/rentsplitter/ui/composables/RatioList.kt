package `in`.ashnehete.rentsplitter.ui.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import `in`.ashnehete.rentsplitter.data.Ratio
import `in`.ashnehete.rentsplitter.data.getInitialRatioList
import `in`.ashnehete.rentsplitter.ui.theme.RentSplitterTheme

@Composable
fun RatioList(
    list: List<Ratio>,
    onRatioChange: (Ratio, String) -> Unit,
    onCloseItem: (Ratio) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = list,
            key = { task -> task.id }
        ) { ratio ->
            RatioItem(
                ratio,
                onRatioChange = { ratioText -> onRatioChange(ratio, ratioText) },
                onCloseItem = { onCloseItem(ratio) }
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
private fun RatioListPreview() {
    RentSplitterTheme {
        RatioList(
            list = getInitialRatioList(),
            onRatioChange = { _, _ -> },
            onCloseItem = {}
        )
    }
}