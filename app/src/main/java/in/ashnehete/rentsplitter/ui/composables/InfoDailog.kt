package `in`.ashnehete.rentsplitter.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import `in`.ashnehete.rentsplitter.ui.theme.RentSplitterTheme

@Composable
fun InfoDialog(onDismissRequest: () -> Unit) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
            append("Created with ❤️ by ")
        }

        pushStringAnnotation(tag = "ashnehete", annotation = "https://ashnehete.in/")
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append("Aashish Nehete")
        }
        pop()
    }

    val uriHandler = LocalUriHandler.current


    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
        ) {
            ClickableText(
                text = annotatedString,
                onClick = { offset ->
                    annotatedString.getStringAnnotations(
                        tag = "ashnehete",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        uriHandler.openUri(it.item)
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun InfoDialogPreview() {
    RentSplitterTheme {
        InfoDialog({})
    }
}