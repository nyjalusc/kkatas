package trees.lc.bst.`108`

import trees.lc.TreeNode

/**
 * Solution:
 * Step 1: BST -> Sorted List using inorder traversal
 * Step 2: SortedList to BST using start and end index pointers that recursively split the list(array) Problem 108
 * Time: O(N)
 * Space:
 *  BSTToArray(): O(H) + O(N) for list, O(H) can degrade to O(N)
 *  sortedListToBST(): O(log N) for the recusion stack, now it could've been O(N) but since we are creating a balanced
 *  tree it'll be bounded to O(log N)
 */
class BalanceBST_1382 {
    fun balanceBST(root: TreeNode?): TreeNode? {
        if(root == null) return null

        val list = mutableListOf<Int>()

        // Create sorted list from BST
        BSTToArray(root, list)

        // Create BST from sorted List: Problem 108
        return sortedListToBST(list, 0, list.lastIndex)
    }

    private fun sortedListToBST(list: MutableList<Int>, start: Int, end: Int): TreeNode? {
        if(start > end) return null

        val mid = (start + end) / 2
        val root = TreeNode(list[mid])
        root.left = sortedListToBST(list, start, mid - 1)
        root.right = sortedListToBST(list, mid + 1, end)

        return root
    }

    // Using inorder traversal
    private fun BSTToArray(root: TreeNode?, list: MutableList<Int>) {
        if(root == null) return

        BSTToArray(root.left, list)
        list.add(root.`val`)
        BSTToArray(root.right, list)
    }
}