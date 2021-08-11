package trees.lc.`104`
import kotlin.math.max
import trees.lc.TreeNode

class MaximumDepth {
    // Bottom-up
    fun maxDepth(root: TreeNode?): Int {
        if(root == null) return 0
        return 1 + max(maxDepth(root.left), maxDepth(root.right))
    }
}