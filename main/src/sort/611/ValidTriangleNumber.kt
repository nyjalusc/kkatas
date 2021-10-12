package sort.`611`

class ValidTriangleNumber {
    /**
     * Similar to 3 sum
     * Sort the array first. Starting reverse lock one element at a time start processing. Use two pointers l = 0 and
     * r = i - 1, and check if nums[l] + nums[r] > nums[i], if that is the case then it means there are r - l combinations
     * possible, add it to the count. if nums[l] + nums[r] is smaller than increment l
     */
    fun triangleNumber(nums: IntArray): Int {
        if(nums.size < 3) return 0

        nums.sort()
        var count = 0
        for (i in nums.lastIndex downTo 2) {
            var r = i - 1
            var l = 0
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    // This means total r - l combinations are possible, ie. all elements from i to r - 1 will form
                    // valid combination with nums[r]
                    count += r - l
                    r--
                } else l++
            }
        }
        return count
    }
}