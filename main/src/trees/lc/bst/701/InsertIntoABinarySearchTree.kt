package trees.lc.bst.`701`

import trees.lc.TreeNode

class InsertIntoABinarySearchTree {
    /**
     * Go to the left if value being inserted is small, otherwise right. Once you reach null insert the node.
     * Don't forget to connect it to the parent.
     * Time: O(N)
     * Space: O(H)
     */
    fun insertIntoBST(root: TreeNode?, `val`: Int): TreeNode? {
        if(root == null) return TreeNode(`val`)

        if(`val` > root.`val`) {
            root.right = insertIntoBST(root.right, `val`)
        } else {
            root.left = insertIntoBST(root.left, `val`)
        }
        return root
    }
}