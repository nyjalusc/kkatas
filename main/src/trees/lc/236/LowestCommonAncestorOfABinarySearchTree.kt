package trees.lc.`236`

import trees.lc.TreeNode

class LowestCommonAncestorOfABinarySearchTree {
    /**
     * Time & Space: O(N)
     */
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root == null) return null
        // Value of current node or parent node.
        val parentVal = root.`val`

        // Value of p
        val pVal = p!!.`val`

        // Value of q;
        val qVal = q!!.`val`

        return if (pVal > parentVal && qVal > parentVal) {
            // If both p and q are greater than parent
            lowestCommonAncestor(root.right, p, q)
        } else if (pVal < parentVal && qVal < parentVal) {
            // If both p and q are lesser than parent
            lowestCommonAncestor(root.left, p, q)
        } else {
            // We have found the split point, i.e. the LCA node.
            root
        }
    }
}