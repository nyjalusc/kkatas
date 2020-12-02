package binarysearch.`1060`

/**
 *
 */
class MissingElementInSortedArray {
    /**
     * Important: Formula to deduce total number of missing elements between any two given indices:
     * -> nums[j] - nums[i] - (j - i) (for i < j)
     * In this solution we use the formula to deduce total number of missing elements from the beginning
     * to some current index i. So the formula is updated to nums[i] - nums.first() - i
     *
     * To short circuit, we check if k > all the missing elements between 0, N - 1 indices. If its not we can
     * calculate offset = k - [missing values between [0, N-1]] and the result will be nums[n-1] + offset
     *
     * Else, we need to now do a linear search within the array. We use a pointer and keep incrementing it until
     * number of missing elements from [0, pointer] > k. At this point we know that the pointer is at upper bound
     * because number of missing elements from [0, pointer] > k so its quite safe to say that [pointer - 1] <= k
     * Calculate offset to compute final result:
     * nums[pointer - 1] + (k - missing values between [0, pointer - 1]), for readability pointer - 1 is represented
     * as prev in the solution.
     *
     * Time: O(log N)
     * Space: O(1)
     */
    fun missingElementLinear(nums: IntArray, k: Int): Int {
        if (k > missingUntilIdx(nums, nums.lastIndex)) {
            return nums.last() + (k - missingUntilIdx(nums, nums.lastIndex))
        }

        var index = 0
        var prev = 0
        while (missingUntilIdx(nums, index) < k) {
            prev = index
            index++
        }

        // At this point index has overflown and prev is closest small index using which we can determine the missing
        // value
        return nums[prev] + (k - missingUntilIdx(nums, prev))
    }

    private fun missingUntilIdx(nums: IntArray, index: Int): Int {
        return nums[index] - nums.first() - index
    }

    /**
     * Improve search from previous solution by implementing a binary search. We use the second version of binary search
     * (left < right) to implement the solution. We always check if the number of missing elements between [0, mid] < k,
     * if it is we need to perform our search on the right side so we move left/start pointer to mid + 1. I could have
     * also adjusted K every time I move start pointer by doing K = K - [missing values between [left, mid]], and then
     * at the end do nums[left - 1] + k. Here I do the offset calculation at the very end because we have the formula
     * to calculate missing elements between any to indices. During binary search i only focus on moving left pointer.
     * At the end, left pointer will overflow and will be one position greater than the desired position.
     *
     * Result: nums[left - 1] + Offset, here Offset = K - MissingElements between [0, left - 1]
     */
    fun missingElement(nums: IntArray, k: Int): Int {
        if (k > missingUntilIdx(nums, nums.lastIndex)) {
            return nums.last() + (k - missingUntilIdx(nums, nums.lastIndex))
        }

        var left = 0
        var right = nums.lastIndex
        while (left < right) {
            val mid = left + (right - left) / 2
            if(missingUntilIdx(nums, mid) < k) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        // At this point left has overflown and is one element right of the the position we wanted
        return nums[left - 1] + (k - missingUntilIdx(nums, left - 1))
    }
}