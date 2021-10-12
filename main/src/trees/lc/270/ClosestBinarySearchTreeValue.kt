package trees.lc.`270`

import trees.lc.TreeNode
import kotlin.math.abs

class ClosestBinarySearchTreeValue {
    fun closestValue(root: TreeNode?, target: Double): Int {
        if(root == null) return Int.MAX_VALUE

        val rootVal = root.`val`
        val closestFromChildren = if(target > rootVal.toDouble()) closestValue(root.right, target)
        else closestValue(root.left, target)

        return if(abs(rootVal.toDouble() - target) < abs(closestFromChildren.toDouble() - target)) rootVal else closestFromChildren
    }
}