package trees.lc.`112`

import trees.lc.TreeNode
import kotlin.math.max

/**
 * Post order Traversal: Using a global variable or a property such as result, makes it easy to handle multiple pieces of information.
 * Trick is to indentify what needs to be passed to the parent, essentially only the info that can be used by the parent.
 * Max Path Sum can be one of of the following:
 * 1. Just the current node val
 * 2. Current node val + max(leftPathSum, rightPathSum) <--- This is what we pass to the parent
 * 3. Current node val + leftPathSum + rightPathSum <--- IMPORTANT
 * Time: O(N)
 * Space: O(H), where H is the height of the tree, worst case O(N)
 */
class MaximumPathSum_124 {
    private var result = Int.MIN_VALUE
    fun maxPathSum(root: TreeNode?): Int {
        if(root == null) return 0
        pathSumHelper(root)
        return result
    }

    /**
     *
     */
    private fun pathSumHelper(root: TreeNode?): Int {
        if(root == null) return 0

        val leftPathMaxSum = pathSumHelper(root.left)
        val rightPathMaxSum = pathSumHelper(root.right)

        result = max(result, root.`val`)
        result = max(result, max(leftPathMaxSum, rightPathMaxSum) + root.`val`)
        // Path going from left child, to root, to right child - this info cannot be really used by the parent
        // so we only maintain it in the global variable
        result = max(result, leftPathMaxSum + rightPathMaxSum + root.`val`)

        // Only pass the information that can be used by the parent to form a longer path, this means
        // picking just the current root OR current root + max(leftSum, rightSum)
        return max(max(leftPathMaxSum, rightPathMaxSum) + root.`val`, root.`val`)
    }
}