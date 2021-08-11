package trees.lc.`314`

import trees.lc.TreeNode
import kotlin.math.max
import kotlin.math.min
import kotlin.test.assertEquals

class VerticalOrderTraversal {
    // 314 and 987 can be solved using DFS and BFS, slight modification to 314 is required to make it work for 987

    /**
     * DFS Solution: Create a 2 dimensional data structure to store the values. Here we use Map<Col, Map<Row, List>>
     * to achieve this. Its a List because multiple values can exist at the same (col, row) index.
     * Constraint: if mutliple values are at the same place they need to arranged from left to right, where as in 987
     * they need to be sorted.
     *
     * While post processing, start from the leftmost row and then processing the columnMap, the indexes are rows, they
     * need to be sorted so that we can start merging lists starting from row 0.
     */
    fun verticalOrder(root: TreeNode?): List<List<Int>>? {
        val map: MutableMap<Int, MutableMap<Int, MutableList<Int>>> = mutableMapOf()
        helper(root, map, 0, 0)
        val vertical: MutableList<List<Int>> = mutableListOf()
        for (col in map.keys.sorted()) { // <--- start from the left most column
            val level: MutableList<Int> = mutableListOf()
            for (row in map[col]!!.keys.sorted()) { // <-- start from the first row (0)
                level.addAll(map[col]!![row]!!) // <--- In 987, values need to be sorted
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


    /**
     * DFS (recursive): In my solution, I used a deque instead of a map. The downside of a deque is that it is a list with index starting from 0.
     * In this problem as we traverse (especially left side) the index becomes negative. We start from root at index 0, add
     * it to the list, which then gets added to the deque at position 0. When we traverse its left child, it needs to be added
     * at index rootIndex - 1, meaning -1. To handle this I used ArrayDeque, where I add left child at the first position,
     * now this moves the list of parent by one position to the right. Once left node processiing is done, we pass position
     * rootIndex + 1 to the right subtree, but rootIndex needs to be computed as the left tree processing must have moved it.
     * We do not know by how much because so rootIndex (final position) can only be determined from the result of left node.
     * Processing of right subtree doesn't affect the current node because all the lists will be added to its right side.
     * The solution worked but I had to be extra careful while managing indices. This solution can be simplified by just
     * using a map while traversal and post-processing the map to create result.
     *
     * Upsolving, BFS: Instead of Deque or list we use a map of [Index(position in the final list) to List].
     * This doesn't suffer from the limitation described above as index can be negative because its just a key.
     * And we post process the map to prepare final result. To post process we need to know the bounds of key min and max.
     * This is adjusted every iteration. We need to maintain parity between index position and node that gets added, for this
     * I use a wrapper data class instead of using another queue.
     *
     * Time: O(N)
     * Space: O(N)
     */
    private data class NodeIndexed(val index: Int, val node: TreeNode?)

    fun verticalOrderLevelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()

        val result = mutableListOf<List<Int>>()
        val deque = ArrayDeque<NodeIndexed>()
        // map of index(position) to list with TreeNode values
        val map = HashMap<Int, MutableList<Int>>()

        deque.add(NodeIndexed(0, root))
        var minIndex = 0
        var maxIndex = 0
        while (deque.isNotEmpty()) {
            // To handle multiple values at the same position no need to do anything special as we process left node first, so they are
            // arranged as per the constraint. In 987, we need to sort based on value so this part requires slight modification
            val nodeIndexed = deque.removeFirst()
            val list = map.getOrPut(nodeIndexed.index) { mutableListOf() }
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

        // Populate the result
        for(i in minIndex..maxIndex) result.add(map[i]!!)

        return result
    }

    // Working solution, failing in LC due to kotlin version issues
    /**
    fun verticalOrder2(root: TreeNode?): List<List<Int>> {
        val result = ArrayDeque<MutableList<Int>>()
        verticalOrderHelper2(root, 0, result)
        return result.toList()
    }

    private fun verticalOrderHelper2(root: TreeNode?, possibleIndex: Int, result: ArrayDeque<MutableList<Int>>): Int {
        if(root == null) return possibleIndex

        var rootIndex = possibleIndex
        when {
            possibleIndex < 0 || result.isEmpty() -> {
                val list = mutableListOf(root.`val`)
                result.addFirst(list)
                rootIndex = 0
            }
            possibleIndex > result.lastIndex -> {
                val list = mutableListOf(root.`val`)
                result.addLast(list)
                rootIndex = result.lastIndex
            }
            else -> {
                val list = result[possibleIndex]
                list.add(root.`val`)
            }
        }


        val leftChildIndex = verticalOrderHelper2(root.left, rootIndex - 1, result)
        rootIndex = leftChildIndex + 1
        val rightChildIndex = verticalOrderHelper2(root.right, rootIndex + 1, result)

        return rootIndex
    }
    **/
}

fun main() {
    val solution = VerticalOrderTraversal()
    val tree1 = TreeNode(3).apply {
        left =  TreeNode(9).apply {
            left = TreeNode(15)
            right = TreeNode(7)
        }
        right = TreeNode(20)
    }

    val tree2 = TreeNode(3).apply {
        left =  TreeNode(9)
        right = TreeNode(20).apply {
            left = TreeNode(15)
            right = TreeNode(7)
        }
    }
    println("Tree1 result: ${solution.verticalOrder(tree1)}")
    assertEquals(listOf(listOf(15), listOf(9), listOf(3, 7), listOf(20)), solution.verticalOrder(tree1))
    println("Tree2 result: ${solution.verticalOrder(tree2)}")
    assertEquals(listOf(listOf(9), listOf(3, 15), listOf(20), listOf(7)), solution.verticalOrder(tree2))

    val tree3 = TreeNode(1).apply {
        right = TreeNode(2).apply {
            right = TreeNode(3).apply {
                left = TreeNode(4).apply {
                    left = TreeNode(5)
                }
            }
        }
    }
    println("Tree3 result: ${solution.verticalOrder(tree3)}")
    assertEquals(listOf(listOf(1, 5), listOf(2, 4), listOf(3)), solution.verticalOrder(tree3))

    val tree4 = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(3).apply {
                right = TreeNode(4).apply {
                    right = TreeNode(5)
                }
            }
        }
    }
    println("Tree4 result: ${solution.verticalOrder(tree4)}")
    assertEquals(listOf(listOf(3), listOf(2, 4), listOf(1, 5)), solution.verticalOrder(tree4))

}