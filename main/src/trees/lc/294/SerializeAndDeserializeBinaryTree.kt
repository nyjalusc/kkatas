package trees.lc.`294`

import trees.lc.TreeNode
class SerializeAndDeserializeBinaryTree {
    // Encodes a URL to a shortened URL.
    // We use Preorder traversal to populate the list and convert it into a string
    fun serialize(root: TreeNode?): String {
        if(root == null) return "null"
        val list = mutableListOf<String>()
        serializeHelper(root, list)
        return list.joinToString(",")
    }

    private fun serializeHelper(root: TreeNode?, list: MutableList<String>) {
        if(root == null) {
            list.add("null")
            return
        }

        list.add(root.`val`.toString())
        serializeHelper(root.left, list)
        serializeHelper(root.right, list)
    }

    // Decodes your encoded data to tree.
    // IMPORTANT: This is dependent on the implementation of the serialize()
    // Here we use a deque populated with the result of serialize(). We work on one node at a time
    // the node is polled() from the queue and then we create its left and right child nodes using recursion.
    // Check my alternate implementation that did not work.
    fun deserialize(data: String): TreeNode? {
        if(data.isEmpty()) return null

        val deque = ArrayDeque<String>(data.split(","))
        return buildTree(deque)
    }

    private fun buildTree(deque: ArrayDeque<String>): TreeNode? {
        if(deque.isEmpty()) return null

        val nodeVal = deque.removeFirst()
        if(nodeVal == "null") return null

        val root = TreeNode(nodeVal.toInt())
        root.left = buildTree(deque)
        root.right = buildTree(deque)

        return root
    }

    // Doesn't work: The relationship 2n+1 and 2n+2 is not maintained by the serialization function.
    // For this to work the serialized string will need to preserve the relationship, it'll be quite a long string
    // due to all the null values required to represent leaf nodes correctly.
    //     // Decodes your encoded data to tree.
    //     fun deserialize(data: String): TreeNode? {
    //         if(data.isEmpty()) return null

    //         val list = data.split(",")
    //         println(list)
    //         return buildTree(list, 0)
    //     }

    //     private fun buildTree(list: List<String>, index: Int): TreeNode? {
    //         if(index >= list.size) return null

    //         val nodeVal = list[index]!!.let { if(it == "null") null else it.toInt() }
    //         if(nodeVal == null) return null

    //         val root = TreeNode(nodeVal)
    //         root.left = buildTree(list, 2 * index + 1)
    //         root.right = buildTree(list, 2 * index + 2)

    //         return root
    //     }
}