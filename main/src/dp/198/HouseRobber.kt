package dp.`198`

import kotlin.math.max

class HouseRobber {
    /**
     * At every house you see if you will maximize result by looting the house and adding it the total of looting made
     * until one house over (i - 2) OR would you make more by looting the previous house (i - 1) and skipping this one.
     * ie. dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])
     * Time: O(n)
     * Space: O(n)
     */
    fun rob(nums: IntArray): Int {
        if(nums.isEmpty()) return 0

        val dp = IntArray(nums.size) { 0 }
        dp[0] = nums[0]
        for (i in 1 until nums.size) {
            if(i == 1) {
                dp[i] = max(nums[i], dp[i - 1])
            }
            else {
                dp[i] = max(nums[i] + dp[i - 2], dp[i - 1])
            }
        }
        return dp.maxOf{ it }
    }
}