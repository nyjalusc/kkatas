package stack.`636`

class ExclusiveTimeOfFunctions {
    /**
     * We maintain a lastTimeStamp to indicate the lasttime when operation was performed. We translate the endtime into
     * start time by adding 1. Meaning if a job completed at the end of time 5 it is the same as saying it ended at start of another op at 6
     * Every time we start a function op we add the id to stack and compute the previous functions running time because
     * it will be paused now and update the lastTimestamp. If there is end op we do the same thing but remove the id from
     * stack top
     */
    fun exclusiveTime(n: Int, logs: List<String>): IntArray {
        val result = IntArray(n) { 0 }
        val stack = ArrayDeque<Int>()
        var lastTimestamp = 0

        logs.forEachIndexed { index, log ->
            val tokens = log.split(":")
            val id = tokens[0].toInt()
            val isStart = tokens[1] == "start"
            var timeStamp = tokens[2].toInt()
            val isEnd = !isStart

            if(isEnd) timeStamp++

            if(!stack.isEmpty()) {
                result[stack.first()] += timeStamp - lastTimestamp
                lastTimestamp = timeStamp
            }

            if(isStart) {
                stack.addFirst(id)
            } else {
                stack.removeFirst()
            }
        }
        return result
    }
}