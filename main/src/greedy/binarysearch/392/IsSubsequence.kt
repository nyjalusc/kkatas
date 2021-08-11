package greedy.binarysearch.`392`

/**
 *
 */
class IsSubsequence {
    /**
     * Two pointer technique
     * Time: O(min(Lt, Ls)), Length of t = Lt, Length of s = Ls
     * Space: O(1)
     */
    fun isSubsequence(s: String, t: String): Boolean {
        if (s.isEmpty()) return true
        if(s.isNotEmpty() && t.isEmpty()) return false
        var i = 0
        t.forEach {
            if(it == s[i]) i++
            if(i == s.length) return true
        }
        return false
    }

    /**
     * Follow up:
     * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if
     * T has its subsequence. In this scenario, how would you change your code?
     *
     * Create a hashmap of char to index list. Next iterate through subsequence string s, find the minIndex of char
     * using the hashmap and adjust the minIndex bounds every iteration. Once we find the current char c in s at index i
     * in string t. Then we can deduce that the next char in s should at least be at index x + 1 or more in string t.
     * In this implm I am doing a linear search, the next solution improves on it by doing a binary search.
     *
     * Time: O(T + S * T)
     *       O(T) for hashmap creation. O(S * T) to iterate over every char in S and worst case it can have a upper bound of O(T)
     *       We impove O(S * T) to O(S * log(T)) in the solution below by using binary search instead of linear search.
     * Space: O(T) for hashmap
     */
    fun isSubsequenceLinear(s: String, t: String): Boolean {
        val map: MutableMap<Char, MutableList<Int>> = mutableMapOf()

        // Prepare map of char -> List of Index
        t.forEachIndexed { index, c ->
            val list = map.getOrPut(c) { mutableListOf() }
            list.add(index)
        }

        // Index of the char we find in indexList should be at least indexLowerBound or greater
        var indexLowerBound = 0
        s.forEach { character ->
            val indexList = map[character] ?: return false
            val selectedIndex = indexList.firstOrNull { it >= indexLowerBound } ?: return false
            indexLowerBound = selectedIndex + 1
        }

        return true
    }

    /**
     * Improvement upon previous solution by implementing seach logic using Binary Search.
     */
    fun isSubsequenceBinarySearch(s: String, t: String): Boolean {
        val map: MutableMap<Char, MutableList<Int>> = mutableMapOf()

        // Prepare map of char -> List of Index
        t.forEachIndexed { index, c ->
            val list = map.getOrPut(c) { mutableListOf() }
            list.add(index)
        }

        var findIndex = 0
        s.forEachIndexed { index, c ->
            val indexList = map[c] ?: return false
            var indexInList = indexList.binarySearch(findIndex)
            if (indexInList < 0) {

                // Failed to find the index, but the negative value indicates one greater than where it should have been.
                // Lets convert it into positive value
                indexInList = -indexInList - 1
            }
            // At this point we can say that we found the char c in s at index indexInList
            // Since we adjust the value of index in case of a miss, we must make sure the
            // computed index is still within bounds ie.
            if(indexInList !in 0 until indexList.size) return false
            findIndex = map[c]!![indexInList] + 1
        }

        return true
    }
}