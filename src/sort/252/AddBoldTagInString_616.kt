package sort.`252`

import java.lang.StringBuilder
import java.util.*
import kotlin.math.max
import kotlin.math.min
import kotlin.test.assertEquals

/**
 * For every word in the dictionary we want to find all the starting and ending indices in the given string.
 * We can use String.indexOf() for this. This will give us a collection of all intervals of [start, end] for
 * all the dictionary words that can be found. Now this can be reduced to a interval merge probelem. Now the
 * using Priorrity Queue (Max Heap) we can easily merge the intervals. The merged intervals in the collections
 * will tell us at what index we should be inserting <b> and </b> tags. Interate over the string by indices and
 * look if the index is present in the merged intervals collection. To simplify this I create a startSet and endSet
 * to make the lookup easy.
 *
 * Do not use Pair as the properties are immutable, an array of size 2 would be a better choice.
 *
 * Time: O(N Log N)
 * Space: O(N)
 */
class AddBoldTagInString_616 {
    fun addBoldTag(s: String, dict: Array<String>): String {
        if(s.isEmpty() || dict.isEmpty()) return s

        // Find all the start and end indices of every dictionary word that can be found in the string s
        // Create Pair (intervals) out of it
        val intervalList = mutableListOf<Pair<Int, Int>>()
        dict.forEach { word ->
            for (i in s.indices) {
                val index = s.indexOf(word, startIndex = i)
                if(index != -1) intervalList.add(Pair(index, index + word.length - 1))
            }
        }

        // merge intervals
        val mergedIntervals = mergeIntervals(intervalList.distinct().toMutableList())
        val startSet = mergedIntervals.map { it.first }
        val endSet = mergedIntervals.map { it.second }

        // Process input string to prepare the result
        val sb = StringBuilder()
        for (i in s.indices) {
            // if index found in startSet add <b> tag
            if (i in startSet) {
                sb.append("<b>")
            }

            sb.append(s[i])

            // if index found in endSet set add </b> tag
            if (i in endSet) {
                sb.append("</b>")
            }
        }
        return sb.toString()
    }

    // Classic interval merge implementation
    private fun mergeIntervals(intervalList: MutableList<Pair<Int, Int>>): List<Pair<Int, Int>> {
        intervalList.sortBy { it.first }
        // Max heap, to merge increasing intervals
        val pq = PriorityQueue<Pair<Int, Int>>(compareByDescending { it.second })

        intervalList.forEach { interval ->
            if (pq.isEmpty()) {
                pq.add(interval)
            } else {
                var mergedInterval = pq.poll()
                // there is no overlap
                if (interval.first !in (mergedInterval.first-1)..(mergedInterval.second+1) &&
                    interval.second !in (mergedInterval.first-1)..(mergedInterval.second+1)) {
                    pq.add(interval)
                } else if (interval.first in (mergedInterval.first-1)..(mergedInterval.second+1) ||
                    interval.second in (mergedInterval.first-1)..(mergedInterval.second+1)
                ) {
                    // Overlap min start bound and max end bound
                    mergedInterval = Pair(min(mergedInterval.first, interval.first), max(mergedInterval.second, interval.second))
                }
                // Add the updated/removed interval back in the PQ
                pq.add(mergedInterval)
            }
        }
        return pq.toList()
    }
}

fun main() {
    val solution = AddBoldTagInString_616()
    assertEquals("<b>aaabbc</b>c", solution.addBoldTag("aaabbcc", arrayOf("aaa", "aab", "bc")))
    assertEquals("<b>a</b>b<b>c</b>d<b>e</b>f", solution.addBoldTag("abcdef", arrayOf("a","c","e","g")))
}