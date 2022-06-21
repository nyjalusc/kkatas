package linkedlist.lc.`141`

import linkedlist.lc.ListNode

/**
 * https://leetcode.com/problems/linked-list-cycle/
 */

class LinkedListCycle {
    /**
     * Two pointer approach
     * Time: O(N), Space: O(1)
     */
    fun hasCycle(head: ListNode?): Boolean {
        if(head == null) return false
        var fast = head
        var slow = head

        while(fast != null && fast.next != null) {
            fast = fast.next?.next
            slow = slow!!.next
            if(fast == slow) return true
        }
        return false
    }

    /**
     * If you want to avoid repetition of node processing you can use memory in the form of a set
     * Check if the current node is in a set of visited nodes, if not add the current node and move to next
     */
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