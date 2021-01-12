package greedy.`55`

import kotlin.math.max

/**
 *
 */
class JumpGameII_45 {

    /**
     * Similar to JumpGame, the difference here is that we create bounds using max. Once current index i touches max,
     * we update max with maxSoFar. maxSoFar keeps track of the furthest we can jump from elements between 0..max.
     * Every time we update max we increment jump counter.
     *
     * Time: O(N)
     * Space: O(1)
     */
    fun jump(nums: IntArray): Int {
        if(nums.size <= 1) return 0
        // Represents upperbound for the current jump
        var max = 0
        // Represents the upperbound for the farthest index you can reach from elements in i..max
        var maxSoFar = 0
        // number of jumps
        var jump = 0
        for (i in 0 until nums.lastIndex) { // Skip the last index
            // Check what is the max you can reach
            maxSoFar = max(maxSoFar, i + nums[i])

            // Update what is the furthest we can reach after processing the values
            if(i >= max) {
                max = maxSoFar
                jump++
            }

            // If max is >= lastIndex it means we can reach it using the values processed so far
            if(max >= nums.lastIndex) return jump
        }
        // Should never reach
        return -1
    }
}