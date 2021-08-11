package trees.lc.`543`

import trees.lc.TreeNode
import kotlin.math.max

/**
 *
 */
class DiameterOfBinaryTree {
    /**
     * SOLUTION WITH GLOBAL
     */
    var diameter = 0
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        updateDiameter(root)
        return diameter
    }

    private fun updateDiameter(root: TreeNode?): Int {
        if(root == null) return 0

        val leftTreeHeight = updateDiameter(root.left)
        val rightTreeHeight = updateDiameter(root.right)

        // Check if the diameter path goes through the current node do we have a better result
        diameter = max(diameter, leftTreeHeight + rightTreeHeight)
        return max(leftTreeHeight, rightTreeHeight) + 1
    }

    /**
     * SOLUTION WITHOUT GLOBAL
     * Check comments for explanation
     * Time: O(N)
     * Space: O(H), where H = height of the tree, upper bound for H will be N in case of a skewed tree
     *
     * If this impl is difficult checkout java impl in LC
     */
    fun diameterOfBinaryTreeWithoutGlobal(root: TreeNode?): Int {
        if(root == null) return 0
        val(_, diameter) = helper(root)
        return diameter
    }

    private fun helper(root: TreeNode?): Result {
        if(root == null) return Result(0, 0)

        // post-order traversal, we need info from children to
        // process current node
        var (left, leftTreeDiameter) = helper(root.left)
        var (right, rightTreeDiameter) = helper(root.right)

        // Include the root in
        root.left?.also{ left++ }
        root.right?.also{ right++ }

        // There are 2 possibilities:
        // 1. Diameter path includes left and right childs of root and goes through it
        //  1a. Current root is in the path of diameter, in that case diameter will be left + right
        //  1b. One of the child subtrees already contain the result, so we pick the one that is larger. And then we also
        //    compute if we have a diameter path through current node do we get a better result? If yes take that.
        // 2. Only one of left or right child + root will be part of the diameter path, meaning this could be a partial result
        // We return result containing values for both steps to let the parent compute the result.

        val diameterSoFar = max(max(leftTreeDiameter, rightTreeDiameter), left + right)
        return Result(max(left, right), diameterSoFar)
    }

    private data class Result(val maxChildCount: Int, val pathWithRoot: Int)
}