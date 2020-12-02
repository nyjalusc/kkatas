package arrays.cw

import kotlin.test.assertEquals

/**
 *
 */
fun tribonacci(signature: DoubleArray, n: Int): DoubleArray {
    val result = DoubleArray(n) {0.0}
    signature.copyInto(result)
    var i = 3
    var start = 0
    var end = 2
    while (i < result.size) {
        result[i++] = result.slice(start..end).sum()
        start++
        end++
    }
    return result
}
