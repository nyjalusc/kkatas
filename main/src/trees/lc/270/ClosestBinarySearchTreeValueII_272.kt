package trees.lc.`270`

import trees.lc.TreeNode
import java.util.*
import kotlin.math.abs


class ClosestBinarySearchTreeValueII_272 {
    /**
     * Clever: Using LinkedList and avoiding using a Priority Queue
     * Since BST is parsed in a sorted manner we can keep elements in the LL in a sorted manner by adding elements
     * at the end and removing things from the front
     * Time: O(N)
     * Space: O(N)
     */
    fun closestKValuesOpt(root: TreeNode?, target: Double, k: Int): List<Int> {
        val res = LinkedList<Int>()
        collect(root, target, k, res)
        return res
    }

    private fun collect(root: TreeNode?, target: Double, k: Int, res: LinkedList<Int>) {
        if (root == null) return
        collect(root.left, target, k, res)
        if (res.size == k) {
            //if size k, add curent and remove head if it's optimal, otherwise return
            if (Math.abs(target - root.`val`) < Math.abs(target - res.peekFirst())) res.removeFirst() else return
        }
        res.add(root.`val`)
        collect(root.right, target, k, res)
    }

    /**
     * Using MAX-HEAP (Simple)
     * Time complexity: O(N Log K) to push N elements into the heap of the size K.
     * Space complexity: O(K + H) to keep the heap of K elements and the recursion stack of the tree height.
     */
    fun closestKValues(root: TreeNode?, target: Double, k: Int): List<Int> {
        val pq = PriorityQueue<Double>(compareBy{ abs(it.toDouble() - target) })
        inorder(root, target, k, pq)
        return pq.map { it.toInt() }
    }

    private fun inorder(root: TreeNode?, target: Double, k: Int, pq: PriorityQueue<Double>) {
        if(root == null) return

        inorder(root.left, target, k, pq)
        pq.add(root.`val`.toDouble())
        if(pq.size > k) pq.remove() // remove element with max difference
        inorder(root.right, target, k, pq)
    }
}