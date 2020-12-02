package heap.`378`

import java.util.*

/**
 * Min-heap: Can be used to find Kth largest element by always maintaining heap of size K
 * Max-heap: Can be used find Kth smallest element by always maintaining heap of size K
 */
class KthSmallestElementInSortedMatrix {
    /**
     * Solution 1: Create a Max-Heap, keep adding all the elements one at a time. Maintain heap of size K
     * at all times this will make sure only K smallest elements will be present in the heap at any given time.
     * The larger values will be removed from heap every time heap size grows beyond k. The element at the top will
     * be the Kth smallest element
     *
     * Time: O(N * log K) - Inserting N elements into the heap while maintaining heap of size k. Delete op in heap is O(1)
     * Space: O(K) - for maintaining heap of size K
     */
    fun kthSmallestMaxHeap(matrix: Array<IntArray>, k: Int): Int {
        val pq = PriorityQueue<Int>(reverseOrder()) // Max heap, largest value at the top
        for (arr in matrix) {
            for (element in arr) {
                pq.add(element)
                if (pq.size > k) {
                    pq.poll()
                }
            }
        }

        return pq.poll()
    }

    private data class Node(val value: Int, val row: Int, val col: Int)

    /**
     * Min-Heap solution
     * Using the min heap we find the smallest element. Once we find the element there is a good likelihood that the next
     * smallest element is on its right side in the same array. To get this information we create a wrapping Node data structure
     * that holds value, row and col info. This strategy is similar to sorting linkedlist. Instead of just using value we also need
     * info to find the next element.
     * Step 1: Create Min Heap of Node type and sorted by its value
     * Step 2: Init Heap by loading first col, these are the smallest values all the arrays.
     * Step 3: Remove one element at a time, it will be minimum node, use it to populate next value in its array. Check bounds
     * before doing so.
     * Step 4: Perform Step 3 K times. Kth element removed from min-heap will be Kth smallest element.
     *
     * Time: O(N * log K) - Inserting N elements into the heap while maintaining heap of size k. Delete op in heap is O(1).
     * Average complexity better than Solution 1
     * Space: O(N) - worst case, average case complexity will be much smaller
     */
    fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {
        val pq = PriorityQueue<Node>(compareBy { it.value }) // Min heap, smallest value at the top
        // Initialize the heap, by loading smallest elements from each array
        // This will be used to navigate the matrix based on the smallest element found from the heap
        for (row in matrix.indices) {
            pq.add(Node(matrix[row][0], row, 0))
        }

        var count = 0
        var minNode: Node? = null
        while (count != k) {
            minNode = pq.poll()
            count++
            if(minNode.col >= matrix[minNode.row].lastIndex) continue
            pq.add(Node(matrix[minNode.row][minNode.col + 1], minNode.row, minNode.col + 1))
        }

        return minNode!!.value
    }

}