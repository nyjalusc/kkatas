package arrays.lc.`243`

import kotlin.math.abs
import kotlin.math.min

/**
 * Init with a mpa of <String, List<Indices>>. Here the list will be managed in a sorted order when preprocessed.
 * Use two pointers i & j to compute min-diff between list indices. Every iteration move the pointer with smaller index.
 * Time & Space: O(N)
 */
class ShortestWordDistanceII_244(wordsDict: Array<String>) {
    private val map = mutableMapOf<String, MutableList<Int>>()
    init {
        wordsDict.forEachIndexed { index, word ->
            val list = map.getOrPut(word) { mutableListOf() }
            list.add(index)
        }
    }

    fun shortest(word1: String, word2: String): Int {
        var minDistance = Int.MIN_VALUE
        val word1Indices = map[word1]
        val word2Indices = map[word2]

        if(word1Indices == null || word2Indices == null) return minDistance

        var i = 0
        var j = 0
        while (i < word1Indices.size && j < word2Indices.size) {
            minDistance = min(minDistance, abs(word1Indices[i] - word2Indices[j]))
            if(word1Indices[i] < word2Indices[j]) i++
            else j++
        }
        return minDistance
    }
}