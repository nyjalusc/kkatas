package trees.lc.`199`

import trees.lc.TreeNode

/**
 *
 */
class BinaryTreeRightSideView {
    /**
     * Used level order traversal. Initially I tried modified preorder traversal but it failed for trees
     * where right side is shorter than left side.
     *
     * Time: O(N)
     * Space: O(D), where D = diameter of the tree. In a complete binary tree, there can be a max of N/2
     * nodes in the last level.
     */
    fun rightSideView(root: TreeNode?): List<Int> {
        if(root == null) return emptyList()

        val result = mutableListOf<Int>()
        val queue = ArrayDeque<TreeNode>()
        queue.addLast(root)
        var currentLevelCount = 1
        var nextLevelCount = 0
        while(queue.isNotEmpty()) {
            result.add(queue.first().`val`)
            repeat(currentLevelCount) {
                val current = queue.removeFirst()
                current.right?.also{
                    queue.addLast(it)
                    nextLevelCount++
                }
                current.left?.also{
                    queue.addLast(it)
                    nextLevelCount++
                }
            }
            currentLevelCount = nextLevelCount
            nextLevelCount = 0
        }

        return result
    }
}