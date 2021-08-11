package linkedlist.lc.`160`

import linkedlist.lc.ListNode

/**
 * https://leetcode.com/problems/intersection-of-two-linked-lists
 */
class IntersectionOfTwoLinkedLists {
    fun getIntersectionNode(headA: ListNode?, headB:ListNode?):ListNode? {
            var aCurr = headA
            var bCurr = headB

            while (true) {
                if (aCurr == null && bCurr == null) return null

                if (aCurr == bCurr) return aCurr

                if (aCurr == null) aCurr = headB else aCurr = aCurr.next
                if (bCurr == null) bCurr = headA else bCurr = bCurr.next
            }
        }
}