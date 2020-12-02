package linkedlist.lc.`24`

import linkedlist.lc.ListNode

/**
 *
 */
class ReverseNodesInKGroups {

    /**
     * Similar to swap nodes in pairs solution. Use prevStart and prevEnd to mark the start and end of sub linked lists
     * that are already reversed. Use these pointers to connect the next sub linked list. Don't forget about the newHead.
     * Use a next pointer for resuming processing once the current sub linked list gets disconnected.
     *
     * Time: O(1) Space: O(1)
     */
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        var newHead: ListNode? = null

        var start = head
        var end = head
        var prevEnd: ListNode? = null
        var prevStart: ListNode? = null
        var next: ListNode? = null

        while (end != null) {

            repeat(k - 1) {
                end = end?.next
            }

            if (end != null) {
                next = end?.next
                prevStart = reverse(start, k)
                if (newHead == null) newHead = prevStart
                if (prevEnd?.next != null) prevEnd!!.next = prevStart
                prevEnd = start
            } else {
                next = null
                prevEnd!!.next = start
            }

            end = next
            start = next
        }

        return newHead
    }

    // Reverse N nodes
    private fun reverse(start: ListNode?, n: Int): ListNode? {
        var prev: ListNode? = null
        var current = start
        var temp: ListNode? = null

        repeat(n) {
            temp = current?.next
            current?.next = prev
            prev = current
            current = temp
        }

        return prev
    }
}