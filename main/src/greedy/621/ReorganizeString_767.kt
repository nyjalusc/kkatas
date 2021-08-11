package greedy.`621`

import java.util.*


/**
 * Check TaskScheduler solution first.
 *
 * Alternate solution:
 * No Sort O(N):
 * 1. count letter appearance and store in hash[i]
 * 2. find the letter with largest occurence.
 * 3. put the letter into even index numbe (0, 2, 4 ...) char array
 * 4. put the rest into the array
 * https://leetcode.com/problems/reorganize-string/discuss/232469/Java-No-Sort-O(N)-0ms-beat-100
 */
class ReorganizeString_767 {
    fun reorganizeString(S: String): String? {
        // Create map of each char to its count
        val map = mutableMapOf<Char, Int>()
        for (c in S) {
            val count = map.getOrElse(c) { 0 } + 1
            // Impossible to form a solution
            if (count > (S.length + 1) / 2) return ""
            map[c] = count
        }

        // Greedy: fetch char of max count as next char in the result.
        // Use PriorityQueue to store pairs of (char, count) and sort by count DESC.
        val queue = PriorityQueue<Map.Entry<Char, Int>>(compareByDescending { it.value })
        queue.addAll(map.entries)

        // Build the result.
        val sb = StringBuilder()
        while (!queue.isEmpty()) {
            val mostFrequentEntry = queue.poll() as MutableMap.MutableEntry
            if (sb.isEmpty() || mostFrequentEntry.key != sb.last()) {
                sb.append(mostFrequentEntry.key)
                mostFrequentEntry.setValue(mostFrequentEntry.value - 1)
                if (mostFrequentEntry.value > 0) {
                    queue.add(mostFrequentEntry)
                }
            } else {
                val secondMostFrequentEntry = queue.poll() as MutableMap.MutableEntry
                sb.append(secondMostFrequentEntry.key)
                secondMostFrequentEntry.setValue(secondMostFrequentEntry.value - 1)
                if (secondMostFrequentEntry.value > 0) {
                    queue.add(secondMostFrequentEntry)
                }
                queue.add(mostFrequentEntry)
            }
        }
        return sb.toString()
    }
}