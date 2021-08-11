package trees.lc.bst.`108`

import trees.lc.TreeNode

/**
 * Use Binary Array Splitting to adjust the bounds and pick array value to create the node
 * Time: O(N)
 * Space: O(log N) the recusion stack will be bounded because we are creating a balanced BST, it will not degrade to
 */
class SortedArrayToBST {
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return helper(nums, 0, nums.lastIndex)
    }

    private fun helper(nums: IntArray, low: Int, high: Int): TreeNode? {
        if(low > high) return null

        val mid = (low + high) / 2
        val node = TreeNode(nums[mid])
        node.left = helper(nums, low, mid - 1)
        node.right = helper(nums, mid + 1, high)

        return node
    }
}