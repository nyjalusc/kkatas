package dp.`1143`

import kotlin.math.max

class LongestPalindromicSubsequence_516 {
    /**
     * Version of LCS
     * Received the second string by reversing the input string. And then its the same as finding longest common subsequence
     * between the input string and the reversed string.
     */
    fun longestPalindromeSubseq(s: String): Int {
        val r = s.reversed()
        val dp = Array(s.length + 1) { Array(r.length + 1) { 0 } }

        for (i in 1 until dp.size) {
            for (j in 1 until dp[0].size) {
                if (s[i - 1] == r[j - 1]) { // here -1 offset is required because, i & j represent length bounds
                    dp[i][j] = 1 + dp[i - 1][j - 1] // if both are same then we add 1 to the length of previous result without both chars
                } else {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]) // max of (without char in text1, without char in text2)
                }
            }
        }

        return dp[s.length][r.length]
    }
}