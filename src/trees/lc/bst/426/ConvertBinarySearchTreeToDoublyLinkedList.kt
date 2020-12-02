package trees.lc.bst.`426`

import trees.lc.TreeNode


/**
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 * Step 1: Init a dummy head and Implement inorder traversal
 * Step 2: We need to maintain a pointer to the last element of the Linked list we are constructing.
 * This is what we'll be returning in each call. This step can be simplified if usage of class level property
 * is allowed (side-effects). In every call the last node in the result LL is passed as an arg and the updated
 * last node in LL is returned.
 *
 * Time: O(N)
 * Space: O(H)
 */
typealias Node = TreeNode
class ConvertBinarySearchTreeToDoublyLinkedList {
    fun treeToDoublyList(root: Node?): Node? {
        if(root == null) return null
        val head = Node(Int.MIN_VALUE)
        val current: Node? = head
        val lastNode = inOrderBSTToLinkedListConverter(root, current)
        val firstNode = head.right

        // Link first and the last
        firstNode!!.left = lastNode
        lastNode!!.right = firstNode

        return firstNode
    }

    private fun inOrderBSTToLinkedListConverter(root: Node?, current: Node?): Node? {
        if(root == null) return current
        var last = inOrderBSTToLinkedListConverter(root.left, current)
        root.left = last
        last?.right = root
        last = last?.right

        return inOrderBSTToLinkedListConverter(root.right, last)
    }

    fun treeToDoublyListIterative(root: Node?): Node? {
        TODO("Implement Iterative solution")
    }
}