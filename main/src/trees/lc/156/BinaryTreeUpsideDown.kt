package trees.lc.`156`

import trees.lc.TreeNode

class BinaryTreeUpsideDown {
    fun upsideDownBinaryTree(root: TreeNode?): TreeNode? {
        if(root == null || (root.left == null && root.right == null)) return root

        val newRoot = upsideDownBinaryTree(root.left)

        root.left?.left = root.right
        root.left?.right = root

        root.left = null
        root.right = null

        return newRoot
    }
}