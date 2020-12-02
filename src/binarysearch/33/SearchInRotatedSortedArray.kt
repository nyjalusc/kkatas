package binarysearch.`33`

/**
 *
 */
class SearchInRotatedSortedArray {
    /**
     * Alternate solution (Java sol in LC): Using modified Binary search find the pivot index. Perform binary search from 0..pivot - 1,
     * if you find result return otherwise perform search in pivot..end. Solution requires two pass.
     * Time: O(log N)
     * Space: O(1)
     */

    /**
     * One Pass solution:
     * Important: To not ignore how rotation works in the problem, elements from the front are pushed to the end
     * of the array. This info can be used to come up with some constraints
     * Step 1: Find where mid falls, is it in unrotated part OR in rotated part. We do this by using the info provided
     * in type of rotation and say that if nums[mid] >= nums[start] it is in unrotated part otherwise it is rotated part.
     * If it is in:
     * Unrotated Part: All the elements between (including) start and mid are sorted
     * Rotated Part: All the elements between (including) mid and end are sorted
     * Step 2: Use these two properties to formulate constraints
     *
     * Time: O(log N)
     * Space: O(1)
     */
    fun search(nums: IntArray, target: Int): Int {
        var start = 0
        var end = nums.lastIndex
        while(start <= end) {
            val mid = start + (end - start)/2
            if(nums[mid] == target) return mid

            // You cannot substitue this with nums[mid] <= nums[end]
            // This is because in this question upon rotation elements from the front are added at the back of the array
            // so if nums[mid] is in unrotated part we can deduce that all the elements between start..end are sorted
            if(nums[mid] >= nums[start]) {
                // All elements between mid and start including mid and start haven't been rotated
                if(target >= nums[start] && target < nums[mid]) end = mid - 1
                else start = mid + 1
            } else {
                // mid is in rotated part, if thats the case then we know that all the elements
                // between mid and end have been rotated so they nums[mid]..nums[end] are sorted
                if(target > nums[mid] && target <= nums[end]) start = mid + 1
                else end = mid - 1
            }
        }

        return -1
    }
}