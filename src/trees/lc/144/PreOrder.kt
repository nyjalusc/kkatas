package trees.lc.`144`

import trees.lc.TreeNode

class PreOrder {
    // Recursive
    /*
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        helper(root, result)
        return result
    }

    private fun helper(root: TreeNode?, list: MutableList<Int>) {
        if(root == null) return

        list.add(root.`val`)
        helper(root.left, list)
        helper(root.right, list)
    }
    */

    // Iterative
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        val stack = ArrayDeque<TreeNode>()
        var current = root
        while(true){
            if(current != null) {
                result.add(current.`val`)
                current.right?.also{ stack.addFirst(it) }
                current = current.left
            } else {
                if(stack.isEmpty()) break;
                current = stack.removeFirst()
            }
        }
        return result
    }
}