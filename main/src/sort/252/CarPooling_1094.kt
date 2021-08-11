package sort.`252`

import java.util.*

/**
 * Time: O (N Log N)
 * Space: O(N) worst case
 */
class CarPooling_1094 {
    fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
        trips.sortBy { it[1] }

        val pq = PriorityQueue<IntArray>(compareBy { it.last() })
        var availableCapacity = capacity
        trips.forEach { trip ->
            // It is possible that multiple trips ended by the time you process current trip, hence a while loop is needed
            while(pq.isNotEmpty() && pq.peek().last() <= trip[1]) {
                val tripEndingNext = pq.poll()
                availableCapacity += tripEndingNext.first()

            }
            pq.add(trip)
            availableCapacity -= trip.first()
            if(availableCapacity < 0) return false
        }

        return true
    }

    /**
     * Clever solution keeping track of change time points and the seat allocation.
     * We use treeMap to preserve the sorted order, or we could also sort the input first and then use linkedHashmap.
     */
    fun carPooling2(trips: Array<IntArray>, capacity: Int): Boolean {
        var availableCapacity = capacity
        val map = TreeMap<Int, Int>()
        // Note multiple trips can start and end from the same point
        trips.forEach { trip ->
            map[trip[1]] = map.getOrElse(trip[1]) { 0 } + trip[0]
            map[trip[2]] = map.getOrElse(trip[2]) { 0 } - trip[0]
        }

        map.values.forEach { seatsRequired ->
            availableCapacity -= seatsRequired
            if(availableCapacity < 0) return false
        }
        return true
    }
}