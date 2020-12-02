package strings.hr

import java.util.HashSet

/**
 *
 */
fun main() {
    val input = "abba"
    println(palindrome(input))
}

// 1. Generate substrings and check if palindrome (Fails for large tests)
fun dirtyPalindrome(s: String): Int {
    val result: MutableSet<String> = HashSet()
    for (i in s.indices) {
        var j = i + 1
        while (j <= s.length) {
            val sub = s.substring(i, j)
            var left = 0
            var right = sub.lastIndex
            while (left <= right && sub[left] == sub[right]) {
                left++
                right--
            }
            if (left > right) result.add(sub)
            j++
        }
    }
    return result.size
}

// 2. Expand around center (Accepted)
fun palindrome(s: String): Int{
    val result = HashSet<String>()
    for (i in s.indices) {
        expand(left = i, right = i, input = s, output = result)
        expand(left = i, right = i + 1, input = s, output = result)
    }
    return result.size
}

private fun expand(
    left: Int,
    right: Int,
    input: String,
    output: MutableSet<String>
): Set<String> {
    var leftIdx = left
    var rightIdx = right
    while (leftIdx >= 0 && rightIdx < input.length && input[leftIdx] == input[rightIdx]) {
        output.add(input.substring(range = leftIdx..rightIdx))
        leftIdx--
        rightIdx++
    }
    return output
}