package linkedlist.lc.`19`

import linkedlist.lc.ListNode

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */
class RemoveNthEndFromEndOfList {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        // Create a new dummy head
        val newHead = ListNode(-1).apply { next = head }
        var curr = head
        repeat(n) {
            curr = curr?.next
        }

        var prev: ListNode? = newHead

        // When curr reaches last node, prev will be one place before the subject node
        while (curr != null) {
            curr = curr?.next
            prev = prev?.next
        }

        // Made mistakes here earlier when I didn't use a dummy head node
        if (prev == newHead) return head?.next // just move the head pointer
        else prev?.next = prev!!.next?.next

        return newHead.next
    }
}