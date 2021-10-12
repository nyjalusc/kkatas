package dp.`730`

class CountDifferentPalindromicSubsequences {
    /**
     * Difficult problem:
     * Case 1: First and last char of the input is same:
     * Consider string abba
     * All palindromic sequences: a, b, b, a, aba, aba, aa, bb, abba -> 9
     * Palindromic sequences for suffix bba -> b, b, a, bb -> 4
     * Palindromic sequences for prefix abb -> a, b, b, bb -> 4
     *
     * So result 9 = 4 + 4 + 1 ie. suffix + prefix + 1
     *
     * Case 2: First and last char are different
     * consider string abb
     * All palindromic sequences: a, b, b, bb -> 4
     * Palindromic sequences for suffix bb: 3
     * Palindromic sequences for middle b: 1
     * Palindromic sequences for prefix ab: 2
     * So result 4 = 3 + 2 - 1 -> ie. suffix + prefix - middle
     *
     * Base Case: When char is of len 1, result is 1. When char is of len 2 and last and first char are different it is
     * 2 otherwise 3
     *
     * Using the base case we can build the solution in a bottom up manner by using a dp[i][j] where the matix will hold
     * the count of palindromic subsequence in a substring starting at index i and ending at j.
     *
     * Case 1: First and last char are same: dp[i][j] = dp[i][j - 1] (Prefix) + dp[i + 1][j] (suffix) + 1
     * Case 2: First and last char are different: dp[i][j] = dp[i][j - 1] (Prefix) + dp[i + 1][j] (suffix) - dp[i + 1][j - 1] (middle)
     */
    fun countPalindromicSubsequences(s: String): Int = TODO()
}