package trees.lc.`112`

/**
 *
 */
import trees.lc.TreeNode
import kotlin.math.max
import kotlin.math.min
import kotlin.math.abs

class MaximumDifferenceNodeAndAncestor_1026 {

    /**
     * Important: While computing its essential to make sure child parent relationship is maintained.
     * Used a combination of PreOrder and PostOrder method. Goal is to find the min and max values between nodes
     * that have parent child relationship. You cannot compare two nodes from different branches of a node (mistake I made
     * in first attempt). We compute the current node to find min and max values so far, Until the children passes the result,
     * the current node cannot complete processing hence post-order traversal. Current node is implicitly processed, this
     * is achieved by passing the min/maxSoFar to children, if they do not have better value, they return the same result
     * with min-max values that parent had passed, otherwise they return a better result. Parent computes the result
     * by comparing absolute difference between result of left and right subtrees.
     *
     * Time: O(N)
     * Space: O(H), worst case O(N)
     */
    fun maxAncestorDiff(root: TreeNode?): Int {
        if(root == null) return 0
        val minMax = maxAncestorDiffHelper(root, root.`val`, root.`val`)
        return abs(minMax.first - minMax.second)
    }

    private fun maxAncestorDiffHelper(root: TreeNode?, min: Int, max: Int): Pair<Int, Int> {
        if(root == null) return Pair(min, max)

        val minSoFar = min(min, root.`val`)
        val maxSoFar = max(max, root.`val`)

        val leftResult = maxAncestorDiffHelper(root.left, minSoFar, maxSoFar)
        val rightResult = maxAncestorDiffHelper(root.right, minSoFar, maxSoFar)


        val leftDiff = abs(leftResult.first - leftResult.second)
        val rightDiff = abs(rightResult.first - rightResult.second)

        return if (leftDiff > rightDiff) leftResult else rightResult
    }
}