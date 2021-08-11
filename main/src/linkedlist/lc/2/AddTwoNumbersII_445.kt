package linkedlist.lc.`2`

import linkedlist.lc.ListNode

/**
 *
 */
class AddTwoNumbersII_445 {
    // Waste little time thinking between modulus (rem) and division operation.
    // eg if given num is 18 and we need to get 1 and 8
    // Division (/) = 1
    // Modulus 18.rem(10) = 8 (remainder after division)
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

        val op1 = ArrayDeque<Int>()
        val op2 = ArrayDeque<Int>()

        var ptr = l1
        while(ptr != null) {
            op1.addFirst(ptr.`val`)
            ptr = ptr.next
        }

        ptr = l2
        while(ptr != null) {
            op2.addFirst(ptr.`val`)
            ptr = ptr.next
        }

        val root = ListNode(-1)
        var current: ListNode? = root
        var carry = 0

        while(!op1.isEmpty() || !op2.isEmpty()) {
            val x = op1.removeFirstOrNull() ?: 0
            val y = op2.removeFirstOrNull() ?: 0
            val sum = x + y + carry

            carry = sum / 10
            val value = sum.rem(10)
            current!!.next = ListNode(value)
            current = current!!.next
        }

        if(carry > 0) current!!.next = ListNode(carry)

        return root.next
    }
}