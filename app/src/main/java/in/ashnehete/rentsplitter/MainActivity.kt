package `in`.ashnehete.rentsplitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import `in`.ashnehete.rentsplitter.ui.theme.RentSplitterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RentSplitterTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    SplitScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SplitScreen(
    modifier: Modifier = Modifier,
    splitViewModel: SplitViewModel = viewModel()
) {
    Column(modifier = modifier) {
        TotalBar(
            total = splitViewModel.total,
            onTotalChange = { splitViewModel.onTotalChange(it) },
            onAdd = { splitViewModel.add() }
        )
        RatioList(
            list = splitViewModel.ratioList,
            onRatioChange = { ratio, ratioText ->
                splitViewModel.onRatioChange(ratio, ratioText)
            },
            onCloseItem = { ratio ->
                splitViewModel.remove(ratio)
            }
        )
    }
}

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

@Composable
fun TotalBar(
    total: Int,
    onTotalChange: (String) -> Unit,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TextField(
            value = total.toString(),
            onValueChange = onTotalChange,
            label = { Text("Total") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f)
        )
        FilledIconButton(
            modifier = Modifier.padding(start = 8.dp),
            onClick = onAdd
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add"
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplitScreenPreview() {
    RentSplitterTheme {
        SplitScreen()
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun TotalBarPreview() {
    RentSplitterTheme {
        TotalBar(0,
            onTotalChange = {},
            onAdd = {}
        )
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

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun RatioListPreview() {
    RentSplitterTheme {
        RatioList(
            list = getRatioList(),
            onRatioChange = { _, _ -> },
            onCloseItem = {}
        )
    }
}