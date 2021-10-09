package trees.lc.`144`

import trees.lc.TreeNode

class SecondMinimumNodeInATree_671 {
    var min = -1

    // Long is used to handle one testcase that causes Int overflow otherwise Int.MAX_VALUE should be fine
    var secondMin = Long.MAX_VALUE

    fun findSecondMinimumValue(root: TreeNode?): Int {
        if(root == null) return -1
        min = root.`val`
        dfs(root)

        return if(secondMin == Long.MAX_VALUE) -1 else secondMin.toInt()
    }

    /**
     * Preorder traversal, because the tree is sorted we need to find the first node that is greater
     * than the root (which is guaranteed to be the min node).
     */
    private fun dfs(root: TreeNode?) {
        if(root == null) return

        if(min < root.`val` && root.`val` < secondMin) {
            secondMin = root.`val`.toLong()
            return
        }

        dfs(root.left)
        dfs(root.right)
    }
}