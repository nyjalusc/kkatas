package trees.lc.`112`

import trees.lc.TreeNode

class PathSum {
    fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
        if(root == null) {
            return false
        }

        if(root.left == null &&
            root.right == null &&
            sum - root.`val` == 0
        ) return true

        return hasPathSum(root.left, sum - root.`val`) ||
                hasPathSum(root.right, sum - root.`val`)
    }
}