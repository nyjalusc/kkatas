package linkedlist.lc.`160`

import trees.lc.TreeNode


class LowestCommonAncestorOfBinaryTreeIII_1650 {
    /**
     * Finds the lowest common ancestor (LCA) of two nodes in a binary tree.
     *
     * Algorithm: Same as finding intersection point of two lists (#160)
     * 1. Start with two pointers, c1 and c2, initialized to nodes p and q respectively.
     * 2. Move each pointer up one step in the tree (to the parent node) in each iteration.
     * 3. If a pointer reaches the root (null), it continues from the other node (simulating a cycle).
     * 4. The first node where c1 and c2 meet is the LCA.
     *
     * Time Complexity: O(h), where h is the height of the tree. In the worst case, both pointers may traverse the height of the tree.
     *
     * Space Complexity: O(1), as no additional space is used apart from the two pointers.
     */
    fun lowestCommonAncestor(p: Node?, q: Node?): Node? {
        var c1 = p
        var c2 = q

        while (c1 != c2) {
            if (c1 == null) c1 = q else c1 = c1.parent
            if (c2 == null) c2 = p else c2 = c2.parent
        }
        return c1
    }
}

class Node(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
    var parent: Node? = null
}