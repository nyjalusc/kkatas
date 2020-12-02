package arrays.lc.`243`

import kotlin.math.abs
import kotlin.math.min

/**
 * One Pass Solution: Use two pointers to locate the two words in Array and compute the difference.
 * Repeat this every time one of the pointer moves.
 * Time: O(N)
 * Space: O(1)
 */
class ShortestWordDistance {
    fun shortestDistance(words: Array<String>, word1: String, word2: String): Int {
        var i: Int? = null
        var j: Int? = null
        var result = Int.MAX_VALUE
        words.forEachIndexed { index, word ->
            if(word == word1) {
                i = index
                if(j != null) {
                    result = min(result, abs(i!! - j!!))
                }
            }

            if(word == word2) {
                j = index
                if(i != null) {
                    result = min(result, abs(i!! - j!!))
                }
            }
        }
        return result
    }
}