package `in`.ashnehete.rentsplitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import `in`.ashnehete.rentsplitter.data.SplitViewModel
import `in`.ashnehete.rentsplitter.ui.composables.RatioList
import `in`.ashnehete.rentsplitter.ui.composables.TopBar
import `in`.ashnehete.rentsplitter.ui.composables.TotalBar
import `in`.ashnehete.rentsplitter.ui.theme.RentSplitterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RentSplitterTheme {
                Scaffold(
                    topBar = { TopBar() },
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplitScreenPreview() {
    RentSplitterTheme {
        SplitScreen()
    }
}


