package binarysearch.`33`

import kotlin.math.min

/**
 *
 */
class FindMinInRotatedSortedArray_154 {

    /**
     * Same as 153, added a case to handle duplicates.
     * Goal is to find the pivot point, that will be smallest number in the given array.
     * When we encounter duplicates we cannot confidently say that the pivot point exists in [start, mid] or [mid, end]
     * because the input can look like this
     * 1. [2, 1, 2, 2, 2] (pivot is on left of mid) OR
     * 2. [2, 2, 2, 1, 2] (pivot is on right of mid).
     * To handle duplicates the algorithm runtime degrades to O(N). We move end to the left by one position and repeat
     * the search.
     *
     * This solution is closer to the classical Binary Search impl. Before moving past mid element we save the temporary
     * result in a variable.
     *
     * Think through all 3 cases nums[mid] < nums[end], nums[mid] > nums[end] and nums[mid] == nums[end]
     *
     * Time: O(log N), if there are dups, it degrades to O(N)
     * Space: O(1)
     */
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
            } else if(nums[mid] > nums[end]) {
                // rotated
                start = mid + 1
            } else {
                end--
            }
        }
        return result
    }

    /**
     * In this version we make start pointer move to the smallest element (pivot point). Since we do not use temp var
     * we do not let mid get skipped by doing end = mid - 1, instead we do end = mid and in case mid is truly the smallest
     * element start will come to end.
     *
     * Think through all 3 cases nums[mid] < nums[end], nums[mid] > nums[end] and nums[mid] == nums[end]
     */
    fun findMin2(nums: IntArray): Int {
        if(nums.isEmpty()) return -1
        if(nums.size == 1) return nums[0]

        var start = 0
        var end = nums.lastIndex
        while(start < end) {
            val mid = start + (end - start) / 2
            if(nums[mid] < nums[end]) {
                // sorted
                end = mid
            } else if(nums[mid] > nums[end]) {
                // rotated
                start = mid + 1
            } else {
                end--
            }
        }
        return nums[start]
    }
}