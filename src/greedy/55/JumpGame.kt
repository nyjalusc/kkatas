package greedy.`55`

import kotlin.math.max

/**
 *
 */
class JumpGame {
    /**
     * Keep checking what is the farthest you can reach (max), if this point is ever >= lastIndex return true.
     * Make sure the current index never exceeds max (upper limit).
     * Time: O(N)
     * Space: O(1)
     */
    fun canJump(nums: IntArray): Boolean {
        if(nums.size <= 1) return true
        var max = 0
        for (i in 0 until nums.lastIndex) { // Skip the last index
            // If i ever exceeds max it means we are on a unreachable index
            if(i > max) return false
            // Update what is the furthest we can reach after processing the values
            max = max(max, i + nums[i])
            // If max is >= lastIndex it means we can reach it using the values processed so far
            if(max >= nums.lastIndex) return true
        }
        return false
    }
}