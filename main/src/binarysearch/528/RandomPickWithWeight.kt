package binarysearch.`528`

import kotlin.random.Random

class RandomPickWithWeight(w: IntArray) {
    //    val wSums = w.runningReduce { acc, i -> acc + i}.toIntArray()
    val wSums = w.copyOf()

    init {
        for (i in 1 until w.size) {
            wSums[i] += wSums[i - 1]
        }
    }

    val random = Random(0)

    fun pickIndex(): Int {
        val max = wSums.last()
        val randomWeight = random.nextInt(until = max + 1)
//        val bucket = wSums.binarySearch(randomWeight, 0, wSums.lastIndex)
        return binarySearch(wSums, randomWeight)
    }

    private fun binarySearch(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex
        while (left <= right) {
            val mid = left + (right - left) / 2
            if(nums[mid] == target) return mid
            if(target > nums[mid]) left = mid + 1
            else right = mid - 1
        }
        return left
    }
}