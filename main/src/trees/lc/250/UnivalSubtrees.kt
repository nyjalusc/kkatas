package trees.lc.`250`

import trees.lc.TreeNode

/**
 *
 */
class UnivalSubtrees {
    fun countUnivalSubtrees(root: TreeNode?): Int {
        return univalHelper(root).second
    }

    // Post order traversal
    private fun univalHelper(root: TreeNode?): Pair<Boolean, Int> {
        if(root == null) return Pair(true, 0)

        val leftTreeRes = univalHelper(root.left)
        val rightTreeRes = univalHelper(root.right)

        // Check if current root represents a unival subtree
        var isCurrentRootUnival = leftTreeRes.first && rightTreeRes.first

        root.left?.also {
            isCurrentRootUnival = isCurrentRootUnival && it.`val` == root.`val`
        }

        root.right?.also {
            isCurrentRootUnival = isCurrentRootUnival && it.`val` == root.`val`
        }

        // Calculate count
        var count = leftTreeRes.second + rightTreeRes.second
        count += if(isCurrentRootUnival) 1 else 0

        return Pair(isCurrentRootUnival, count)
    }
}