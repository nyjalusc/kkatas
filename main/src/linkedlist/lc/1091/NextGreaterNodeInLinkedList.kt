package linkedlist.lc.`1091`

import linkedlist.lc.ListNode

/**
 *
 */
class NextGreaterNodeInLinkedList {
    fun nextLargerNodes(head: ListNode?): IntArray {
        // Only save indexes in the deque
        // Mistake: I was relying on deducing index using a var but it failed for examples such as
        // 9 6 7 6 7 9
        val deque = ArrayDeque<Int>()

        // Convert linked list to ArrayList that way we can get fast access to nodes using the index
        // We also get size of the list which can be used to init the final result IntArray
        var current = head
        val list = ArrayList<ListNode>()
        while(current != null) {
            list.add(current)
            current = current.next
        }

        val result = IntArray(list.size) {0}

        // Update all result[index] with value node.`val` for which node.`val` > list.get(index).`val
        // Keep popping indies from the stack until this condition is true. Stack will always contain
        // indices of nodes that need to be processed.The ones for which we found the the closest(next)
        // large value is already popped.
        list.forEachIndexed{ index, node ->
            while(!deque.isEmpty() && node.`val` > list[deque.first()].`val`) {
                result[deque.removeFirst()] = node.`val`
            }
            deque.addFirst(index)
        }
        return result
    }
}