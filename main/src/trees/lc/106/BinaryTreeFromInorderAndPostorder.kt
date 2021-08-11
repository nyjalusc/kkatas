package trees.lc.`106`

import trees.lc.TreeNode

class BinaryTreeFromInorderAndPostorder {
    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        if(inorder.isEmpty() || postorder.isEmpty()) return null

        // create a map<value, index> from inorder
        val map = mutableMapOf<Int, Int>()
        inorder.forEachIndexed {index, value -> map[value] = index}

        return builder(
            inorder, postorder, map,
            inStart = 0, inEnd = inorder.lastIndex,
            postStart = 0, postEnd = postorder.lastIndex
        )
    }

    private fun builder(
        inorder: IntArray,
        postorder: IntArray,
        map: MutableMap<Int, Int>,
        inStart: Int, inEnd: Int,
        postStart: Int, postEnd: Int
    ): TreeNode? {
        if(inStart > inEnd || postStart > postEnd) return null

        val root = TreeNode(postorder[postEnd])
        val rootInorderIndex = map[root.`val`]!!

        // Right subtree
        val rightInStart = rootInorderIndex + 1
        val rightInEnd = inEnd
        val rightSubTreeNodeCount = rightInEnd - rightInStart + 1
        val rightPostStart = postEnd - rightSubTreeNodeCount
        val rightPostEnd = postEnd - 1

        root.right = builder(
            inorder, postorder, map,
            rightInStart, rightInEnd,
            rightPostStart, rightPostEnd
        )

        // Left subtree
        val leftInStart = inStart
        val leftInEnd = rootInorderIndex - 1
        val leftPostEnd = rightPostStart - 1
        // Do not use number of nodes in left tree to deduce this because bounds start from 0..N and in every
        // loop N will keep getting smaller but starting point won't change
        val leftPostStart = postStart

        root.left = builder(
            inorder, postorder, map,
            leftInStart, leftInEnd,
            leftPostStart, leftPostEnd
        )

        return root
    }
}