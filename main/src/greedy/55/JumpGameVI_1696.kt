package greedy.`55`

import java.util.*

class JumpGameVI_1696 {
    /**
     * We create scoreindex class with a PQ to keep track of maxScore in a window of size K.
     * As we iterate, if the element we are comparing is out of the window then we keep popping elements from PQ until it enters
     * the window. We create a result maxScores[] dp array, result is at the last index
     * Time: O(N Log N)
     * Space: O(N)
     */
    fun maxResult(nums: IntArray, k: Int): Int {
        val maxScores = IntArray(nums.size) { 0 }
        val pq = PriorityQueue<ScoreIndexed>(compareByDescending{ it.score })
        pq.add(ScoreIndexed(nums[0], 0))
        maxScores[0] = nums[0]

        for (i in 1 until nums.size) {
            while(pq.isNotEmpty() && pq.peek().index + k < i) pq.poll()

            maxScores[i] = pq.peek().score + nums[i]
            pq.add(ScoreIndexed(maxScores[i], i))
        }

        return maxScores.last()
    }
}

data class ScoreIndexed(val score: Int, val index: Int)