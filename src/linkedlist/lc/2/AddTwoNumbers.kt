package linkedlist.lc.`2`

import linkedlist.lc.ListNode

/**
 *
 */
class AddTwoNumbers {
    // Waste little time thinking between modulus (rem) and division operation.
    // eg if given num is 18 and we need to get 1 and 8
    // Division (/) = 1
    // Modulus 18.rem(10) = 8 (remainder after division)
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var p1 = l1
        var p2 = l2
        var carry = 0
        val head = ListNode(-1).apply{ next = null }
        var res = head
        var sum = 0
        while(p1 != null || p2 != null) {
            val val1 = p1?.`val` ?: 0
            val val2 = p2?.`val` ?: 0
            sum = val1 + val2 + carry
            carry = sum/10
            sum = sum.rem(10)
            res.next = ListNode(sum)
            res = res.next!!
            p1 = p1?.next
            p2 = p2?.next
        }

        if (carry != 0) res.next = ListNode(carry) // Don't forget carry, try 9 + 1

        return head.next
    }
}