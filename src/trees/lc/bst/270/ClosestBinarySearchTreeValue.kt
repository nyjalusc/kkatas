package trees.lc.bst.`270`

import trees.lc.TreeNode
import kotlin.math.abs

/**
 * Used Post order traversal to compute the closest value.
 * Time: O(N) Space: O(H)
 *
 * It can be improved by  using Binary Search Tree property.
 * If target > root go to right otherwise left.
 * Time: O(H) Space: O(1)
 */
class ClosestBinarySearchTreeValue {
    fun closestValue(root: TreeNode?, target: Double): Int {
        if(root == null) return target.toInt()

        val result = findClosestValue(root, target)
        return result.first!!.`val`
    }

    private fun findClosestValue(root: TreeNode?, target: Double): Pair<TreeNode?, Double> {
        if(root == null) return Pair(null, Double.MAX_VALUE)

        val left = findClosestValue(root.left, target)
        val right = findClosestValue(root.right, target)

        val closerChild = if(left.second < right.second) left else right

        val rootDistance = abs(root.`val` - target)
        return if(closerChild.second < rootDistance) closerChild else Pair(root, rootDistance)
    }

    fun closestValueIterative(root: TreeNode?, target: Double): Int {
        var current = root
        var closest = root!!.`val`
        while (current != null) {
            closest = if (abs(current.`val` - target) < abs(closest - target)) current.`val` else closest
            current = if (target < root.`val`) root.left else root.right
        }
        return closest
    }
}