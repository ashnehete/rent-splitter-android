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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import `in`.ashnehete.rentsplitter.data.SplitViewModel
import `in`.ashnehete.rentsplitter.ui.composables.InfoDialog
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
                SplitScreen()
            }
        }
    }
}

@Composable
fun SplitScreen(
    modifier: Modifier = Modifier,
    splitViewModel: SplitViewModel = viewModel()
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    if (showDialog) {
        InfoDialog(onDismissRequest = { showDialog = false })
    }

    Scaffold(
        topBar = {
            TopBar(onClickInfo = { showDialog = showDialog.not() })
        },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplitScreenPreview() {
    RentSplitterTheme {
        SplitScreen()
    }
}


