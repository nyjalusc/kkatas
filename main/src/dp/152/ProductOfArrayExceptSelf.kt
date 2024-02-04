package dp.`152`

class ProductOfArrayExceptSelf {
    /**
     * Bottom up approach where you compute partial results when iterating from left to right
     * and then update it with complete results while iterating and computing backwards.
     * ### AVOID USING DIVISION, IT WILL CAUSE PROBLEM WITH 0 Element ####
     * Time: O(N)
     * Space: O(1)
     */
    fun productExceptSelf(nums: IntArray): IntArray {
        val dp = IntArray(nums.size) { 1 }

        dp[0] = 1

        // Build partial result using elements on the left side, build a running result by multiplying all elements
        // Only dp[nums.lastIndex] would have complete result at this point. All other elements have partial results,
        // because they do not have info about elements to their right
        // Another alternative is to init dp[0] = 1 and then dp[i] = dp[i - 1] * nums[i - 1]
        var left = nums[0]
        for (i in 1 until nums.size) {
            dp[i] = left
            left *= nums[i]
        }

        // Do the same but in reverse to update the result
        var right = nums[nums.lastIndex]
        for (i in (nums.lastIndex - 1) downTo 0) {
            dp[i] *= right
            right *= nums[i]
        }
        return dp
    }
}