package trees.lc.bst.`450`

import trees.lc.TreeNode

class DeleteNodeinABST {
    /**
     * Case 1: Node being deleted doesn't have children (leaf node) return null to parent
     * Case 2: Has one child, return the child
     * Case 3: Has 2 child, copy inordersuccessor and then delete it from the right subtree
     */
    fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
        if(root == null) return null

        if (key < root.`val`) {
            root.left = deleteNode(root.left, key)
        } else if(key > root.`val`) {
            root.right = deleteNode(root.right, key)
        } else {
            // We have found the node with value key

            // Case 1: Node is a leaf node, to delete return null to its parent
            if (root.left == null && root.right == null) return null

            // Case 2: Node has one child, it should be attached to current node's parent
            // Can be simplified and combined with case 1 but keeping it separate for better understanding
            // without this if block case 3 will get qualified
            if (!(root.left != null && root.right != null)) {
                return if (root.left == null) root.right else root.left
            }

            // Case 3: If node has both childs. Find the inorder successor of this node, ie. leftmost child in the
            // right subtree and copy its value in root. Later delete that node from right subtree
            val inorderSuccessor = findInorderSuccessor(root.right!!)
            root.`val` = inorderSuccessor.`val` // Copied
            root.right = deleteNode(root.right, root.`val`) // Delete copied node from right subtree
        }
        return root
    }

    fun findInorderSuccessor(root: TreeNode): TreeNode {
        var current = root
        while (current.left != null) current = current.left!!
        return current
    }
}