package sort.`252`

import java.util.*
import kotlin.math.max
import kotlin.math.min

/**
 *
 */
class MergeIntervals_56 {
    /**
     * Time: O(N Log N), (for sorting and max-heap operations, its the same)
     * Space: O(N) worst case
     */
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        if(intervals.size <= 1) return intervals

        // Sort the intervals
        intervals.sortBy{ it.first() }

        // Create a PQ with a comaparator that sorts arrays by the last value in a descending order
        // Max heap, returns the interval with largest ending bound
        val pq = PriorityQueue<IntArray>(compareByDescending{ it.last() })

        intervals.forEach { interval ->
            if(pq.isEmpty()) {
                pq.add(interval)
            } else {
                val lastMergedInterval = pq.poll()
                // check if the current interval overlaps with the last merged interval
                // Done by checking if either the starting or ending bound of the interval is within the range/bounds
                // (start, end) of the lastMergedInterval
                if(interval.first() in lastMergedInterval.first()..lastMergedInterval.last() ||
                        interval.last() in lastMergedInterval.first()..lastMergedInterval.last()) {
                    if(interval.last() > lastMergedInterval.last()) {
                        lastMergedInterval[0] = min(lastMergedInterval.first(), interval.first())
                        lastMergedInterval[1] = max(lastMergedInterval.last(), interval.last())
                    }
                } else {
                    pq.add(interval)
                }
                // Do not forget to reinsert the last completed interval polled
                pq.add(lastMergedInterval)
            }
        }
        return pq.toTypedArray()
    }
}