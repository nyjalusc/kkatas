package linkedlist.lc.`141`

import linkedlist.lc.ListNode

/**
 *
 */
class LinkedListCycleII_142 {
    /*
   	run time O(n)
   	space O(1)
   	 */
    fun detectCycle(head: ListNode?): ListNode? {
        var fast = head
        var slow = head

        while(fast != null) {
            fast = fast.next?.next
            slow = slow?.next

            if (fast == slow) break
        }

        if (fast == null) return null

        var slow2 = head
        while (slow2 != slow) {
            slow = slow?.next
            slow2 = slow2?.next
        }

        return slow
    }
}