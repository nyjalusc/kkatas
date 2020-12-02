package linkedlist.lc.`206`

import linkedlist.lc.ListNode

/**
 *
 */
class ReverseLinkedLIst {
    // Iterative - 3 pointer technique
    fun reverseListItr(head: ListNode?): ListNode? {
        var curr = head
        var temp: ListNode? = null
        var prev: ListNode? = null

        while(curr != null) {
            temp = curr.next
            curr.next = prev
            prev = curr
            curr = temp
        }

        return prev
    }

    // Recursive
    fun reverseList(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        return helper(head, null)
    }

    private fun helper(curr: ListNode?, prev: ListNode?): ListNode? {
        if (curr == null) return prev

        var temp = curr.next
        curr.next = prev
        return helper(temp, curr)
    }
}