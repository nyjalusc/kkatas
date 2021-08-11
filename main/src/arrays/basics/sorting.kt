package arrays.basics

import java.util.*
import kotlin.Comparator

/**
 *
 * Kotlin Convention:
 *     sort() as a noun or verb is going to act upon the receiver
 *     sorted() - gives a List
 *     sortedArray() - gives typed Array
 *     sort(ed)By - takes lambda(selector) as an input, the selector returns the value to sort on
 *     sort(ed)With - takes Comparator as an input, you can use different factory methods for creating Comparators
 */
fun main() {

    val pq = PriorityQueue<Int>(Comparator {a, b -> a - b})

    val nums = arrayOf(7, 3, 3, 4, 5, 9, 1)
    println("Sorted Array: ${nums.sortedArray().contentToString()}")
    println("Sorted Array (Desc): ${nums.sortedArrayDescending().contentToString()}")

    nums.sort()
    println("In place sort: ${nums.contentToString()}")

    val cars = arrayOf("Ford", "Chevrolet", "Jaguar", "Audi", "BMW")

    println(nums.sortBy { it })

    val sortedCarList = cars.sortedWith(object : Comparator<String> {
        override fun compare(o1: String?, o2: String?): Int {
            return if (o2 != null) o1?.compareTo(o2)!! else null ?: 1
        }
    })

    println(sortedCarList)

    // Simplified
    val same = cars.sortedWith(Comparator { o1, o2 -> if (o2 != null) o1?.compareTo(o2)!! else null ?: 1 })
    println("With simplified Comparator: $sortedCarList")


    // Comparators can be easily created by factory methods such as compareBy that takes in a selector function
    // and returns a Comparator instance.
    val helperResult = cars.sortedWith(compareBy { it })
    println("Ascending sort by name: $helperResult")

    // Use compareByDescending for reverse ordered Comparator
    val reversed = cars.sortedWith(compareByDescending {it.length})
    println("Descending sort by length: $reversed")

    // CompareBy can also take multiple selector functions to make more complex Comparators
    // Multiple sorting rules (Complex)
    val phones = arrayOf(
        Phone("Pixel", 800),
        Phone("iPhone", 1100),
        Phone("One Plus", 600),
        Phone("iPhone", 1000)
    )
    // Sort by Name and then by price (if names are same)
    val sortByNameAndPrice = phones.sortedWith(compareBy({it.name}, {it.price}))
    println("Sorted by name and price: $sortByNameAndPrice")

    // Comparator Creation using Factory methods
    // Supports all built-in dataytypes, custom types must implement Comparable interface
    val simpleComparator = naturalOrder<String>()
    println(cars.sortedWith(simpleComparator))

    val simpleComparatorReversed = reverseOrder<String>()
    println(cars.sortedWith(simpleComparatorReversed))

    // Handle nulls
    // nullsFirst/nullsLast takes in a comparator and decortes it. Default impl also available
    val pairList = listOf(Pair(1, null), Pair(2, "b"), Pair(3, null), Pair(4, "d"))
    val sortedPairs = pairList.sortedWith(nullsFirst(compareBy {it.second}))
    println("Sorted Pairs (Nulls First): $sortedPairs")

    // Extend comparator
    val studentsPairList = mutableListOf(21 to "Helen", 21 to "Tom", 20 to "Jim")

    val ageComparator = compareBy<Pair<Int, String?>> {it.first}
    val ageAndNameComparator = ageComparator.thenByDescending {it.second}
    println(studentsPairList.sortedWith(ageAndNameComparator))

    val numsList = listOf(5, 20, 1, 2, 15)
    println(numsList.sorted())

}

data class Phone(val name: String, val price: Int)