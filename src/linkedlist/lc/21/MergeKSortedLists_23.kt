package linkedlist.lc.`21`

import linkedlist.lc.ListNode
import java.util.*

/**
 * Start with inserting heads of all K lists in Priority Queue. Next, extract one node at a time
 * from the PQ and attach it to the result list pointer. PQ will be used to sort the value based
 * on ascending comparator.
 *
 * Time complexity : O(N log k) where k is the number of linked lists.
 * The comparison cost will be reduced to O(log k) for every pop and insertion to priority queue.
 * But finding the node with the smallest value just costs O(1) time.
 * There are N nodes in the final linked list.
 *
 * Space complexity : O(n) Creating a new linked list costs O(n) space. O(k) The code above present
 * applies in-place method which cost O(1) space. And the priority queue (often implemented with heaps)
 * costs O(k) space (it's far less than N in most situations).
 */
class MergeKSortedLists_23 {

    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val root = ListNode(-1)
        var current: ListNode? = root
        val queue = PriorityQueue<ListNode>(compareBy { it.`val` })

        lists.forEach {
            it?.let{ queue.add(it) }
        }

        while(!queue.isEmpty()) {
            val node: ListNode? = queue.poll()
            current?.next = node
            current = node

            node?.next?.let { queue.add(it) }
        }

        return root.next
    }
}