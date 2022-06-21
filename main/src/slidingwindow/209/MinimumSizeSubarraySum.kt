package slidingwindow.`209`

class MinimumSizeSubarraySum {
    /**
     * Classic Variable window size problem
     * Runtime: O(N)
     * Space: O(1)
     */
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var i = 0
        var j = 0
        var result = Int.MAX_VALUE
        var remSum = target
        while (j < nums.size) {
            // Compute current value and expand the window
            remSum -= nums[j++]
            // Shrink until a condition is met
            // In the process of shrinking the window you might get multiple results, pick the best one
            while(remSum <= 0) {
                // Same as result = Math.min(result, j - i)
                result = result.coerceAtMost(j - i)
                remSum += nums[i++]
            }
        }
        return if(result == Int.MAX_VALUE) 0 else result
    }
}