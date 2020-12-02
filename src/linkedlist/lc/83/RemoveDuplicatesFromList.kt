package linkedlist.lc.`83`

import linkedlist.lc.ListNode

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 */
class RemoveDuplicatesFromList {
    // Use slow and fast pointers, use slow pointers to skip and connect next nodes
    // move slow only if fast.val != slow.val
    fun deleteDuplicates(head: ListNode?): ListNode? {
        var fast = head?.next
        var slow = head

        while(fast != null) {
            if (fast.`val` == slow?.`val`) {
                slow.next = fast.next
            } else {
                slow = slow?.next
            }
            fast = fast.next
        }

        return head
    }
}