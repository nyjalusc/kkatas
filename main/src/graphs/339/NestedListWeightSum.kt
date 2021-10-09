package graphs.`339`

class NestedListWeightSum {
    /**
     * DFS
     * Time: O(N)
     * Space: O(N)
     */
    fun depthSumDFS(nestedList: List<NestedInteger>): Int {
        return helper(nestedList, 1)
    }

    private fun helper(nestedList: List<NestedInteger>, depth: Int): Int {
        if(nestedList.isEmpty()) return 0
        var sum = 0
        nestedList.forEach {
            if(it.isInteger()) sum += it.getInteger()!! * depth
            else helper(it.getList()!!, depth + 1)
        }
        return sum
    }

    /**
     * BFS
     * Time: O(N)
     * Space: O(N)
     */
    fun depthSum(nestedList: List<NestedInteger>): Int {
        val queue = ArrayDeque<List<NestedInteger>>()

        var depth = 0
        var sum = 0
        queue.addFirst(nestedList)
        while (queue.isNotEmpty()) {
            depth++
            val count = queue.size
            repeat(count) {
                val current = queue.removeFirst()
                current.forEach {
                    if(it.isInteger()) sum += it.getInteger()!! * depth
                    else queue.addLast(it.getList()!!)
                }
            }
        }
        return sum
    }
}