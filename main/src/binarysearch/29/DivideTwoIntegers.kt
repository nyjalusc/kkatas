package binarysearch.`29`

import kotlin.math.abs

class DivideTwoIntegers {
    /**
     * Using Long to prevent integer overflow issues;
     * Smallest Int value -2^31 cannot be converted to positive value, range of positive int is [0..2^31-1]
     * To avoid int overflow issue we convert everything to long and turn them into positive values by using Math.abs()
     * We first figure out if the result will need a negative sign and use that at the end to return the signed result
     * Time: O(N)
     * Space: O(1)
     */
    fun divideUsingLong(dividend: Int, divisor: Int): Int {

        val isNegative = (dividend < 0) xor (divisor < 0)

        var quotient: Long = 0L
        var mDividend: Long = abs(dividend.toLong())
        val mDivisor: Long = abs(divisor.toLong())
        while (mDividend >= mDivisor) {
            quotient++
            mDividend -= mDivisor
        }

        val result = if(isNegative) -quotient else quotient

        return if(result > Int.MAX_VALUE) Int.MAX_VALUE else result.toInt()
    }

    /**
     * Operate in -ve integer space. Turn inputs into negative ints after computing sign of the result.
     * Add divisor as long as the computed result >= dividend OR keep subtracting divisor from dividend as long as
     * dividend >= divisor. Number of times this operation is performed is the quotient.
     *
     * Watch out for integer overflow.
     * Time: O(N)
     * Space: O(1)
     */
    fun divideUsingLinearOp(dividend: Int, divisor: Int): Int {
        val isNegative = (dividend < 0) xor (divisor < 0)

        // Convert to negative values
        var quotient = 0
        var prev = 0
        var mDividend = if(dividend < 0) dividend else -dividend
        val mDivisor = if(divisor < 0) divisor else -divisor
        while (mDivisor >= mDividend) {
            prev = quotient
            quotient++
            if(quotient < prev) return if(isNegative) Int.MIN_VALUE else Int.MAX_VALUE // Overflow occurred return the largest value

            mDividend = mDividend + (-mDivisor) // Handle it carefully since we are operating in negative integer space
        }

        return if(isNegative) -quotient else quotient
    }

    /**
     * Using modified version of Exponential Search
     * Similar to previous solutions we'll operate in -ve integer space. Handle (Int.MIN_VALUE, -1) as a special overflow
     * case.
     * Step 1: Extract sign of the result
     * Step 2: Convert inputs to negative ints
     * Step 3: Like other solutions we'll add divisor to reach Dividend, create the outer loop condition
     * Step 4: Now we add the divisor in an exponential manner, i.e multiply by 2 every loop and check if the result
     * is still less than Divisor. We are trying to find what is largest value that is multiple of 2 and dividend we can
     * fit in the divisor. Once we find that we subtract that from the divisor and repeat the process. While doing this,
     * we also keep a count the times we multiply by two.
     *
     * NOTE: Since we cannot use multiplication we are doubling the values by using addition.
     *
     * Step 5: Compute quotient by simply adding powerOfTwo and return the result after adjusting the sign
     *
     *
     * Time: O(log N)
     * Space: O(1)
     */
    fun divide(dividend: Int, divisor: Int): Int {
        // Special case: overflow.
        if (dividend == Int.MIN_VALUE && divisor == -1) {
            return Int.MAX_VALUE;
        }

        val isNegative = (dividend < 0) xor (divisor < 0)

        var mDividend = if(dividend < 0) dividend else -dividend
        val mDivisor = if(divisor < 0) divisor else -divisor

        var quotient = 0
        var value = mDivisor
        val HALF_MAX = Int.MAX_VALUE / 2
        while (mDivisor >= mDividend) {
            var powerOfTwo = -1 // IMPORTANT
            while ((value + value >= mDividend) && (value + value >= HALF_MAX)) {
                value += value
                powerOfTwo += powerOfTwo
            }

            mDividend -= value
            quotient += powerOfTwo
        }

        return if(isNegative) quotient else -quotient
    }
}

fun main() {
    val divide = DivideTwoIntegers()
    println(divide.divide(Int.MIN_VALUE, 2))
}