package sort.`1235`

import java.util.*
import kotlin.math.max


class MaximumProfitInJobScheduling {
    private data class Job(val startTime: Int, val endTime: Int, val profit: Int)

    /**
     * Profit made by end of time t2 = max profit made by end of t1 + profit made during t2 duration
     * We use a treemap to keep track of <EndTime, Max profit made by then>. To compute total profit made
     * by the end of current job, we find the latest job that completed + profit of current job and put it in the map
     * https://www.youtube.com/watch?v=ZOP43iB_E_8
     * Time: O(N log N)
     * Space: O(N)
     */
    fun jobSchedulingSmallBug(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        if((startTime.size != endTime.size) || (startTime.size != profit.size)) return Int.MIN_VALUE // Invalid input

        val jobNums = startTime.size
        val jobs = mutableListOf<Job>()
        repeat(jobNums) { index ->
            jobs.add(Job(startTime = startTime[index], endTime = endTime[index], profit = profit[index]))
        }

        // Sort he jobs by end time
        jobs.sortBy { it.endTime }

        // Create a map that indicates maximum profit(value) made by end of time (key)
        val map = TreeMap<Int, Int>()
        var maxProfit = 0
        for (job in jobs) {
            // Get maximum profit made by start time
            val maxProfitByStartTime = map.floorEntry(job.startTime)?.value ?: 0
            map[job.endTime] = maxProfitByStartTime + job.profit
            maxProfit = max(maxProfit, maxProfitByStartTime + job.profit)
        }

        return maxProfit
    }

    fun jobScheduling(startTime: IntArray, endTime: IntArray, profit: IntArray): Int {
        val n = startTime.size
        val jobs = Array(n) { IntArray(3) }
        for (i in 0 until n) {
            jobs[i] = intArrayOf(startTime[i], endTime[i], profit[i])
        }
        Arrays.sort(jobs) { a: IntArray, b: IntArray -> a[1] - b[1] }
        val dp = TreeMap<Int, Int>()
        dp[0] = 0
        for (job in jobs) {
            val cur = dp.floorEntry(job[0]).value + job[2]
            if (cur > dp.lastEntry().value) dp[job[1]] = cur
        }
        return dp.lastEntry().value
    }
}