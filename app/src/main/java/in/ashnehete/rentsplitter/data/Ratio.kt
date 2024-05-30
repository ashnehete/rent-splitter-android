package `in`.ashnehete.rentsplitter.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

class Ratio(
    var id: Int,
    initialRatio: Int = 1,
    initialSplit: Float = 0f
) {
    var ratio: Int by mutableIntStateOf(initialRatio)
    var split: Float by mutableFloatStateOf(initialSplit)
}


fun getInitialRatioList() = List(0) { i -> Ratio(i + 1) }
