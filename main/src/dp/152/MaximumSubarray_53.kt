package dp.`152`

import kotlin.math.max

class MaximumSubarray_53 {
    /**
     * Build the solution in dp[] one element at a time
     * dp[i] = max(dp[i - 1] + nums[i], nums[i]), current num + best result so far or just the num itself
     * Time: O(N)
     * Space: O(N) - can be improved to O(1), because we only care about one previous result
     */
    fun maxSubArray(nums: IntArray): Int {
        if(nums.isEmpty()) return 0

        val dp = IntArray(nums.size) { 0 }
        dp[0] = nums[0]
        var maxSum = nums[0]

        for (i in 1..nums.lastIndex) {
            dp[i] = max(nums[i], dp[i - 1] + nums[i])
            maxSum = max(dp[i], maxSum)
        }

        return maxSum
    }

    /**
     * BF is done by running a loop starting at i to all the way to end and summing elements along the way
     * Time: O(N^2)
     * Space: O(1)
     */
    fun maxSubArrayBruteForce(nums: IntArray): Int {
        var maxSubarraySum = Int.MIN_VALUE
        for (i in nums.indices) {
            var currentSubarraySum = 0
            for (j in i until nums.size) {
                currentSubarraySum += nums[j]
                maxSubarraySum = max(maxSubarraySum, currentSubarraySum)
            }
        }
        return maxSubarraySum
    }
}