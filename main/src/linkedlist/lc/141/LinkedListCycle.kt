package linkedlist.lc.`141`

import linkedlist.lc.ListNode

/**
 * https://leetcode.com/problems/linked-list-cycle/
 */

class LinkedListCycle {
    // Two pointer approach
    fun hasCycle(head: ListNode?): Boolean {
        var fast = head
        var slow = head

        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            if (fast == slow && fast != null && slow != null) return true
        }
        return false
    }

    // More efficient solution than two pointer
    fun hasCycle2(head: ListNode?): Boolean {
        val set = HashSet<ListNode>()
        var curr = head
        while (curr != null) {
            if (set.contains(curr)) return true
            set.add(curr)
            curr = curr.next
        }
        return false
    }
}