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
     * Solution 3: Quick Select (Buggy)
     * Checkout Problem 215 to learn about Quick select algorithm
     * Time: O(N) in average, O(N^2) if you have to run the partition algo for all elements
     * https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.
     */
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        var left = 0
        var right = points.lastIndex
        val random = Random(0) // instead of random, you can always select the last element
        while (left <= right) {
            val randPivotIndex = random.nextInt(right - left + 1) + left
            val partitionIndex = partition(points, left, right, randPivotIndex)
            if(partitionIndex == k - 1) return points.copyOfRange(0, k)

            if(partitionIndex < k - 1) left = partitionIndex + 1
            else right = partitionIndex - 1
        }
        return emptyArray()
    }

    private fun partition(points: Array<IntArray>, left: Int, right: Int, pivotIndex: Int): Int {
        val pivotValue = points[pivotIndex]
        swap(points, pivotIndex, right)

        var i = left
        for (j in left until right) {
            if (compare(points[j], points[right]) > 0) {
                swap(points, i++, j)
            }
        }
        swap(points, i, right)
        return i
    }

    /**
     * If point1 and point2 are the same then 0 otherwise
     * < 0 if point1 is closer to the origin, else > 0
     */
    private fun compare(point1: IntArray, point2: IntArray) =
        (point1.first() * point1.first() + point1.last() + point1.last()) -
                (point2.first() * point2.first() + point2.last() + point2.last())

    private fun swap(points: Array<IntArray>, index1: Int, index2: Int) {
        val temp = points[index1]
        points[index2] = points[index1]
        points[index1] = temp
    }

}