package linkedlist.lc.`328`

import linkedlist.lc.ListNode

/**
 *
 */
fun oddEvenList(head: ListNode?): ListNode? {
    var odd = head
    var evenStart = head?.next
    var even = evenStart
    var current = evenStart?.next
    var isOdd = true

    while(current != null) {
        if (isOdd) {
            odd?.next = current
            odd = odd?.next
        } else {
            even?.next = current
            even = even?.next
        }
        current = current?.next
        isOdd = !isOdd
    }

    odd?.next = evenStart
    even?.next = null // Made a mistake here, forgot to cut the end of even list

    return head
}