package binarysearch.`33`

import kotlin.math.min

/**
 * Solution 1: Divide the problem into two sub problems, find the pivot point and find min in each subarray.
 *
 * Solution 2: Compare the nums[mid] with nums[end] to check if the bounds (start and end) are in mixed elements
 * meaning start is in sorted part of the input and end is in rotated part. If the array is rotated and nums[mid]
 * is greater than nums[end] we know the smallest element must be on the right so we do start = mid + 1. If both
 * mid and end are in rotated part of the array nums[mid] could be the result but there could be a better result on
 * the left of mid, so we move end = mid - 1. Save the best result using min() every iteration.
 *
 * Think through all 3 cases nums[mid] < nums[end], nums[mid] > nums[end] and nums[mid] == nums[end].
 * Unlinke 154 because there are no duplicates nums[mid] == nums[end] will not happen unless there is just one element
 * in the array. Because we take floor while calculating mid, the value of mid has a bias to move towards start.
 *
 * Time: O(log N)
 * Space: O(1)
 */
class FindMinInRotatedSortedArray_153 {
    fun findMin(nums: IntArray): Int {
        if(nums.isEmpty()) return -1
        if(nums.size == 1) return nums[0]

        var start = 0
        var end = nums.lastIndex
        var result = Int.MAX_VALUE
        while(start <= end) {
            val mid = start + (end - start) / 2
            result = min(result, nums[mid])
            if(nums[mid] < nums[end]) {
                // sorted
                end = mid - 1
            } else {
                // rotated
                start = mid + 1
            }
        }
        return result
    }

    /**
     * Modified Optimal solution without using min()
     */
    fun findMin2(nums: IntArray): Int {
        if(nums.isEmpty()) return -1
        if(nums.size == 1) return nums[0]

        var start = 0
        var end = nums.lastIndex
        while(start < end) { // Important: if both start and end converge to an element that must be the min value
            val mid = start + (end - start) / 2
            if(nums[mid] < nums[end]) {
                // sorted
                // Because mid could be the answer we cannot throw it out hence we are not doing end = mid - 1
                // we will make start to converge to this point.
                end = mid
            } else {
                // rotated
                // This means mid does not belong to the rotated array subarray yet, so its safe to do start = mid + 1
                start = mid + 1
            }
        }
        return nums[start]
    }
}