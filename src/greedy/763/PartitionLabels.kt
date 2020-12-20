package greedy.`763`

/**
 *
 */
class PartitionLabels {
    /**
     * Two pointer greedy approach:
     * Prepare a list that maintains last occurence of every character. It can be Array(26), I am using a map here.
     * Using this info, use two pointers to keep track of the largest window/interval formed by all the characters
     * processed until now. We read one char at a time from the string and adjust bounds of the window. If our current
     * pointer reaches the end of the pointer it means, this is the size of one partition, record it and reset the window
     * to start processing the next interval.
     *
     * Time: O(N)
     * Space: O(1), map keys will not contain more than 26 entries (upper bound is constant)
     *
     * Alternate Solution:
     * Merge intervals, similar to the below solution, we create intervals and then merge them. Result is the size of
     * all dispjoint intervals.
     */
    fun partitionLabels(S: String): List<Int> {
        if(S.isEmpty()) return emptyList()
        val result = mutableListOf<Int>()
        val map = HashMap<Char, Int>()
        S.forEachIndexed { index, c -> map[c] = index }

        // Window start and end pointers
        var start = 0
        var end = 0
        for (i in S.indices) {
            end = end.coerceAtLeast(map[S[i]]!!) // same as end = Math.max(end, map[S[i]]!!)
            if (i == end) {
                result.add(i - start + 1)
                // reset the window
                start = i + 1
                end = start
            }
        }
        return result
    }
}