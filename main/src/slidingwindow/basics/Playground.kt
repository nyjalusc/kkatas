package slidingwindow.basics

import kotlin.math.max

/**
 * Max subarray sum in a window of size k
 */
class Playground {
    // brute
    fun maxSubArraySumBrute(arr: IntArray, k: Int): Int {
        var result = Int.MIN_VALUE
        for (i in arr.indices) {
            var sum = 0
            for (j in i until i + k) {
                if(j >= arr.size) break
                sum += arr[j]
            }
            result = max(result, sum)
        }

        return result
    }

    fun maxSubArraySumBruteSmart(arr: IntArray, k: Int): Int =
        arr.toList()
            .windowed(size = k, step = 1, partialWindows = true)
            .map { it.sum() }
            .maxOf { it }

        // Sliding Window
    // Window size: j - i + 1, in the implementation below j is exclusive hence j - i is the window size
    fun maxSubArraySum(arr: IntArray, k: Int): Int {
        var result = 0
        var i = 0
        var j = 0
        var sum = 0

        while (j < arr.size) {
            // After counting the element at j we increment so when we count the window size j is exclusive
            sum += arr[j++]
            if((j - i) > k) sum -= arr[i++]
            result = max(result, sum)
        }

        return result
    }

    /**
     * Find first negative number in a window of size k
     * We maintain a queue that keeps track of indices of negative integers within the window
     */
    fun firstNegativeNum(arr: IntArray, k: Int): List<Int> {
        val result = mutableListOf<Int>()
        var i = 0
        val deque = ArrayDeque<Int>()
        for (j in arr.indices) {
            if(arr[j] < 0) deque.addFirst(j)
            if(j - i + 1 >= k) {
                val value = if(deque.isEmpty()) 0 else arr[deque.last()]
                result.add(value)
                i++
            }
            if(deque.isNotEmpty() && i > deque.last()) deque.removeLast()
        }

        return result.toList()
    }
}

fun main() {
    val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    val solution = Playground()
//    println(solution.maxSubArraySumBrute(arr, k = 3))
//    println(solution.maxSubArraySumBruteSmart(arr, k = 3))
//    println(solution.maxSubArraySum(arr, k = 3))


    val negArr = intArrayOf(2, -1, 3, -4, -1, 4, 5, 6)
    println(solution.firstNegativeNum(negArr, 3))
}