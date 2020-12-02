package trees.lc.`112`

import trees.lc.TreeNode

/**
 * Time: O(N)
 * Space: O(H), where H = Height of the tree
 */
class SumRootToLeafNumbers_129 {
    fun sumNumbers(root: TreeNode?): Int {
        return sumNumbersHelper(root, 0)
    }

    private fun sumNumbersHelper(root: TreeNode?, sum: Int): Int {
        if(root == null) return 0

        // if leaf node return
        if(root.left == null && root.right == null) return sum * 10 + root.`val`

        val leftSum = sumNumbersHelper(root.left, sum * 10 + root.`val`)
        val rightSum = sumNumbersHelper(root.right, sum * 10 + root.`val`)

        // Non-leaf nodes will return sum of results from left and right subtrees
        return leftSum + rightSum
    }
}