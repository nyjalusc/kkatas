package strings.cw

import kotlin.test.assertEquals

/**
 * buggy
 */
fun duplicateCount(text: String): Int {
    val countMap = Array(26) { 0 }
    text.toLowerCase().forEach {
        val idx = it - 'a'
        if (idx in 0..25) {
            countMap[idx]++
        }
    }
    return countMap.filter { it > 1 }.size
}

/** Optimal **/
// groupBy is defined over an Array that will return a map of Key specified by key selector and values from the Array/Collection
fun duplicateCount2(text: String): Int = text.toLowerCase().groupBy { it }.count { it.value.size > 1 }

fun main() {
    val str = "NRtL9vcyymCnSMvM0sNGPJlWIn0ajLYU80I5NR5VWJ08LzJ0zszrzUZYgYCNrUKZjzSk8liajiPVPwZLpPaYSKYgYj880UrMYPSNlj55YIrLUs0wUjAaU8PllSjZ5z"
    assertEquals(20, duplicateCount2(str))
}