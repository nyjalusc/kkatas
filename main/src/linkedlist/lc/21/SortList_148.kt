package linkedlist.lc.`21`

import linkedlist.lc.ListNode

/**
 * Recursively split into halves until single nodes, start merging as the recurssion unfolds.
 *
 * Space: O(log N), where n is the number of nodes in linked list. Since the problem is recursive, we need additional
 * space to store the recursive call stack. The maximum depth of the recursion tree is log n
 *
 * Time: O(N log N)
 */
class SortList_148 {
    fun sortList(head: ListNode?): ListNode? {
        // base case
        if (head?.next == null) return head

        var slow = head
        var fast = head
        var prev: ListNode? = null

        // Using two pointer strategy mark the midpoint of list.
        // Using the third pointer prev, that lags slow cut the list into two halves
        while(fast?.next != null) {
            prev = slow
            slow = slow?.next
            fast = fast.next!!.next
        }

        // Cut the list
        prev?.next = null

        // Recurssively split both halves
        var l1 = sortList(head)
        var l2 = sortList(slow)

        return merge(l1, l2)
    }

    // Merge two given lists in a sorted manner
    private fun merge(l1: ListNode?, l2: ListNode?): ListNode? {
        var p1 = l1
        var p2 = l2
        var root = ListNode(-1).apply { next = null }
        var current: ListNode? = root
        while(p1 != null && p2 != null) {
            if (p1.`val` <= p2.`val`) {
                current?.next = p1
                p1 = p1.next
            } else {
                current?.next = p2
                p2 = p2.next
            }
            current = current?.next
        }
        if (p1 != null) current?.next = p1
        else current?.next = p2

        return root.next
    }
}