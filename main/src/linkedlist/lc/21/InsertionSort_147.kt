package linkedlist.lc.`21`

import linkedlist.lc.ListNode

/**
 *
 */
/**
 * Cut one node at a time. Iterate using prev pointer to find the location of insertion.
 * Adjust the pointers using prev and nextPtr. Result list will be created using new root.
 */
class InsertionSort_147 {
    fun insertionSortList(head: ListNode?): ListNode? {
        val root = ListNode(-1)

        var prev: ListNode = root
        root.next = head
        var current = head
        var nextPtr: ListNode? = null

        while(current != null) {
            nextPtr = current.next

            while(prev.next != current) {
                prev = prev.next!!
            }

            prev.next = nextPtr
            current.next = null
            prev = root

            while(prev.next != null && prev.next!!.`val` <= current.`val`) {
                prev = prev.next!!
            }

            current.next = prev.next
            prev.next = current
            current = nextPtr
        }

        return root.next
    }
}