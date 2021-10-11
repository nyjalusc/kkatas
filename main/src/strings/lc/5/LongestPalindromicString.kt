package strings.lc.`5`

class LongestPalindromicString {
    /**
     * Expand around center approach
     * Time: O(N^2)
     * Space: O(N)
     */
    fun longestPalindrome(s: String): String {
        if (s.length <= 1) return s
        var result = ""

        for (i in s.indices) {
            // Single char center (odd length)
            val oddCenteredResult = expand(s, i, i)
            if(result.length < oddCenteredResult.length) result = oddCenteredResult

            // Two char center (even center)
            val evenCenteredResult = expand(s, i, i + 1)
            if(result.length < evenCenteredResult.length) result = evenCenteredResult
        }

        return result
    }

    fun expand(s: String, left: Int, right: Int): String {
        var i = left
        var j = right
        while (i >= 0 && j < s.length && s[i] == s[j]) {
            i--
            j++
        }
        // i & j are out of bounds when they exist the loop, so we need to adjust i, j is anyways not included
        // for the substring()
        return s.substring(i + 1, j)
    }
}