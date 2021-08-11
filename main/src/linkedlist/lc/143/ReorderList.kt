package linkedlist.lc.`143`

import linkedlist.lc.ListNode

/**
 *
 */
class ReorderList {

    // Save the Nodes in a map<Index, Node>
    // Use two bounds left and right that close in and use the bounds as key to get
    // Nodes from the list and connect them
    // Space: O(N) Time: O(N)
    fun reorderListNotOpt(head: ListNode?): Unit {
        var map = mutableMapOf<Int, ListNode>()
        var current = head
        var index = 0
        while(current != null) {
            map[index] = current
            index++
            current = current?.next
        }

        var left = 0
        var right = index - 1
        var isReverse = false
        while(left < right) {
            if (!isReverse) {
                map[left]!!.next = map[right]
                left++
            } else {
                map[right]?.next = map[left]
                right--
            }
            isReverse = !isReverse
        }

        if(isReverse) map[left]?.next = null
        else map[right]?.next = null
    }


    /**
     * 1. Cut the list in half, with middle being in the second half. Do this by using fast pointer to go till the last node,
     * slow pointer to reach half and a prev pointer that lags slow and use it to cut the list into two pieces
     * 2. Reverse the second half
     * 3. Merge l1 and l2 to get the output
     */
    fun reorderList(head: ListNode?): Unit {
        if (head?.next == null) return

        var prev: ListNode? = null
        var fast = head
        var slow = head

        // 1. Cut the list in half with mid pointed by slow in second half
        while(fast?.next != null) {
            prev = slow
            slow = slow?.next
            fast = fast.next?.next
        }

        prev?.next = null

        var l1 = head

        // 2. Reverse second half
        var l2 = reverse(slow)
        var temp:ListNode? = null

        // 3. Merge both lists
        while(l1 != null && l2 != null) {
            temp = l1.next
            l1.next = l2
            l1 = temp
            temp = l2.next
            l2.next = l1 ?: l2.next
            l2 = temp
        }
    }


    // Reverse using 3 pointer technique
    fun reverse(head: ListNode?): ListNode? {
        var current = head
        var prev: ListNode? = null
        var temp: ListNode? = null

        while (current != null) {
            temp = current.next
            current.next = prev
            prev = current
            current = temp
        }

        return prev
    }
}