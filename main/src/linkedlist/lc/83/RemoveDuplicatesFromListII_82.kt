package linkedlist.lc.`83`

import linkedlist.lc.ListNode

/**
 * 3 pointers: fast, slow and slowest.
 * Using two pointers we can easily remove duplicates, slow will point to the first occurence of
 * duplicating element. But if we want to get rid of all duplicating elements then we need an
 * additional pointer that will lag slow pointer. Hence I use an additional pointer called slowest.
 * Slowest always is always lagging slow by one node.
 * Slowest pointer is used to skip all the repeated values. If we find repeated values
 * between slow and fast, we keep incrementing fast until its false. Then slow = fast
 * and slowest.next = slow.
 *
 * Time: O(N) Space: O(1)
 */
fun deleteDuplicates(head: ListNode?): ListNode? {
    val root = ListNode(-1).apply { next = head }
    var slowest: ListNode = root
    var slow = head
    var fast = head?.next

    while(fast != null) {
        if (slow?.`val` == fast.`val`) {
            while(slow.`val` == fast?.`val`) fast = fast.next
            slow = fast
            slowest.next = slow
        } else {
            slow = slow?.next
        }

        if (slowest.next != slow) slowest = slowest.next!!

        fast = fast?.next
    }

    return root.next
}