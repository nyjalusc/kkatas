package graphs.`323`

class NumberOfConnectedComponentsInUndirectedGraph {
    /**
     * DFS approach:
     * Step 1: Create an adjacency list (graph) that shows connection of a node with other nodes Array<List>, index of
     * array indicates the node and list all the nodes it is linked to. It is undirected graph so all do the reverse
     * connection. I call it Graph.
     * Step 2: Iterate over all the nodes in graph (graph.indices) and start dfs at every node. As you traverse the graph
     * in a dfs manner, mark all visited nodes in boolean array(num of nodes).
     * Step 3: At the end of each dfs traversal all visited nodes will be marked as visited and it will return 1
     * (count of components)
     *
     * Time: O(E + V) -> O(E) travel all edges to build graph, then DFS will take O(E + V) we visit all nodes and edges once
     * Space: O(E + V) -> O(E) to build graph, O(V) to keep track of visited nodes, O(V) depth of stack
     */
    fun countComponents(n: Int, edges: Array<IntArray>): Int {
        val graph = Array<MutableList<Int>>(n) { mutableListOf() }
        for (edge in edges) {
            graph[edge.first()].add(edge.last())
            graph[edge.last()].add(edge.first()) // since it is undirected graph we have to do reverse connection
        }

        val visited = BooleanArray(n) { false }
        var components = 0
        for (node in graph.indices) {
            components += dfs(graph, visited, node)
        }
        return components
    }

    private fun dfs(graph: Array<MutableList<Int>>, visited: BooleanArray, node: Int): Int {
        if(visited[node]) return 0
        visited[node] = true
        for(connectingNode in graph[node]) dfs(graph, visited, connectingNode)
        return 1
    }


}