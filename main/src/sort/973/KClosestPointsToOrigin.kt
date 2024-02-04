package sort.`973`

import java.util.*

class KClosestPointsToOrigin {
    /**
     * Solution 1: Sort the entire array and
     * Time: O(N Log N)
     * Space: O(1)
     * Advantage: easy to implement
     * Disadvantage: Need to sort the whole input hence inefficient, need to know the entire data set
     */
    fun kClosestSortArray(points: Array<IntArray>, k: Int): Array<IntArray> {
        points.sortBy { it.last() * it.last() + it.first() * it.first() }
        return points.copyOfRange(0, k)
    }

    /**
     * Solution 2: Max heap, closest element at the bottom of heap
     * As you iterate maintain a heap of size k
     * Time: O(N log K)
     * Space: O(K)
     * Advantage: Can work on streaming input, relatively efficient
     * Disadvantage: High time complexity
     */
    fun kClosestMaxHeap(points: Array<IntArray>, k: Int): Array<IntArray> {
        val pq = PriorityQueue<IntArray>(compareByDescending { it.last() * it.last() + it.first() * it.first() })
        for (point in points) {
            pq.add(point)
            if (pq.size > k) pq.poll()
        }
        return pq.toTypedArray()
    }

    /**
     * Solution 3: Quick Select
     * Time: O(N) in average, O(N^2) if you have to run the partition algo for all elements
     * https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.
     */
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        var left = 0
        var right = points.lastIndex
        while (left <= right) {
            val partitionIndex = partition(points, left, right)
            if (partitionIndex == k) break
            if (partitionIndex < k) left = partitionIndex + 1
            else right = partitionIndex - 1
        }

        return points.copyOfRange(0, k)
    }

    private fun partition(points: Array<IntArray>, left: Int, right: Int): Int {
        val pivot = points[right]
        var start = left
        var end = right - 1

        while (start <= end) {
            // Move start forward until a greater element is found
            while (start <= end && compare(points[start], pivot) <= 0) {
                start++
            }

            // Move end backward until a lesser element is found
            while (start <= end && compare(points[end], pivot) > 0) {
                end--
            }

            // Swap elements at start and end if needed
            if (start < end) {
                swap(points, start, end)
            }
        }

        // Finally, swap the pivot with the element at start
        swap(points, start, right)
        return start
    }

    private fun compare(point1: IntArray, point2: IntArray) =
        (point1.first() * point1.first() + point1.last() * point1.last()) -
                (point2.first() * point2.first() + point2.last() * point2.last())


    private fun swap(points: Array<IntArray>, idx1: Int, idx2: Int): Unit {
        val temp = points[idx1]
        points[idx1] = points[idx2]
        points[idx2] = temp
    }

}