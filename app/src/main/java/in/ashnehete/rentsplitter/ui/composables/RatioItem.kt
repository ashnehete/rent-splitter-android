package `in`.ashnehete.rentsplitter.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.ashnehete.rentsplitter.data.Ratio
import `in`.ashnehete.rentsplitter.ui.theme.RentSplitterTheme

@Composable
fun RatioItem(
    ratio: Ratio,
    onRatioChange: (String) -> Unit,
    onCloseItem: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TextField(
            value = ratio.ratio.toString(),
            onValueChange = onRatioChange,
            label = { Text("Ratio ${ratio.id}") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = ratio.split.toString(),
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        )
        IconButton(
            modifier = Modifier.padding(start = 8.dp),
            onClick = onCloseItem
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Remove"
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun RatioItemPreview() {
    RentSplitterTheme {
        RatioItem(
            ratio = Ratio(0),
            onRatioChange = {},
            onCloseItem = {}
        )
    }
}