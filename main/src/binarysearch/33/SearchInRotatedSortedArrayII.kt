package binarysearch.`33`

class SearchInRotatedSortedArrayII {
    /**
     * Variation over part 1
     * There are four cases in our binary search implementation
     * 1. target == nums[mid], then we find it
     * 2. nums[start] < nums[mid], then the left part must be sorted;
     * 3. nums[end] > nums[mid], then the right part must be sorted
     * 4. nums[left] == nums[mid], we can not make sure which part is sorted. Since target != nums[mid] == nums[left],
     * we just offset right by left++ because we know the result will be on right side.
     * 5. nums[end] == nums[mid] - Same as 4 but we go on left side.
     */
    fun search(nums: IntArray, target: Int): Boolean {
        var start = 0
        var end = nums.lastIndex

        while(start <= end) {
            val mid = start + (end - start)/2
            if(nums[mid] == target) return true

            if(nums[mid] > nums[start]) {
                // All elements between mid and start including mid and start haven't been rotated
                if(target >= nums[start] && target < nums[mid]) end = mid - 1
                else start = mid + 1
            } else if(nums[mid] < nums[end]) {
                // mid is in rotated part, if thats the case then we know that all the elements
                // between mid and end have been rotated so they nums[mid]..nums[end] are sorted
                if(target > nums[mid] && target <= nums[end]) start = mid + 1
                else end = mid - 1
            } else {
                if(nums[start] == nums[mid]) start++
                if(nums[mid] == nums[end]) end--
            }
        }

        return false
    }
}
