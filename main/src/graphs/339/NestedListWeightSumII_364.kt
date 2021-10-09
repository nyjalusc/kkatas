package graphs.`339`

import kotlin.math.max
class NestedListWeightSumII_364 {
    /**
     * The weight of an integer is maxDepth - (the depth of the integer) + 1.
     * Return the sum of each integer in nestedList multiplied by its weight.
     * Two pass Algorithm: 1 pass to calculate depth
     */
    fun depthSumInverse(nestedList: List<NestedInteger>): Int {
        val maxDepth = getMaxDepth(nestedList, 1)

        var sum = 0
        var depth = 0
        val queue = ArrayDeque<List<NestedInteger>>()
        queue.addFirst(nestedList)
        while (queue.isNotEmpty()) {
            depth++
            val queueSize = queue.size
            repeat(queueSize) {
                val nestedList = queue.removeFirst()
                nestedList.forEachIndexed { _, value ->
                    if (value.isInteger()) {
                        sum += value.getInteger()!! * (maxDepth - depth + 1)
                    } else {
                        queue.addLast(value.getList()!!)
                    }
                }
            }
        }
        return sum
    }

    private fun getMaxDepth(nestedList: List<NestedInteger>, initDepth: Int): Int {
        if (nestedList.isEmpty()) return 0

        var depth = initDepth
        nestedList.forEach {
            if (!it.isInteger()) {
                depth = max(getMaxDepth(it.getList()!!, initDepth + 1), depth)
            }
        }
        return depth
    }

    /**
     * Tree like DFS
     */
    private fun getMaxDepth(nestedList: List<NestedInteger>): Int {
        if (nestedList.isEmpty()) return 0
        var maxDepth = 1
        nestedList.forEach {
            if (!it.isInteger()) {
                maxDepth = max(getMaxDepth(it.getList()!!) + 1, maxDepth)
            }
        }
        return maxDepth
    }
}