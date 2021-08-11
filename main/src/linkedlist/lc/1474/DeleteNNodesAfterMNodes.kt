package linkedlist.lc.`1474`

import linkedlist.lc.ListNode

/**
 *
 *

[1,2,3,4,5,6,7,8,9,10,11]
3
1

Out:

 Ex:
[1,2,3,5,6,7,9,10,11]
 */
fun deleteNodes(head: ListNode?, m: Int, n: Int): ListNode? {
    var current = head
    var prev = ListNode(-1).apply { next = head }

    while (current != null) {
        for (i in 0 until m) {
            current = current?.next
            if (current != null) prev = prev.next!!
        }

        repeat(n - 1) {
            current = current?.next
        }
        if (current != null) prev.next = current!!.next
        current = current?.next
    }
    return head
}
