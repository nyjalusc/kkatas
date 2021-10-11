package dp.`1143`

import kotlin.math.max

class LongestCommonSubsequence {
    /**
     * We create a 2D array representing the lengths of input string. eg. dp[0][0] contains LCS result when length of
     * both string is 0. All of dp[i][0] and dp[0][j] will be zero because if one of the string is of 0 length, there is
     * no computation required. This is taken care of by Kotlin Array Initialization
     *
     * To fill the remaining matrix, we check:
     * 1. If both text1[i] and text2[j] is the same char then we add 1 to the result of previous result when these chars were
     * not counted, ie 1 + dp[i - 1][j - 1]
     * 2. Otherwise we see if we get better result by omitting one of the two chars. So we take
     * max(dp[i][j - 1], dp[i - 1][j])
     * The result at the end is stored in dp[text1.length][text2.length]
     */
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val dp = Array(text1.length + 1) { Array(text2.length + 1) { 0 } }

        for (i in 1 until dp.size) {
            for (j in 1 until dp[0].size) {
                if (text1[i - 1] == text2[j - 1]) { // here -1 offset is required because, i & j represent length bounds
                    dp[i][j] = 1 + dp[i - 1][j - 1] // if both are same then we add 1 to the length of previous result without both chars
                } else {
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]) // max of (without char in text1, without char in text2)
                }
            }
        }

        return dp[text1.length][text2.length]
    }
}