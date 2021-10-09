package maps.lc.`1`


class TwoSum {
    /**
     * Runtime: O(N)
     * Space: O(N)
     */
    fun twoSum(nums: IntArray, target: Int): IntArray? {
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            if (map.containsKey(nums[i])) {
                return intArrayOf(map[nums[i]]!!, i)
            }
            map[target - nums[i]] = i
        }

        // return 0 sized array
        return IntArray(0)
    }
}