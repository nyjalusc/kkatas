package arrays.lc.`169`

import kotlin.math.ceil

class MajorityElement {

    /**
     * Time: O(N), terminate as soon we get the result, no need to process all inputs
     * Space: O(N)
     */
    fun majorityElement(nums: IntArray): Int {
        if(nums.isEmpty()) return -1

        val map = HashMap<Int, Int>()
        val maxElementCount = ceil(nums.size/2.0).toInt()
        for (i in nums.indices) {
            map[nums[i]] = map.getOrElse(nums[i]) { 0 } + 1
            if(map[nums[i]]!! >= maxElementCount) return nums[i]
        }
        return -1
    }

    /**
     * Boyer-Moore counting algorithm
     */
    fun majorityElementBoyerMoore(nums: IntArray): Int {
        if(nums.isEmpty()) return -1
        var count = 1
        var majorityElement = nums[0]
        for (i in 1 until nums.size) {
            if(nums[i] == majorityElement) count++
            else {
                count--
                if(count == 0) {
                    majorityElement = nums[i]
                    count++
                }
            }
        }
        return majorityElement
    }


}