package linkedlist.lc.`206`

import linkedlist.lc.ListNode

/**
 *
 */
class ReverseLinkedListII {

    // Using an anchor node (dummy head) made it easier
    // Trick is to keep track of prev pointer to link the beginning and use similar ptr pointer
    // in reverse() to link the end.
    // Time wasted not dry-running examples that reversed the whole list.
    fun reverseBetween(head: ListNode?, m: Int, n: Int): ListNode? {
        if (head?.next == null || n == 1) return head
        var prev: ListNode = ListNode(-1).apply { next = head }
        val anchor = prev
        repeat(m - 1) {
            prev = prev.next!!
        }

        prev.next = reverse(prev.next, m, n)
        return anchor.next
    }

    private fun reverse(curr: ListNode?, m: Int, n: Int): ListNode? {
        var ptr = curr
        var temp: ListNode? = null
        var prev: ListNode? = null
        repeat(n - m + 1) {// Wasted time here, it was originally n, that was wrong
            temp = ptr!!.next
            ptr!!.next = prev
            prev = ptr
            ptr = temp
        }
        curr?.next = ptr
        return prev
    }
}