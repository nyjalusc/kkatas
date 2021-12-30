package trees.lc.`144`

import trees.lc.TreeNode

class Postorder_145 {
    fun postorderTraversal(root: TreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        val stack = ArrayDeque<TreeNode>()
        var current = root
        while(true) {
            if(current != null) {
                stack.addFirst(current)
                current = current.left
            } else {
                if(stack.isEmpty()) break
                current = stack.first()?.right
                if(current == null) {
                    // current node processing is complete, we must pop it from the stack and add to the result
                    // But it is possible that this node is the right child of its parent so in that case
                    // parent's we must also pop the parent and add it to the result. We keep doing this until
                    // we hit a node whose right child is not visited.
                    var node = current
                    while(!stack.isEmpty() && node == stack.first()?.right) { // Most important
//                        node = stack.()
//                        result.add(node.`val`)
                    }
                }
            }
        }
        return result
    }
}