package maps.lc.`1`

import java.util.*


class ThreeSum {
	fun threeSum(nums: IntArray): List<List<Int>> {
		val result = mutableListOf<List<Int>>()
		if (nums.isEmpty()) return result
		nums.sort()

		// Since every element will be compared with every other two elements skip the last two elements otherwise we will get
		// duplicate list with different order of elements. When we use twoSumHelper we are adding triplets in the format:
		// [target, nums[left], nums[right]]. if we have already added [-1, -1, 2] we can also have [2, -1, -1] if we process last two elements.
		// We do not want triplets with same values.
		for (i in 0 until nums.size - 2) {
			// Skip elements with same value
			if (i > 0 && nums[i] == nums[i - 1]) continue
			val target = -nums[i]
			val left = i + 1
			val right = nums.size - 1
			twoSumHelper(nums, result, target, left, right)
		}
		return result
	}

	private fun twoSumHelper(nums: IntArray, result: MutableList<List<Int>>, target: Int, left: Int, right: Int) {
		var left = left
		var right = right
		while (left < right) {
			if (nums[left] + nums[right] == target) {
				val triplet = mutableListOf<Int>()
				triplet.add(-target)
				triplet.add(nums[left])
				triplet.add(nums[right])
				result.add(triplet)


				// continue processing because there could be more triplets
				left++
				right--

				// Avoid elements with same values as we parse the list, this will help us avoid duplicate triplets in the final result
				// such as [[-1, -1, 2], [-1, -1, 2]] if our nums[] is [-4, -1, -1, 0, 1, 2]
				while (left < right && nums[left] == nums[left - 1]) {
					// new element for left pointer is the same as previous element so we can skip
					left++
				}
				while (left < right && nums[right] == nums[right + 1]) {
					// new element for right pointer is the same as previous element so we can skip
					right--
				}
			} else if (nums[left] + nums[right] < target) {
				// added numbers too small, lets increase
				left++
			} else {
				// added numbers too large, lets decrease
				right--
			}
		}
	}
}