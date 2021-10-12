package trees.lc.bst.`653`

import trees.lc.TreeNode

class TwoSumBST {
    /**
     * Use a set to keep track of values of visited nodes. While visiting a node check if k - root.val already exists in
     * the set
     * Space: O(N)
     * Time: O(N)
     */
    fun findTarget(root: TreeNode?, k: Int): Boolean {
        val set = HashSet<Int>()
        return twoSumFinder(root, k, set)
    }

    private fun twoSumFinder(root: TreeNode?, k: Int, set: MutableSet<Int>): Boolean {
        if(root == null) return false

        if (set.contains(root.`val`)) return true
        else set.add(k - root.`val`)

        return twoSumFinder(root.left, k, set) || twoSumFinder(root.right, k, set)
    }
}