package graphs.`1197`

import java.util.*


class MinimumKnightMoves {
    /**
     * Solution 1: BFS
     * Solution below is not optimal, generate the points using an offset array of 8 elements
     * Iterate over it and sum it with the current position to generate 8 next positions. Set is inefficient, use 2d boolean array
     * of max size (605)
     * Time: O(max(x, y) ^ 2) - Area of the square
     * Space: O(max(x, y) ^ 2) - Area of the square
     *
     * Solution 2
     * Bi-directional BFS
     * Do two separate BFS one from target and one from destination. Instead of a set use a map to keep track of distance
     * of a point from origin and target. Each BFS will need its own queue and its own hashmap. We process one element
     * from a queue at any given moment. And then we see if the element we are currently processing exists in the targetmap,
     * if yes then the result = distance of current from origin + distance of current from target
     * Time: O(max(x, y) ^ 2) - Area of the square
     * Space: O(max(x, y) ^ 2) - Area of the square
     */
    fun minKnightMoves(x: Int, y: Int): Int {
// the offsets in the eight directions
        // the offsets in the eight directions
        val offsets = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(2, 1),
            intArrayOf(2, -1),
            intArrayOf(1, -2),
            intArrayOf(-1, -2),
            intArrayOf(-2, -1),
            intArrayOf(-2, 1),
            intArrayOf(-1, 2)
        )

        // - Rather than using the inefficient HashSet, we use the bitmap
        //     otherwise we would run out of time for the test cases.
        // - We create a bitmap that is sufficient to cover all the possible
        //     inputs, according to the description of the problem.

        // - Rather than using the inefficient HashSet, we use the bitmap
        //     otherwise we would run out of time for the test cases.
        // - We create a bitmap that is sufficient to cover all the possible
        //     inputs, according to the description of the problem.
        val visited = Array(605) { BooleanArray(605) }

        val queue: Deque<IntArray> = LinkedList()
        queue.addLast(intArrayOf(0, 0))
        var steps = 0

        while (queue.size > 0) {
            val currLevelSize = queue.size
            // iterate through the current level
            for (i in 0 until currLevelSize) {
                val curr = queue.removeFirst()
                if (curr[0] == x && curr[1] == y) {
                    return steps
                }
                for (offset in offsets) {
                    val next = intArrayOf(curr[0] + offset[0], curr[1] + offset[1])
                    // align the coordinate to the bitmap
                    if (!visited[next[0] + 302][next[1] + 302]) {
                        visited[next[0] + 302][next[1] + 302] = true
                        queue.addLast(next)
                    }
                }
            }
            steps++
        }
        // move on to the next level
        return steps
    }


}