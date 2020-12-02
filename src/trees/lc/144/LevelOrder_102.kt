package trees.lc.`144`

import trees.lc.TreeNode

/**
 *
 */
class LevelOrder_102 {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
            val result = mutableListOf<List<Int>>()
            val queue = ArrayDeque<TreeNode>()

            var current = root
            current?.also { queue.addLast(it) }
            var nodeCountByLevel = 1
            while(!queue.isEmpty()) {
                val nodesAtLevel = mutableListOf<Int>()
                var nextLevelNodes = 0
                repeat(nodeCountByLevel) {
                    current = queue.removeFirst()
                    current?.left?.also {
                        queue.addLast(it)
                        nextLevelNodes++
                    }

                    current?.right?.also {
                        queue.addLast(it)
                        nextLevelNodes++
                    }
                    nodesAtLevel.add(current!!.`val`)
                }
                result.add(nodesAtLevel)
                nodeCountByLevel = nextLevelNodes

            }
            return result
        }
}