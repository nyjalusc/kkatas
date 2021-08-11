package sort.`252`

import java.util.*

/**
 *
 */
class MeetingRoomsII_253 {
    /**
     * Time: Sorting O(N Log N), Heap add and poll operations for N elements will be worst case O(N Log N)
     * Total Time: O(N Log N)
     * Space: Worst case O(N)
     */
    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        if(intervals.size <= 1) return intervals.size

        // Sort the intervals
        intervals.sortBy{ it.first() }

        // Create a PQ with a comaparator that sorts arrays by the last value
        val pq = PriorityQueue<IntArray>(compareBy{ it.last() }) //<-- Min heap, returns first completed interval

        intervals.forEach { interval ->
            if(pq.isEmpty()) {
                pq.add(interval)
            } else {
                val firstCompletedInterval = pq.poll()
                // check if the current interval does not overlap with the last interval retrieved
                if(interval.first() >= firstCompletedInterval.last()) {
                    firstCompletedInterval[1] = interval.last()
                } else {
                    pq.add(interval)
                }
                // Do not forget to reinsert the last completed interval polled
                pq.add(firstCompletedInterval)
            }
        }
        return pq.size
    }
}