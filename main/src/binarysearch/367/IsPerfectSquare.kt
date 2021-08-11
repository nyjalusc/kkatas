package binarysearch.`367`

/**
 *
 */
class IsPerfectSquare {
    fun isPerfectSquare(num: Int): Boolean {
        if(num in 0..1) return true // Need to be handled as an edge case

        var start = 0
        var end = num / 2 // Brings down the runtime if input is Int.MAX_VALUE
        val numL = num.toLong() // Doing this so that it can be compared with square (long) in when block
        while(start <= end) {
            val mid = start + (end - start) / 2
            //Int * Int gives you Int, if mid is large enough it will cause overflow
            // To avoid that you need to convert mid to Long before the operation
            val square: Long = (mid.toLong() * mid.toLong())
            when {
                square == numL -> return true
                square < numL -> start = mid + 1
                else -> end = mid - 1
            }
        }
        return false
    }
}