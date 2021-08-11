package arrays.lc.`250`

/**
 *
 */

object Unival {

    data class TreeNode(val value: Int = 0, var left: TreeNode? = null, var right: TreeNode? = null)

    // Use an extra class to wrap the original TreeNode class because we need to preserve the isUnival info for each node.
    data class NodeInfo (var node: TreeNode?, var isUnival: Boolean = false)

    fun countUnivalSubtrees(root: TreeNode?): Int {
        if (root == null) return 0
        val nodeInfo = NodeInfo(root)
        return countUnival(root, nodeInfo)
    }

    // Bottom up (postOrder) style approach.
    // Once we reach the leaf node, isUnival information for each parent node is set as the recursion unfolds.
    // Simultaneously we also compute the count and return.
    // Unival count at current node is leftSubtree count + rightSubtree count.
    // + 1 if the current node itself is representing a unival subtree.
    private fun countUnival(root: TreeNode?, nodeInfo: NodeInfo): Int {
        if (root == null) {
            nodeInfo.isUnival = true
            return 0
        }
        var left = 0
        var right = 0
        val leftNodeInfo = NodeInfo(root.left)
        left = countUnival(root.left, leftNodeInfo)
        val rightNodeInfo = NodeInfo(root.right)
        right = countUnival(root.right, rightNodeInfo)
        var result = left + right
        if (leftNodeInfo.isUnival == true && rightNodeInfo.isUnival == true) {
            if (root.left == null && root.right == null
                || root.left == null && root.right != null && root.value == root.right!!.value
                || root.right == null && root.left != null && root.value == root.left!!.value
                || root.left != null && root.right != null && root.value == root.left!!.value && root.value == root.right!!.value
            ) {

                // Count itself if both left and right child are unival subtrees
                result++
                nodeInfo.isUnival = true
            }
        }
        return result
    }

    fun buildTree(nodeValues: Array<String>, index: Int): TreeNode? {
        // Base case
        if (index >= nodeValues.size) return null

        // Root is null
        if (nodeValues[index] == "null") return null
        val root = TreeNode(nodeValues[index].toInt())
        root.left = buildTree(nodeValues, 2 * index + 1)
        root.right = buildTree(nodeValues, 2 * index + 2)
        return root
    }
}

fun main() {
    val valuesStr = readLine()!!
    val root = Unival.buildTree(valuesStr.split(" ").toTypedArray(), 0)
    println(Unival.countUnivalSubtrees(root))
}
