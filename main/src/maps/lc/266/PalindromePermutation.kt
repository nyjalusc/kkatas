package maps.lc.`266`

/**
 *
 */
class PalindromePermutation {
    /**
     * Palindrome string can be formed if freq count of every char is even or there is only one odd freq count for a char.
     * If the above condition is not met a palindrome string cannot be formed.
     */
    fun canPermutePalindrome(s: String): Boolean {
        // val map = mutableMapOf<Char, Int>()
        // s.forEach {
        //     map[it] = map.getOrElse(it) { 0 } + 1
        // }

        // Considering all Ascii chars
        val map = Array(128) { 0 }
        s.forEach {
            map[it.toInt()] = map[it.toInt()] + 1
        }

        val oddFreqCharCount = map.filter { it % 2 != 0 }.size
        return oddFreqCharCount <= 1
    }
}