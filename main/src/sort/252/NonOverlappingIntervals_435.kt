package sort.`252`

import java.util.*
import kotlin.math.abs

/**
 *
 */
class NonOverlappingIntervals_435 {
    /**
     * Merge Intervals variation:
     * Sort by starting point and use a PQ for processing (Max heap based on end point)
     * Case 1: If there is a complete overalap between the lastDisjointInterval polled from PQ and the current interval
     * keep the smaller one. This will increase the chance of having fewer overlaps in the future
     * Case 2: Partial overlap. Keep the interval from PQ and discard the current one. This is because the intervals are
     * sorted as we move along there is a greater chance of future intervals to overlap with the current one instead of the
     * one we got from PQ. (Greedy)
     * Case 3: Disjoint, add the current interval to PQ
     *
     * Time: O(N Log N)
     * Space: O(N) worst, average O(log N) [ This can be brought down by using pointers instead of a heap ]
     */
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        if(intervals.size <= 1) return 0

        intervals.sortBy { it.first() }

        val pq = PriorityQueue<IntArray>(compareByDescending { it.last() })

        intervals.forEach { interval ->
            if(pq.isEmpty()) {
                pq.add(interval)
            } else {
                var lastDisjointInterval = pq.poll()
                if(interval.first() in lastDisjointInterval.first()..(lastDisjointInterval.last()) &&
                    interval.last() in lastDisjointInterval.first()..(lastDisjointInterval.last())) {
                    // Case 1: Complete overlap, keep the smaller interval
                    lastDisjointInterval = if(abs(interval.last() - interval.first()) < abs(lastDisjointInterval.last() - lastDisjointInterval.first())) {
                        interval
                    } else {
                        lastDisjointInterval
                    }
                } else if(interval.first() < lastDisjointInterval.last()) {
                    // Case 2: Partial overlap
                    // Don't do anything we keep what we have and discard the later interval
                    // This was challenging to visualize so draw an example on paper
                } else {
                    // Case 3: Disjoint
                    pq.add(interval)
                }
                pq.add(lastDisjointInterval)
            }
        }

        return intervals.size - pq.size
    }

}