package binarysearch

/**
 *
 */
class FirstValue {
    // Smallest value greater than x
    fun isGreaterThanX(arr: Array<Int>, x: Int): Int {
        if(arr.isEmpty()) return -1

        var start = 0
        var end = arr.lastIndex
        var result = -1
        while (start <= end) {
            val mid = start + (end - start) / 2
            when {
                arr[mid] == x -> return mid
                arr[mid] > x -> {
                    result = mid
                    end = mid - 1
                }
                else -> start = mid + 1
            }
        }

        return result
    }

    // Greatest value smaller than x
    fun isSmallerThanX(arr: Array<Int>, x: Int): Int {
        if(arr.isEmpty()) return -1

        var start = 0
        var end = arr.lastIndex
        var result = -1
        while (start <= end) {
            val mid = start + (end - start) / 2
            when {
                arr[mid] == x -> return mid
                arr[mid] < x -> {
                    result = mid
                    start = mid + 1
                }
                else -> end = mid - 1
            }
        }

        return result
    }
}

fun main() {
    val solution = FirstValue()
    println(solution.isGreaterThanX(arrayOf(2, 3, 5, 7), 4)) // Prints 2 because 5 is the first value larger than 4
    println(solution.isSmallerThanX(arrayOf(20, 30, 37, 38, 40, 50, 60), 35)) // Prints 1 because 30 is largest value smaller than x

}