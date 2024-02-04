package graphs.`133`

class CloneGraph {

    /**
     * DFS Solution: During DFS traversal use a map to keep track of cloned nodes -- Memoization
     * Time: O(N + M): N is the number of Nodes and M is the number of edges
     * Space: O(N)
     */
    fun cloneGraph(node: Node?): Node? {
        if (node == null) return node
        return helper(node, mutableMapOf())
    }

    private fun helper(input: Node?, map: MutableMap<Node, Node>): Node? {
        if (input == null) return null

        // Check if we have a cloned Node for the input node
        if (map.containsKey(input)) return map[input]

        // Create new cloned Node
        val clonedNode = Node(input.`val`)
        clonedNode.neighbors = ArrayList<Node?>()

        // Cache it for lookup -- Memoization
        map[input] = clonedNode

        for (neighbor in input.neighbors) {
            clonedNode.neighbors.add(helper(neighbor, map))
        }
        return clonedNode
    }
}

class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList<Node?>()
}