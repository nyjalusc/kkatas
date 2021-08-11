package arrays.basics

/**
 * Basic operations
 */
fun main() {
    // 1. Adding new element at the end, the entire array is copied and reassigned (CopyOnWrite)
    val nums1 = arrayOf(1, 2)
    val nums2 = nums1 + 3 // Or nums = nums.plus(3)
    println(nums2.contentToString())

    // 2. Slice, sliceArray returns Array<T>, slice return List<T>
    val fullArr = arrayOf(1, 2, 3, 4, 5)
    println(fullArr.sliceArray(0..2)) // [1, 2, 3]

    // 3. Take, first N
    println(fullArr.take(2)) // [1, 2]

    // Take last N
    println(fullArr.takeLast(2)) // [4, 5]

    // 4. Difference between takeWhile() and filter()
    // takeWhile() -> takes elements as long as the condition is true, and stops once it is not
    // filter() -> filters all elements that meet the condition
    val arr = arrayOf(1, 1, 1, 2, 1, 3, 4, 5, 5, 6, 6, 6)
    println(arr.takeWhile { it == 1 }) // [1, 1, 1]
    println(arr.filter { it == 1 }) // [1, 1, 1, 1]

    // Same thing from the other end
    println(arr.takeLastWhile { it == 1 }) // [] Condition is false as soon as it starts

    // 5. takeIf returns true if the predicate is true, null otherwise
    println(arr.takeIf { it.size > 10}?.contentToString())

    // takeUnless returns true if the predicate is false, null otherwise. Inverse of takeIf
    println(arr.takeUnless { it.size > 15}?.contentToString())

    // 6. indexOf vs lastIndexOf
    println(arr.indexOf(1))
    println(arr.lastIndexOf(1))

    // 7. indexOfFirst vs indexOfLast
    println(arr.indexOfFirst { it > 2 }) // 5, index of 3 is 5
    println(arr.indexOfLast { it > 6 }) // -1, all elements are less than or equal to 7

    // 8. first vs last
    println("First: ${arr.first()}") // same as arr[0]
    println("Last: ${arr.last()}") // and arr[arr.size - 1]

    // 9. size property vs count vs count (predicate)
    println("Number of elements: ${arr.size}")
    println("Count of elements: ${arr.count()}")
    println("Count with predicate: ${arr.count { it > 1 }}") // 8, size - 4 (there are 4 1's)

    // 10. sum(), average(), min(), max()
    println(arr.sum())
    println(arr.average())
    println(arr.minOrNull())
    println(arr.maxOrNull())

    // 11. minBy vs maxBy, takes in lambda that will be used to find min/max result
    // NOTE: maxBy and minBy can return null if the collection or array is null
    val names = arrayOf("John", "Jacob", "Sam", "Li")
    println(names.minByOrNull { it.length }) // Li
    println(names.maxByOrNull { it.length }) // Jacob

    // 12. Filter
    val numsUnfiltered = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)
    val even = numsUnfiltered.filter { it % 2 == 0 }.forEach { print("$it, ")}
    println()

    // 13. find(first) and findLast
    val firstEven = numsUnfiltered.find { it % 2 == 0 }
    println("First Even: $firstEven")

    val lastEven = numsUnfiltered.findLast { it % 2 == 0 }
    println("Last Even: $lastEven")

    // 14. reduce. In the first iteration acc=arr[0] and i = arr[1], in the second iteration acc = prevResult, i = arr[2]
    val factorial = numsUnfiltered.reduce { acc, i -> acc * i }
    println("Factorial(8): $factorial")

    // 15. fold. Same as reduce but it has a starting value.
    // We will double the value of 8 factorial by providing 2 as a starting value
    val doubleFactorial = numsUnfiltered.fold(2) {acc, i -> acc * i }
    println("Double Factorial(8): $doubleFactorial")

    // 16. all() method returns true if all elements match the given predicate.
    val allLessThan10 = numsUnfiltered.all { it < 10 }
    println("All less than 10: $allLessThan10")

    // 17. any() method returns true if all elements match the given predicate.
    val anyGreaterThan10 = numsUnfiltered.any { it > 10 }
    println("Any greater than 10: $anyGreaterThan10")

    // 18. groupBy takes in a keySelector and groups items in Array/Collection based upon the key specified
    // Returns a Map
    val strsGroupedByLength = listOf("abc", "def", "gh", "ijklm").groupBy { it.length }
    println(strsGroupedByLength)

    // First groupBy param is key selector and the second is a transformer for the value
    val strsGroupByWithTransformation = listOf("abc", "def", "gh", "ijklm").groupBy ({ it.length }, {it.toUpperCase()})
    println(strsGroupByWithTransformation)

    // 19. copyInto - the destination array must have enough spaces otherwise you'll get ArrayOutOfBoundEx
    val copiedArr = Array(fullArr.size + 5) {0}
    fullArr.copyInto(copiedArr, destinationOffset = 1, startIndex = 0, endIndex = fullArr.size)
    println(copiedArr.contentToString())

    // 20. distinct() (better than creating a set to remove dups)
    val dups = arrayOf(1, 1, 2, 2, 3, 4, 5, 5, 5)
    println("No dups: ${dups.distinct()}")

}