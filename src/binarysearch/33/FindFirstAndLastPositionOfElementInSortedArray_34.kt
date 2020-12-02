package binarysearch.`33`

/**
 *
 */
class FindFirstAndLastPositionOfElementInSortedArray_34 {
    /**
     * Solution uses a modified Binary search. If element is found mid can point to one of the target values because
     * there can be multiple target values. Logic to find the target element is same as classic Binary Search. Whether
     * to go left to find the start of target or to go right to find the end of target is determinded by a boolean flag
     * passed to the helper function.
     *
     * We make two pass over the input, once to find the start of target and second time to find the end.
     *
     * To keep the solution simple and as close as to the classical impl, i am using a temp variable to save the result.
     * Otherwise we can also use the other variant where
     *  >> while(start < end) { make adjustments to the pointer so that start converges to the result }
     *  >> return start
     *  Requires quite a bit of finagling, my current impl is easier.
     *
     * Time: O(log N)
     * Space: O(1)
     */
    fun searchRange(nums: IntArray, target: Int): IntArray {
        var result = intArrayOf(-1, -1)

        val leftIdx = findIndex(nums, target, true)
        if(leftIdx !in 0..nums.lastIndex) {
            return result
        }

        val rightIdx = findIndex(nums, target, false)

        result = intArrayOf(leftIdx, rightIdx)
        return result
    }

    private fun findIndex(nums: IntArray, target: Int, findStart: Boolean): Int {
        var result = -1
        var start = 0
        var end = nums.lastIndex

        while(start <= end) {
            val mid = start + (end - start) / 2
            if(nums[mid] == target) {
                result = mid // a possible answer, lets save it and look for a better answer if available
                if(findStart) {
                    // We are looking for starting point, lets look for element on left of mid
                    end = mid - 1
                } else {
                    // We are looking for end point, lets look on right of mid
                    start = mid + 1
                }
            } else if (nums[mid] < target) {
                start = mid + 1
            } else {
                end = mid - 1
            }
        }

        return result
    }
}