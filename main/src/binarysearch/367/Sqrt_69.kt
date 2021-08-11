package binarysearch.`367`

/**
 *
 */
class Sqrt_69 {
    // Binary Search: Adjustments made to change bounds from Int to Long to avoid overflow issues
    // Important to handle input that is not a perfect square. Check line: 22
    // Time: O(log N), Space: O(1)
    fun mySqrt(x: Int): Int {
        if(x in 0..1) return x

        // Changing everything to Long makes it easy to perform computation later on
        val xLong: Long = x.toLong()
        var start: Long = 0L
        var end: Long = x.toLong() / 2 // Important property to reduce runtime
        while(start <= end) {
            // mid is Long
            val mid = start + (end - start) / 2
            // It is important square is a Long, to avoid integer overflow mid is Long
            val square = mid * mid
            val squaredPlusOne = (mid + 1L) * (mid + 1L)

            // Handle input that is not a perfect square
            // eg 8, square root is 2.82, 2^2 = 4 < 8 but (2 + 1)^2 = 9 > 8, result should be 2
            // equals (=) to satisfy perfect square numbers
            when {
                xLong in square until squaredPlusOne -> return mid.toInt()
                square < xLong -> start = mid + 1L
                else -> end = mid - 1L
            }
        }
        return -1
    }
}