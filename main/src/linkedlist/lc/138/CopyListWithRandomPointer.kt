package linkedlist.lc.`138`

/**
 *
 */
class Node(var `val`: Int) {
    var next: Node? = null
    var random: Node? = null
}

class CopyListWithRandomPointer {
    /**
     * Time: O(N) Space: O(N)
     *
     * Create dummy root to keep track of new list being created
     * Use map to cache GivenNode -> NewNode relation. It helps to revisit nodes that are created
     * in advance but not fully initialized.
     */
    fun copyRandomList(node: Node?): Node? {
        // p1 is given list pointer
        var p1 = node
        // p2 is result list pointer initialized at a dummy root
        val root = Node(0)
        var p2: Node? = root

        // map of Orignal Node -> New Node
        // There can be nodes in the map that are not fully processed, meaning
        // they do not have next and random pointers set correctly
        val map = mutableMapOf<Node, Node>()
        var newNode: Node? = null
        while(p1 != null) {
            // Check if we have already created a new node for the current node pointed by p1
            // if yes lets use it, otherwise create a new node and update the map
            if (map.containsKey(p1)) {
                newNode = map[p1]
            } else {
                newNode = Node(p1.`val`)
                map[p1] = newNode
            }

            // Create next pointer for the new ode
            if (p1.next != null) {
                if (map.containsKey(p1.next!!)) {
                    newNode!!.next = map[p1.next!!]
                } else {
                    newNode!!.next = Node(p1.next!!.`val`)
                    map[p1.next!!] = newNode.next!!
                }
            }

            // Create random pointer for the new node
            if (p1.random != null) {
                if (map.containsKey(p1.random!!)) {
                    newNode!!.random = map[p1.random!!]
                } else {
                    newNode!!.random = Node(p1.random!!.`val`)
                    map[p1.random!!] = newNode.random!!
                }
            }

            // New node is fully created but not linked to the result linked list at this point

            // Increment orignal list pointer
            p1 = p1.next
            // Connect new node to the result list
            p2?.next = newNode
            // Increment pointer for the result list
            p2 = p2?.next
        }

        return root.next
    }
}