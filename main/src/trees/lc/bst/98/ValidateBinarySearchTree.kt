package trees.lc.bst.`98`

import trees.lc.TreeNode

/**
 * Preorder Traversal (sort-of): If you cannot fully process current node, check if you can pass current nodes info through
 * recurssive call to get it processed implicitly. So that when you get the result from children you can be assured
 * the current nodes info passed through recurssion was used to calculate their result. And there is no need to further
 * process the result. This same technique was also used in Problem 1026
 *
 * Start with min and max bounds of Long or Int because the root can have any value. The bounds do not really matter
 * for root. But as we decide whether to go in left or right, we adjust the bounds based on current nodes value.
 *
 * Time: O(N)
 * Space: O(N)
 */
class ValidateBinarySearchTree {
    fun isValidBST(root: TreeNode?): Boolean {
        if(root == null) return true

        return checkBST(root, Long.MIN_VALUE, Long.MAX_VALUE)
    }

    private fun checkBST(root: TreeNode?, min: Long, max: Long): Boolean {
        if(root == null) return true

        return if(root.`val` > min && root.`val` < max) {
            checkBST(root.left, min, root.`val`.toLong()) && checkBST(root.right, root.`val`.toLong(), max)
        } else {
            false
        }
    }
}