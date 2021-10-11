package trees.lc.`144`

import trees.lc.TreeNode

class LevelOrder_ZigZag_103 {
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()

        if(root == null) return result

        val queue = ArrayDeque<TreeNode>()
        queue.addFirst(root)
        result.add(listOf(root.`val`))
        var leftToRightOrder = false
        while(queue.isNotEmpty()) {
            val currentLevelCount = queue.size
            val nextLevelNodes = mutableListOf<Int>()
            repeat(currentLevelCount) {
                val node = queue.removeLast()
                if(node.left != null) {
                    nextLevelNodes.add(node.left!!.`val`)
                    queue.addFirst(node.left!!)
                }

                if(node.right != null) {
                    nextLevelNodes.add(node.right!!.`val`)
                    queue.addFirst(node.right!!)
                }
            }

            if(nextLevelNodes.isNotEmpty()) {
                if(!leftToRightOrder) result.add(nextLevelNodes.reversed())
                else result.add(nextLevelNodes)
            }

            leftToRightOrder = !leftToRightOrder
        }
        return result
    }
}