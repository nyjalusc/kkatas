package linkedlist.lc.`234`

import linkedlist.lc.ListNode
import java.util.*

/**
 *
 */
// Implement Using Stack
fun isPalindromeStack(head: ListNode?): Boolean {
    val deque: Deque<ListNode> = ArrayDeque<ListNode>()
    var current = head

    var len = 0
    while (current != null) {
        len++
        current = current.next
    }

    current = head
    for (i in 0 until len / 2) {
        deque.push(current)
        current = current?.next
    }

    if (len.rem(2) != 0) current = current?.next

    while (!deque.isEmpty() && current != null) {
        if (deque.pop().`val` != current.`val`) return false
        else current = current.next
    }

    return true
}

// Time: O(N)
// Space: O(1)
// 1. Using slow fast pointer reach the middle of the list and adjust slow pointer based on odd or even length of the string
// 2. Reverse the rest of the list starting from slow pointer location.
// 3. Compare the two lists.
fun isPalindrome(head: ListNode?): Boolean {
    var fast = head
    var slow = head

    while (fast?.next != null) {
        fast = fast.next?.next
        slow = slow?.next
    }

    if (fast != null) slow = slow?.next

    var right = reverse(slow)

    var left = head
    while (left != null && right != null && left != right) {
        if (left.`val` != right.`val`) return false
        left = left.next
        right = right.next
    }

    return true
}

fun reverse(head: ListNode?): ListNode? {
    var temp: ListNode? = null
    var prev: ListNode? = null
    var current = head

    while (current != null) {
        temp = current.next
        current.next = prev
        prev = current
        current = temp
    }

    return prev
}


fun isPalindromePractice(head: ListNode?): Boolean {
    if (head?.next == null) return true
    var slow = head
    var fast = head
    var splitHead: ListNode? = null
    while (fast?.next != null) {
        fast = fast.next!!.next
        if (fast == null) {
            splitHead = slow!!.next
            slow.next = null
        } else if (fast.next == null) {
            splitHead = slow!!.next!!.next
            slow.next = null
        } else {
            slow = slow!!.next
        }
    }
    
    return compareLists(head, splitHead!!)
}

private fun compareLists(head1: ListNode?, head2: ListNode?): Boolean {
    var p1 = head1
    var p2 = head2
    println("P1: ${p1?.`val`}, P2: ${p2?.`val`}")
    while (p1 != null && p2 != null) {
        if (p1.`val` != p2.`val`) return false
        p1 = p1.next
        p2 = p2.next
    }
    return true
}
