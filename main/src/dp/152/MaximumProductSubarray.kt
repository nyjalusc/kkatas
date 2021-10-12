package dp.`152`

import kotlin.math.max
import kotlin.math.min

class MaximumProductSubarray {
    /**
     * Since the numbers can be negative which could later give the biggest max result
     * we maintain two dp arrays to keep track of the min and max values found so far
     * Time: O(N)
     * Space: O(N)
     */
    fun maxProduct(nums: IntArray): Int {
        var result = 0
        val dpMax = IntArray(nums.size)
        val dpMin = IntArray(nums.size)

        dpMax[0] = nums[0]
        result = nums[0]
        dpMin[0] = nums[0]

        for (i in 1 until nums.size) {
            if (nums[i] > 0) {
                dpMax[i] = max(nums[i], dpMax[i - 1] * nums[i])
                dpMin[i] = min(nums[i], dpMin[i - 1] * nums[i])
            } else {
                dpMax[i] = max(nums[i], dpMin[i - 1] * nums[i])
                dpMin[i] = min(nums[i], dpMax[i - 1] * nums[i])
            }
            result = max(result, dpMax[i])
        }
        return result
    }
}