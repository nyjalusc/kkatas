package linkedlist.lc.`203`

import linkedlist.lc.ListNode

/**
 * https://leetcode.com/problems/remove-linked-list-elements/
 */
fun removeElements(head: ListNode?, `val`: Int): ListNode? {
    val root = ListNode(-1).apply{ next = head }
    var prev: ListNode? = root
    var current = head

    // Note: There can be multiple `val` nodes we want to delete,
    // do not increment prev unless we have verified that all such consecutive
    // nodes are deleted.
    while(current != null) {
        if (current.`val` == `val`) prev!!.next = current.next
        else prev = prev?.next
        current = current.next
    }
    return root.next
}