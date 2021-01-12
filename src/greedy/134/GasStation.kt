package greedy.`134`

/**
 *
 */
class GasStation {
    /**
     * Time: O(N)
     * Space: O(1)
     */
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        // Base condition, if total amount of gas available < total cost we cannot have a solution
        val totalGas = gas.sum()
        val totalCost = cost.sum()
        if(totalGas < totalCost) return -1

        // IMPORTANT: At this point we know there is a solution
        // eg. if there are there points A -> B -> C, if we start with A as a starting point and we fail to reach C
        // it means the answer is one of B or C, so in the next iteration we reset tank to 0 and use B as a starting point.
        // If from B we reach C it is good enough, we do not need to check if from C we can reach A. If B fails then we
        // know C is the result. The for-loop goes over the input only once, it doesn't reprocess the values when starting
        // point is changed.
        var tank = 0
        var start = 0
        for (i in gas.indices) {
            tank += gas[i] - cost[i]
            if (tank < 0) {
                tank = 0
                start = i + 1
            }
        }
        return start
    }
}