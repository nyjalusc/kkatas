package linkedlist.lc.`24`

import linkedlist.lc.ListNode

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 *
 * Try examples with odd and even length lists.
 * Using two pointers c1 and c2 adjust the next pointers. c1 shouldn't point to c2.next
 * in fact it should point to c2.next.next, if c2.next.next expression is null point to c2.next (odd length)
 *
 * Time: O(N) Space: O(1)
 */
class SwapNodesInPairs {
    fun swapPairs(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        var c1 = head
        var c2 = head.next
        val newHead = head.next
        var temp: ListNode? = null

        while(c1 != null && c2 != null) {
            temp = c2.next
            c2.next = c1
            c1.next = temp?.next ?: temp
            c1 = temp
            c2 = c1?.next
        }

        return newHead
    }
}