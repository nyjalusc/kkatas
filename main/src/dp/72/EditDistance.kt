package dp.`72`

import kotlin.math.min

class EditDistance {
    /**
     * Imagine we are converting word1 into word2
     * We create dp[word1.length + 1][word2.length + 1], init base condition as cost of transforming "" (empty) to a string.
     * Cost will be length of second string. So value along 0th row and col will be index value. When chars are same
     * cost is the same as prev cost dp[i - 1][j - 1]. When they are not same we look at the min of (insertion, deletion, substitution) cost.
     * Whichever is smaller we take that and add 1 to it
     * Time: O(mn)
     * Space: O(mn)
     */
    fun minDistance(word1: String, word2: String): Int {
        if(word1.isEmpty()) return word2.length
        if(word2.isEmpty()) return word1.length


        val dp = Array(word1.length + 1){ Array(word2.length + 1) { 0 } }
        // Init 0th row and col, indicates cost to transform a "" (empty) string to one of the substring
        for (i in dp.indices) {
            dp[i][0] = i
        }
        for (j in dp[0].indices) {
            dp[0][j] = j
        }

        for (i in 1 until dp.size) {
            for (j in 1 until dp[0].size) {
                if (word1[i - 1] == word2[j - 1]) {
                    // Chars are the same, there is no extra cost, copy the previous cost
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    val matchOrSubstitutionCost = dp[i - 1][j - 1]
                    // Insertion cannot be made in word1, so we emulate this by deleting char from word 2
                    val insertionCost = dp[i][j - 1]
                    // Deletion
                    val deletionCost = dp[i - 1][j]
                    dp[i][j] = min(insertionCost, min(matchOrSubstitutionCost, deletionCost)) + 1
                }
            }
        }
        return dp[word1.length][word2.length]
    }
}