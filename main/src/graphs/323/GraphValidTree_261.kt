package graphs.`323`

class GraphValidTree_261 {
    /**
     * DFS approach:
     * Step 1: Create an adjacency list (graph) that shows connection of a node with other nodes Array<List>, index of
     * array indicates the node and list all the nodes it is linked to. It is undirected graph so all do the reverse
     * connection. I call it Graph.
     * Step 2: Start DFS from one of the node, if you finish visiting all nodes at the end of this traversal such that
     * you visit all nodes just once, then you have a solution
     *
     * The key thing here is that we want to keep track of parent node (ie. where you are coming from) to not falsely
     * think of it as a visited node and return false.
     *
     * Time: O(E + V) -> O(E) travel all edges to build graph, then DFS will take O(E + V) we visit all nodes and edges once
     * Space: O(E + V) -> O(E) to build graph, O(V) to keep track of visited nodes, O(V) depth of stack
     */
    fun validTree(n: Int, edges: Array<IntArray>): Boolean {
        val adjacencyList = Array<MutableList<Int>>(n) { mutableListOf() }
        for (edge in edges) {
            adjacencyList[edge.first()].add(edge.last())
            adjacencyList[edge.last()].add(edge.first())
        }

        val visited = BooleanArray(n) { false }
        return dfs(adjacencyList as Array<List<Int>>, visited, 0, -1) && visited.all { it == true }
    }

    private fun dfs(adjacencyList: Array<List<Int>>, visited: BooleanArray, node: Int, parent: Int): Boolean {
        visited[node] = true
        for(connectedNode in adjacencyList[node]) {
            if(connectedNode != parent) {
                if(visited[connectedNode]) return false
                if(!dfs(adjacencyList, visited, connectedNode, node)) return false
            }
        }
        return true
    }
}