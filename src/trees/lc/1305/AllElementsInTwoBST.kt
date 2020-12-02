package trees.lc.`1305`

import trees.lc.TreeNode

/**
 *
 */
class AllElementsInTwoBST {
    // Do inorder traversal of both tree and save the result in two different queues
    // Perform a sorted merge (one element at a time) to create the result list
    fun getAllElements(root1: TreeNode?, root2: TreeNode?): List<Int> {
        val dq1 = ArrayDeque<Int>()
        val dq2 = ArrayDeque<Int>()

        inorder(root1, dq1)
        inorder(root2, dq2)

        // tree1.addAll(tree2)
        // return tree1.sorted() This will be O((N+M) log(N+M))

        // To reduce sorting complexity lets use sorted merge
        val result = mutableListOf<Int>()
        while (dq1.isNotEmpty() && dq2.isNotEmpty()) {
            val a = dq1.first()
            val b = dq2.first()
            val min = if(a < b) dq1.removeFirst() else dq2.removeFirst()
            result.add(min)
        }
        val leftOver = if(dq1.isEmpty()) dq2 else dq1
        if(leftOver.isNotEmpty()) result.addAll(leftOver)
        return result

    }

    private fun inorder(root: TreeNode?, queue: ArrayDeque<Int>) {
        if(root == null) return

        inorder(root.left, queue)
        queue.addLast(root.`val`)
        inorder(root.right, queue)
    }
}