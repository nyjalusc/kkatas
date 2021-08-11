package trees.lc.bst.`108`

import linkedlist.lc.ListNode
import trees.lc.TreeNode

/**
 * Solution 1: Convert given list into an Array, then the problem turns into 108.
 * Time: O(N)
 * Space: O(N) extra space for the array
 *
 * Solution 2: Find the middle element of the list by using fast-slow 2 pointer technique. Create root using this
 * ListNode. It logically splits the list into two halves. Left and Right child of current TreeNode will come from the
 * two halves respectively through recursion.
 * Time: O(N Log N)
 * Space: O(log N): IMPORTANT: Not O(N), we are creating a height balanced tree so space will always be bounded to log N
 *
 * Variant 1 (Easy): Use a prevSlow pointer to cut the list. First half will be head -> prevSlow and second half will be
 * slow -> end of list.
 *
 * Variant 2: Use tail as an input param. IMPORTANT: tail node is supposed to be out of bounds. During recusion
 * left child will look like: createBST(head, slow), here slow is the exclusive bound since its already processed
 * right child will look like: createBST(slow.next, tail), slow.next because head is always inclusive and tail is exclusive.
 */
class SortedListToBST_109 {

    // Variant 1
    fun sortedListToBSTEasy(head: ListNode?): TreeNode? {
        if(head == null) return null
        if(head.next == null) return TreeNode(head.`val`)

        var fast = head
        var slow = head
        var prevSlow: ListNode? = null
        while(fast?.next != null) {
            fast = fast.next!!.next
            prevSlow = slow
            slow = slow!!.next
        }

        // Cut the list in two halves
        prevSlow?.next = null
        val root = TreeNode(slow!!.`val`)
        root.left = sortedListToBSTEasy(head)
        root.right = sortedListToBSTEasy(slow.next)
        return root
    }

    // Variant 2
    fun sortedListToBST(head: ListNode?): TreeNode? {
        if(head == null) return null
        return createBST(head, null)
    }

    private fun createBST(head: ListNode?, tail: ListNode?): TreeNode? {
        if(head == tail) return null

        var fast = head
        var slow = head
        while(fast != tail && fast!!.next != tail) {
            fast = fast.next!!.next
            slow = slow!!.next
        }

        val root = TreeNode(slow!!.`val`)
        root.left = createBST(head, slow)
        root.right = createBST(slow.next, tail)
        return root
    }
}