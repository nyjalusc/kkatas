package sort.`252`

import java.util.PriorityQueue
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class InsertInterval_57 {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {

        // 1. find the new interval insertion point
        val index = intervals.binarySearch(newInterval, compareBy { it[0].compareTo(newInterval[0]) })
        val insertionIndex = if (index < 0) abs(index) - 1 else index

        // 2. insert the interval
        val allIntervals = mutableListOf<IntArray>()
        allIntervals.addAll(intervals.take(insertionIndex))
        allIntervals.add(newInterval)
        allIntervals.addAll(intervals.drop(insertionIndex))

        // 3. Merge intervals
        val pq = PriorityQueue<IntArray>(compareByDescending { it.last() })
        for (interval in allIntervals) {
            if (pq.size == 0) {
                pq.add(interval)
            } else {
                val lastInterval = pq.peek()
                if(interval.first() in lastInterval.first()..lastInterval.last() || interval.last() in lastInterval.first()..lastInterval.last()) {
                    val (start, end) = Pair(min(lastInterval.first(), interval.first()), max(lastInterval.last(), interval.last()))
                    lastInterval[0] = start
                    lastInterval[1] = end
                } else {
                    pq.add(interval)
                }
            }
        }
        val result = pq.toTypedArray()
        result.sortBy { it.first() }
        return result
    }
}

fun main() {
    println(InsertInterval_57().insert(arrayOf(intArrayOf(1, 5)), intArrayOf(1, 7)))
}