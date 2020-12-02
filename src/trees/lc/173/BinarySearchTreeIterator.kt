package trees.lc.`173`

import trees.lc.TreeNode

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 * 
 * 
 * Solution 1: During init, perform inorder traversal and populate a List <-- O(N)
 * Later implement next() and hasNext() using the list <-- O(1)
 * Space: O(N)
 * NOT ACCEPTABLE because of the constrainsts specified in the problem statement
 *
 * Solution 2: Break the inorder recursion by pre-processing the left nodes recursively.
 * Step 1: Recursively traverse left and add current node to a stack for later processing. Do this at the time of init.
 * next(): Pop element, its value is the result. Before returning perform step 1 for node.right to
 * process right subtree.
 * hasNext(): Check if stack is not empty
 * Time:
 * next() and init(): O(N) worst case, but because we are only processing the left nodes, average amortized complexity will be
 * much better
 * hasNext(): O(1)
 * Space: O(H)
 */
class BinarySearchTreeIterator(root: TreeNode?) {
    private val stack = ArrayDeque<TreeNode>()

        init {
            processLeft(root)
        }

        private fun processLeft(root: TreeNode?) {
            var node: TreeNode? = root
            while(node != null) {
                stack.addFirst(node)
                node = node.left
            }
        }

        /** @return the next smallest number */
        fun next(): Int {
            val node = stack.removeFirst()
            processLeft(node.right)
            return node!!.`val`
        }

        /** @return whether we have a next smallest number */
        fun hasNext(): Boolean = stack.isNotEmpty()

}