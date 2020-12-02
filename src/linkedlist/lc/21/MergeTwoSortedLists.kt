package linkedlist.lc.`21`

import linkedlist.lc.ListNode

/**
 *
 */
class MergeTwoSortedLists {
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        var root = ListNode(-1)
        var current = root
        var p1 = l1
        var p2 = l2

        while(p1 != null && p2 != null) {
            if (p1.`val` <= p2.`val`) {
                current.next = p1
                p1 = p1.next
            } else {
                current.next = p2
                p2 = p2.next
            }
            current = current.next!!
        }

        if (p1 == null) current.next = p2
        else current.next = p1

        return root.next
    }
}