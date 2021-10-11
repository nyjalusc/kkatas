package dp.`256`

import kotlin.math.min

class PaintHouse {
    /**
     * We progressively build the dp array. The dp array represents cumulative min cost of painting a house with particular color
     * particular house. If we are given just one house to paint with red, blue green values as (10, 15, 12), then it means
     * the min total cost if we paint the house red is 10, blue is 15 and green is 12. Now this is our base case, we can
     * use the logic and the constraint to build our solution.
     *
     * If we want to paint second house red (0), then we know that first house cannot be of red color, so we calculate
     * min cost by painting second house red = cost of painting second house red + min(total costs of painting previous house blue or green)
     *
     * Time: O(N)
     * Space: O(N)
     */
    fun minCost(costs: Array<IntArray>): Int {
        val dp = Array(costs.size) { Array(costs[0].size) { 0 } }

        for (i in dp.indices) {
            if(i == 0) {
                dp[0][0] = costs[0][0]
                dp[0][1] = costs[0][1]
                dp[0][2] = costs[0][2]
            } else {
                dp[i][0] = costs[i][0] + min(dp[i - 1][1], dp[i - 1][2])
                dp[i][1] = costs[i][1] + min(dp[i - 1][0], dp[i - 1][2])
                dp[i][2] = costs[i][2] + min(dp[i - 1][0], dp[i - 1][1])
            }

        }
        return min(dp[dp.lastIndex][0], min(dp[dp.lastIndex][1], dp[dp.lastIndex][2]))
    }
}