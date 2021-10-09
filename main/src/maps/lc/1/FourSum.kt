package maps.lc.`1`

import java.util.*


class FourSum {
    /**
     * Time: O(N^3)
     * Space: O(N)
     */
    fun fourSum(nums: IntArray?, target: Int): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()
        if (nums!!.isEmpty()) return result
        nums.sort()

        for (i in 0 until nums.size - 3) {
            // Skip duplicate elements
            if (i > 0 && nums[i] == nums[i - 1]) continue
            val currentNumber = nums[i]
            val threeSumTarget = target - currentNumber
            threeSumHelper(nums, i, threeSumTarget, i + 1, result)
        }
        return result
    }

    private fun threeSumHelper(
        nums: IntArray,
        current: Int,
        target: Int,
        start: Int,
        result: MutableList<MutableList<Int>>
    ) {
        for (i in start until nums.size - 2) {
            // Skip duplicate elements
            if (i - 1 > current && nums[i] == nums[i - 1]) continue
            val twoSumTarget = target - nums[i]
            val left = i + 1
            val right = nums.lastIndex
            twoSumHelper(nums, i, twoSumTarget, left, right, result)
        }
        for (list in result) {
            if (list.size < 4) list.add(0, nums[current])
        }
    }

    private fun twoSumHelper(
        nums: IntArray,
        current: Int,
        target: Int,
        left: Int,
        right: Int,
        result: MutableList<MutableList<Int>>
    ) {
        var left = left
        var right = right
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                val triplet = mutableListOf<Int>()
                triplet.add(nums[current])
                triplet.add(nums[left])
                triplet.add(nums[right])
                result.add(triplet)
                left++
                right--

                // Skip duplicate elements
                while (nums[left] == nums[left - 1] && left < right) left++

                // Skip duplicate elements
                while (nums[right] == nums[right + 1] && left < right) right--
            } else if (nums[left] + nums[right] < target) {
                left++
            } else {
                // nums[left] + nums[right] > target
                right--
            }
        }
    }
}