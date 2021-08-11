package trees.lc.`101`

import trees.lc.TreeNode

class SymmetricTree {
    fun isSymmetric(root: TreeNode?): Boolean {
        if(root == null || (root.left == null && root.right == null)) return true

        return helper(root.left, root.right)
    }

    private fun helper(leftVal: TreeNode?, rightVal: TreeNode?): Boolean {
        if((leftVal == null && rightVal != null) || (leftVal != null && rightVal == null))
            return false

        if(leftVal == null && rightVal == null) return true

        if(leftVal!!.`val` != rightVal!!.`val`) return false

        val res1 = helper(leftVal.left, rightVal.right)
        val res2 = helper(rightVal.left, leftVal.right)

        return res1 && res2
    }
}