package maps.lc.`953`

/**
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 * Time: O(N), N = all chars in given words
 * Space: O(K), K = length of the order string
 *
 * Algo:
 * 1. Create a map of Char to Postion (lexicographical position) from order string
 * 2. Create compareWords fun that returns -1 if s1 < s2, 0 if they are equal else 1
 * 3. Iterate over words and call compareWords to check two words at a time. If anyone
 * returns value > 0, return false otherwise continue and return true at the end.
 */
class VerifyAlienDictionary {
    fun isAlienSorted(words: Array<String>, order: String): Boolean {
        // could have also used IntArray(26){0}
        val map = mutableMapOf<Char, Int>()

        order.forEachIndexed { index, value -> map[value] = index }

        for (i in 0 until words.size - 1) {
            if (compareWords(words[i], words[i + 1], map) > 0)
                return false
        }
        return true
    }

    /**
     * Strings are compared by content, char by char. If they are not equal their positions in the
     * map are checked. If their lengths are different return l1 - l2
     */
    private fun compareWords(s1: String, s2: String, map: Map<Char, Int>): Int {
        val l1 = s1.length
        val l2 = s2.length
        var i = 0
        while(i < l1 && i < l2) {
            if(s1[i] != s2[i]) return map[s1[i]]!! - map[s2[i]]!!
            i++
        }

        val lenDiff = l1 - l2
        return when {
            lenDiff == 0 -> 0
            lenDiff > 0 -> 1
            else -> -1
        }
    }
}