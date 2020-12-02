package trees.lc.`314`

import trees.lc.TreeNode
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.emptyList
import kotlin.collections.forEach
import kotlin.collections.getOrPut
import kotlin.collections.isNotEmpty
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.sorted
import kotlin.math.max
import kotlin.math.min


/**
 *
 */
class BinaryTreeVerticalOrderTraversal_987 {
    // Check https://leetcode.com/problems/binary-tree-vertical-order-traversal/ before reading further

    private data class NodeIndexed(val index: Int, val node: TreeNode?)

    fun verticalTraversal(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()

        val result = mutableListOf<List<Int>>()
        val deque = ArrayDeque<NodeIndexed>()
        // map of index(position) to list with TreeNode values
        val map: MutableMap<Int, MutableList<Int>> = mutableMapOf()

        // Root gets added at position 0, all nodes on left will have -ve index and on the right will have positive index
        deque.add(NodeIndexed(0, root))
        var minIndex = 0
        var maxIndex = 0
        while (deque.isNotEmpty()) {

            val size = deque.size
            val tempMap = mutableMapOf<Int, MutableList<Int>>()
            repeat(size) {                    // <----- Isolating processing of every level and using a temp map so that we can sort the values before adding to the final map
                val nodeIndexed = deque.removeFirst()
                val list: MutableList<Int> = tempMap.getOrPut(nodeIndexed.index) { mutableListOf() }
                list.add(nodeIndexed.node!!.`val`)

                minIndex = min(minIndex, nodeIndexed.index)

                maxIndex = max(maxIndex, nodeIndexed.index)

                // Check if left child exists, since its on left its index will be currentIndex - 1
                nodeIndexed.node.left?.let {
                    deque.add(NodeIndexed(nodeIndexed.index - 1, it))
                }

                // Check if right child exists, since its on right its index will be currentIndex + 1
                nodeIndexed.node.right?.let {
                    deque.add(NodeIndexed(nodeIndexed.index + 1, it))
                }
            }

            tempMap.keys.forEach { // <--- Level processed. Lets process temp Map
                val finalList = map.getOrPut(it) { mutableListOf() }
                finalList.addAll(tempMap[it]!!.sorted())    // Given Constraint: values need to be sorted if they are at same position
            }
        }

        // Populate the result
        for(i in minIndex..maxIndex) result.add(map[i]!!)

        return result
    }


    fun verticalTraversalDFS(root: TreeNode?): List<List<Int>>? {
        val map: MutableMap<Int, MutableMap<Int, MutableList<Int>>> = mutableMapOf()
        helper(root, map, 0, 0)
        val vertical: MutableList<List<Int>> = mutableListOf()
        for (col in map.keys.sorted()) { // <--- start from the left most column
            val level: MutableList<Int> = mutableListOf()
            for (row in map[col]!!.keys.sorted()) { // <-- start from the first row (0)
                level.addAll(map[col]!![row]!!.sorted()) // <--- Constraint: values need to be sorted if they are at same position
            }
            vertical.add(level)
        }
        return vertical
    }

    private fun helper(node: TreeNode?, map: MutableMap<Int, MutableMap<Int, MutableList<Int>>>, col: Int, row: Int) {
        if (node == null) return
        val rowMap = map.getOrPut(col) { mutableMapOf() }
        val rowList = rowMap.getOrPut(row) { mutableListOf() }
        rowList.add(node.`val`)
        helper(node.left, map, col - 1, row + 1)
        helper(node.right, map, col + 1, row + 1)
    }
}