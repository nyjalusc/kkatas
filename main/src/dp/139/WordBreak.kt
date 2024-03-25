package dp.`139`

class WordBreak {

    val memo = mutableMapOf<String, Boolean>()
    val seen = mutableSetOf<String>() // Cache of strings that won't yield positive result

    /**
     * Checks if a given string can be formed by concatenating words from a dictionary.
     *
     * This implementation uses a top-down recursive approach, with potential for redundancy
     * for overlapping subproblems. Consider memoization or bottom-up dynamic programming for
     * better performance with larger inputs.
     *
     * Time Complexity: O(n^2 * m) - n * n combinations of substrings are evaluated m times, where m is the num of strings in dict
     * Space Complexity: O(n) - worst recursion stack will grow up to length of given string s
     *
     * @param s The string to check.
     * @param wordDict The dictionary of allowed words.
     * @return True if the string can be formed using the dictionary, False otherwise.
     */
    fun wordBreakBrute(s: String, wordDict: List<String>): Boolean {
        if (s.isEmpty()) return true

        for (word in wordDict) {
            // Check if word is a prefix, if yes make a recursive call on the remaining string
            if (s.startsWith(word) && wordBreakBrute(s.substring(word.length), wordDict)) {
                return true
            }
        }
        return false
    }

    /**
     * Checks if a given string can be formed by concatenating words from a dictionary,
     * using memoization to improve performance.
     *
     * This implementation utilizes memoization to store results of subproblems, avoiding
     * redundant computations and improving efficiency compared to the basic brute-force
     * approach.
     *
     * Time Complexity: O(n ^ 2 * m)
     * Space Complexity: O(n + m)
     *
     * @param s The string to check.
     * @param wordDict The dictionary of allowed words.
     * @return True if the string can be formed using the dictionary, False otherwise.
     */
    fun wordBreakBruteOptimized(s: String, wordDict: List<String>): Boolean {
        if (memo.containsKey(s)) return memo[s]!! // Check memoization

        if (s.length == 0) return true // Base case: empty string is valid

        for (word in wordDict) {
            if (s.startsWith(word) && wordBreakBruteOptimized(s.substring(word.length), wordDict)) {
                memo[s] = true
                return true
            }
        }

        memo[s] = false // Store result for future reference
        return false
    }

    /**
     * Checks if a given string can be formed by concatenating words from a dictionary,
     * using early pruning to avoid redundant computations.
     *
     * This implementation utilizes a `seen` set to track visited subproblems and avoid
     * exploring them again, potentially improving performance compared to the basic
     * brute-force approach. However, it might not be as efficient as solutions using
     * memoization for subproblem results.
     *
     * Time Complexity: O(N^2 * m)
     * Space Complexity: O(n + m)
     *
     * @param s The string to check.
     * @param wordDict The dictionary of allowed words.
     * @return True if the string can be formed using the dictionary, False otherwise.
     */
    fun wordBreakLimitedMemoization(s: String, wordDict: List<String>): Boolean {
        if (seen.contains(s)) return false
        if (s.isEmpty()) return true

        for (word in wordDict) {
            if (s.startsWith(word) && wordBreakLimitedMemoization(s.substring(word.length), wordDict)) {
                return true
            }
        }
        seen.add(s)
        return false
    }

    /**
     * Checks if a given string can be formed by concatenating words from a dictionary,
     * using bottom-up dynamic programming for efficient solution.
     *
     * This implementation iteratively builds up the solution using a `dp` array,
     * avoiding recursion and redundant computations. It generally offers better
     * performance compared to brute-force approaches.
     *
     * Time Complexity: O(n * m)
     * Space Complexity: O(n)
     *
     * @param s The string to check.
     * @param wordDict The dictionary of allowed words.
     * @return True if the string can be formed using the dictionary, False otherwise.
     */
    fun wordBreakDp(s: String, wordDict: List<String>): Boolean {
        val dp = BooleanArray(s.length + 1) { false }
        dp[0] = true // Base case: empty string is valid - Most IMPORTANT

        for (i in 1..s.length) {
            for (word in wordDict) {
                // Slowly build the string if we can find a solution for the current subproblem which aligns with the
                // previous sub-problem then we have a complete solution
                if (i >= word.length && dp[i - word.length] && s.substring(i - word.length, i) == word) {
                    dp[i] = true
                    break // Optimization: stop checking words after finding a valid combination
                }
            }
        }

        return dp[s.length] // Final result from the dp array
    }



}