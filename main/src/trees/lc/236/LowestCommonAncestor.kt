package trees.lc.`236`

import trees.lc.TreeNode

class LowestCommonAncestor {
    // Time: O(N), Space: O(H): H = height of tree, for skewed tree H <= N
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if(root == null) return null

        // Process the current node to see if it is the same as p or q
        if(root.`val` == p!!.`val` || root.`val` == q!!.`val`) return root

        val left = lowestCommonAncestor(root.left, p, q)
        val right = lowestCommonAncestor(root.right, p, q)

        if(left == null && right == null) return null

        // Found LCA
        if(left != null && right != null) return root

        return left ?: right
    }
}