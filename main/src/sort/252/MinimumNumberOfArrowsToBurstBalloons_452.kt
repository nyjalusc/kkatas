package sort.`252`

import java.util.*
import kotlin.math.max
import kotlin.math.min

/**
 *
 */
class MinimumNumberOfArrowsToBurstBalloons_452 {
    /**
     * Variation of classic interval problem. Instead of continuing to merge intervals, here we maintain only the
     * intersection of intervals. The intervals can only reduce/shrink in size but never grow otherwise you'll miss some
     * balloons. Draw out in notebook for graphical representation of the problem.
     *
     * Time: O (N Log N)
     * Space: O(N), worst case otherwise O(log N)
     */
    fun findMinArrowShots(points: Array<IntArray>): Int {
        if(points.size <= 1) return points.size

        // Sort the intervals
        points.sortBy{ it.first() }

        // Create a PQ with a comaparator that sorts arrays by the last value in a descending order
        // Max heap, returns the interval with largest ending bound
        val pq = PriorityQueue<IntArray>(compareByDescending{ it.last() })

        points.forEach { interval ->
            if(pq.isEmpty()) {
                pq.add(interval)
            } else {
                val lastMergedInterval = pq.poll()
                // check if the current interval overlaps with the last merged interval
                // Done by checking if either the starting or ending bound of the interval is within the range/bounds
                // (start, end) of the lastMergedInterval
                if(interval.first() in lastMergedInterval.first()..lastMergedInterval.last() ||
                    interval.last() in lastMergedInterval.first()..lastMergedInterval.last()) {
                    // NOTE: THIS IS THE CHNAGE. Take the largest start point and smallest end point to get the interval intersection
                    lastMergedInterval[0] = max(lastMergedInterval.first(), interval.first())
                    lastMergedInterval[1] = min(lastMergedInterval.last(), interval.last())
                } else {
                    pq.add(interval)
                }
                // Do not forget to reinsert the last completed interval polled
                pq.add(lastMergedInterval)
            }
        }
        return pq.size
    }
}