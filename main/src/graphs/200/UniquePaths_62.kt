package graphs.`200`

class UniquePaths_62 {
    /**
     * Brute improved with memoization with a map
     *
     * Time Complexity:
     * O(m * n): Each cell in the m x n grid is visited at most once due to memoization. Without memoization, the
     * complexity would be exponential due to the overlapping subproblems.
     *
     * Space Complexity:
     * O(m * n): The memoization map stores up to m * n unique states. Additionally, the recursive call stack could go
     * up to m + n deep, but this is overshadowed by the space used for memoization in the worst case.
     */
    fun uniquePaths(m: Int, n: Int): Int {
        // Use a mutable map to store intermediate results for memoization
        val memo = mutableMapOf<Pair<Int, Int>, Int>()
        return findPaths(0, 0, m - 1, n - 1, memo)
    }

    private fun findPaths(x: Int, y: Int, maxX: Int, maxY: Int, memo: MutableMap<Pair<Int, Int>, Int>): Int {
        // Base case: when the current position is the bottom-right corner
        if (x == maxX && y == maxY) return 1

        // If the current position is out of bounds
        if (x > maxX || y > maxY) return 0

        // Check if the result for the current position is already calculated
        val key = Pair(x, y)
        memo[key]?.let { return it }

        // Calculate the number of paths by exploring right and down moves
        val paths = findPaths(x + 1, y, maxX, maxY, memo) + findPaths(x, y + 1, maxX, maxY, memo)

        // Store the result in the memo map before returning
        memo[key] = paths
        return paths
    }

    /**
     * TIme: O(m * n)
     * Space: O(m * n)
     */
    fun uniquePathsDP(m: Int, n: Int): Int {
        val dp = Array(m) { IntArray(n) { 0 }}

        for (i in 0 until m) dp[i][0] = 1
        for (i in 0 until n) dp[0][i] = 1

        for (i in 1 until m)
            for (j in 1 until n) {
                // Total paths = number of ways you can reach cell above + num of ways you can reach the cell on the left
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            }

        return dp[m - 1][n - 1]
    }
}