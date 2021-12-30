package arrays.lc.slidingwindow.`713`

class SubarrayProductLessThanK {
    /**
     * Sliding Window: use left a right pointer to compute the product of elements in the window. Every time we overshoot,
     * we shrink the window and adjust the product by dividing with nums[left]. At the we count elements = right - left + 1.
     * We do this until right pointer is out of bounds.
     * Time: O(N)
     * Space: O(1)
     */
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
        if (nums.isEmpty() || k == 0) return 0

        var left = 0
        var prod = 1
        var count = 0
        for (right in nums.indices) {
            prod *= nums[right]
            while (prod >= k && left <= right) {
                prod /= nums[left++]
            }
            count += right - left + 1
        }
        return count
    }
}
