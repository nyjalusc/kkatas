package dp.`198`
import kotlin.math.max

class HouseRobberII_213 {
    /**
     * Similar to First part.
     * Since the houses are arranged in a circle we split the processing into two parts:
     * Compute max result from house 0 to last - 1 & from 1 to last
     * Max of these two computations is the result
     * Turn the solution of part 1 to work on ranges
     * Time: O(N)
     * Space: O(N)
     */
    fun rob(nums: IntArray): Int {
        if(nums.isEmpty()) return 0
        if(nums.size == 1) return nums[0]

        val maxRobbertyWithoutFirst = robHelper(nums, 1, nums.lastIndex)
        val maxRobbertyWithoutLast = robHelper(nums, 0, nums.lastIndex - 1)
        return max(maxRobbertyWithoutFirst, maxRobbertyWithoutLast)
    }

    private fun robHelper(nums: IntArray, start: Int, end: Int): Int {
        if(nums.isEmpty()) return 0

        val dp = IntArray(nums.size) { 0 }
        dp[start] = nums[start]
        for (i in (start + 1)..end) {
            if(i == (start + 1)) {
                dp[i] = max(nums[i], dp[i - 1])
            }
            else {
                dp[i] = max(nums[i] + dp[i - 2], dp[i - 1])
            }
        }
        return dp.maxOf{ it }
    }
}

