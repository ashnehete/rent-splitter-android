package `in`.ashnehete.rentsplitter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class Ratio(
    var id: Int,
    initialRatio: Int = 1,
    initialSplit: Float = 0f
) {
    var ratio: Int by mutableIntStateOf(initialRatio)
    var split: Float by mutableFloatStateOf(initialSplit)
}


class SplitViewModel : ViewModel() {
    private val _ratioList = getRatioList().toMutableStateList()
    val ratioList: List<Ratio>
        get() = _ratioList

    private var _total by mutableIntStateOf(1000)
    val total: Int
        get() = _total

    fun onTotalChange(newTotal: String) {
        _total = newTotal.toIntOrNull() ?: 0
        computeSplits()
    }

    fun onRatioChange(ratio: Ratio, ratioText: String) {
        _ratioList.find { it.id == ratio.id }?.let { task ->
            ratio.ratio = ratioText.toIntOrNull() ?: 0
        }
        computeSplits()
    }

    fun add() {
        val new_id = if (!_ratioList.isEmpty()) _ratioList.last().id + 1 else 1
        _ratioList.add(Ratio(new_id))
        computeSplits()
    }

    fun remove(ratio: Ratio) {
        _ratioList.remove(ratio)
        computeSplits()
    }

    private fun computeSplits() {
        val ratioSum = ratioList.fold(0) { acc, next -> acc + next.ratio }.toFloat()
        ratioList.forEach { item -> item.split = total * (item.ratio / ratioSum) }
    }
}

fun getRatioList() = List(0) { i -> Ratio(i + 1) }