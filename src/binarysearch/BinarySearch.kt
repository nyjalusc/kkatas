package binarysearch

/**
 *
 */
class BinarySearch {
    fun findTarget(nums: Array<Int>, target: Int): Int {
        var start = 0
        var end = 0
        while (start <= end) {
            val mid = start + (end - start)/2
            if(nums[mid] == target) {
                println("Normal: Found at $mid")
                return mid
            }
            if(target > nums[mid]) start = mid + 1
            else end = mid - 1
        }
        println("Normal: Should have been at $start")
        return start
    }

    fun findTarget2(nums: Array<Int>, target: Int): Int {
        var start = 0
        var end = 0
        while (start < end) {
            val mid = start + (end - start)/2
            if(nums[mid] == target) {
                println("Modified: Found at $mid")
                return mid
            }
            if(target > nums[mid]) start = mid + 1
            else end = mid
        }
        println("Modified: Should have been at $start")
        return start
    }
}

fun main() {
    val binarySearch = BinarySearch()
    println(binarySearch.findTarget(arrayOf(4, 8, 10, 12), 5))
    println(binarySearch.findTarget2(arrayOf(4, 8, 10, 12), 5))
}