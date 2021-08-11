package trees.lc.`106`

import trees.lc.TreeNode

class BinaryTreeFromInorderAndPreorder_105 {
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        if(inorder.isEmpty() || preorder.isEmpty()) return null

        // create a map<value, index> from inorder
        val map = mutableMapOf<Int, Int>()
        inorder.forEachIndexed {index, value -> map[value] = index}

        return builder(
            inorder, preorder, map,
            inStart = 0, inEnd = inorder.lastIndex,
            preStart = 0, preEnd = preorder.lastIndex
        )
    }

    private fun builder(
        inorder: IntArray,
        preorder: IntArray,
        map: MutableMap<Int, Int>,
        inStart: Int, inEnd: Int,
        preStart: Int, preEnd: Int
    ): TreeNode? {
        if(inStart > inEnd || preStart > preEnd) return null

        val root = TreeNode(preorder[preStart])
        val rootInorderIndex = map[root.`val`]!!

        // Left subtree
        val leftInStart = inStart
        val leftInEnd = rootInorderIndex - 1
        val leftPreStart = preStart + 1
        val leftPreEnd =  leftPreStart + (leftInEnd - leftInStart)

        root.left = builder(
            inorder, preorder, map,
            leftInStart, leftInEnd,
            leftPreStart, leftPreEnd
        )

        // Right subtree
        val rightInStart = rootInorderIndex + 1
        val rightInEnd = inEnd
        val rightPreStart = leftPreEnd + 1
        val rightPreEnd = preEnd

        root.right = builder(
            inorder, preorder, map,
            rightInStart, rightInEnd,
            rightPreStart, rightPreEnd
        )

        return root
    }
}