package trees.lc.`144`

import trees.lc.TreeNode

/**
 *
 */
class Inorder_94 {
    // Only attempting iterative, recursive is very easy
        fun inorderTraversal(root: TreeNode?): List<Int> {
            val result = mutableListOf<Int>()
            val stack = ArrayDeque<TreeNode>()
            var current = root
            while(true){
                if(current != null) {
                    stack.addFirst(current)
                    current = current.left
                } else {
                    if(stack.isEmpty()) break;
                    current = stack.removeFirst()
                    result.add(current.`val`)
                    current = current.right
                }
            }
            return result
        }
}