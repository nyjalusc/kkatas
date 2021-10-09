package maps.lc.`1`
import kotlin.math.abs


class ThreeSumClosest {
    /**
     * Runtime: O(N^2)
     * Space: O(N)
     */
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        var diff = Int.MAX_VALUE
        nums.sort()
        var i = 0
        while (i < nums.size && diff != 0) {
            var left = i + 1
            var right = nums.lastIndex
            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]
                if (abs(target - sum) < abs(diff)) {
                    diff = target - sum
                }
                if (sum < target) {
                    ++left
                } else {
                    --right
                }
            }
            ++i
        }
        return target - diff
    }


}