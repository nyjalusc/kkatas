package dp.`70`

class ClimbingStairs {
    /**
     * Bottom Up
     * Same as Fibonacci:
     * Total number of ways = number of ways to reach step n - 1 + num of ways to reach step n - 2
     * In bottom up we can use two variables to compute the next subproblem
     * Time: O(N)
     * Space: O(1)
     */
    fun climbStairs(n: Int): Int {
        if (n <= 2) return n
        var f1 = 1
        var f2 = 2
        var result = 0
        for (i in 3..n) {
            result = f1 + f2
            f1 = f2
            f2 = result
        }
        return result
    }

    /**
     * Recursive with memoization
     * Time: O(N)
     * Space: O(N)
     */
    fun climbStairsDP(n: Int): Int {
        val dp = IntArray(n + 1)
        return climbStairsHelper(n, dp)
    }

    fun climbStairsHelper(n: Int, dp: IntArray): Int {
        if (n <= 2) return n
        // If cached return
        if (dp[n] != 0) return dp[n]
        // Memoize
        dp[n] = climbStairsHelper(n - 1, dp) + climbStairsHelper(n - 2, dp)
        return dp[n]
    }
}