package trees.lc.bst.`938`

import trees.lc.TreeNode

/**
 * Classic Post Order traversal problem. Get sum from left + right + if current node val in range then add its value else 0
 * Time: O(N)
 * Space: O(N)
 */
class RangeSumOfBST {
    fun rangeSumBST(root: TreeNode?, L: Int, R: Int): Int {
            if(root == null) return 0
            return rangeSumHelper(root, L, R, 0)
        }

        private fun rangeSumHelper(root: TreeNode?, L: Int, R: Int, sum: Int): Int {
            if(root == null) return sum

            val leftSum = rangeSumHelper(root.left, L, R, sum)
            val rightSum = rangeSumHelper(root.right, L, R, sum)

            val childrenSum = leftSum + rightSum

            var result = if (root.`val` in L..R) root.`val` else 0
            result += childrenSum

            return result
        }
}