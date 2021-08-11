package trees.lc.`314`

import trees.lc.TreeNode
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min
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
 * Upsolving, Level Order traversal: Instead of Deque or list we use a map of [Index(position in the final list) to List].
 * This doesn't suffer from the limitation described above as index can be negative because its just a key.
 * And we post process the map to prepare final result. To post process we need to know the bounds of key min and max.
 * This is adjusted every iteration. We need to maintain parity between index position and node that gets added, for this
 * I use a wrapper data class instead of using another queue.
 *
 * Time: O(N)
 * Space: O(N)
 */
class VerticalOrderTraversalTakeTwo {
    private data class NodeIndexed(val index: Int, val node: TreeNode?)

    fun verticalOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()

        val result = mutableListOf<List<Int>>()
        val deque = ArrayDeque<NodeIndexed>()
        // map of index(position) to list with TreeNode values
        val map = HashMap<Int, MutableList<Int>>()

        // Root gets added at position 0, all nodes on left will have -ve index and on the right will have positive index
        deque.add(NodeIndexed(0, root))
        var minIndex = 0
        var maxIndex = 0
        while (deque.isNotEmpty()) {
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
}