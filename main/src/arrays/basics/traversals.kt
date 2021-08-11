package arrays.basics

/**
 * Traversal methods
 */
fun main() {
    // 1. forEach
    val nums = intArrayOf(1, 2, 3, 4, 5)
    nums.forEach { print("$it ") }
    println()

    // 2. forEachIndexed
    nums.forEachIndexed { i, value ->
        println("index: $i value: $value")
    }

    // 3. Traditional for loop. It can be only used on Iterables
    for (num in nums) print("$num ")
    println()

    // 4. For with indices
    for (i in nums.indices) print("${nums[i]} ")
    println()

    // 5. Iterator style
    val iterator = nums.iterator()
    while (iterator.hasNext()) print(iterator.next())
    println()
}