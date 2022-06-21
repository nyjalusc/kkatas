import java.text.SimpleDateFormat
import java.util.*

/**
 * [1,2,2,3,null,null,3,4,null,null,4]
           1
       2       2
    3    x    x  3
  4  x          x  4
 */

fun main() {
//    var arr = arrayOf(2, 4, 6, 7, 8)
//    println(arr.binarySearch(3))
//
//    val sortMe = arrayOf(arrayOf(5, 10), arrayOf(0, 100), arrayOf(3, 7))
//    val sortedListByFirst = sortMe.sortedBy { it.first() }
//    sortedListByFirst.forEach { print(it.contentToString()) }
//    println("\n----------")
//
//    val sortedArrayByLast = sortMe.sortedArrayWith(compareBy { it.last() })
//    sortedArrayByLast.forEach { print(it.contentToString())}
//    println("\n----------")
//
//    sortMe.sortBy { it.average() }
//    sortMe.forEach { print(it.contentToString())}
//
//    val pqSample = arrayOf(intArrayOf(3, 2, 7), intArrayOf(3, 7, 9), intArrayOf(8, 3, 9))
//    val pq = PriorityQueue<IntArray>(compareBy { it.last() })
//    pqSample.sortBy { it[1] }
//    pqSample.map { println("\nSorted: ${it.contentToString()}") }
//    pq.addAll(pqSample)
//    while (pq.isNotEmpty()) println("\nPQ: ${pq.poll().contentToString()}")

//    val w = arrayOf(1, 2, 3)
//    val wSums = w.runningReduce { acc, i -> acc + i}
//    println(wSums)
//
//    val word = "Wow"
//    val out = List(word.length + 1) { word.take(it) }
//    println(out)
//
//    val pq = PriorityQueue<Int>()
//
//
//    val list = mutableListOf<Pair<Int, Int>>()
//    val key = 1
//    val found = list.find { it.first == key }

    val s = "Hello, this; has spaces"
    s.map { if(Character.isLetterOrDigit(it)) it else "" }.joinToString("").also { println(it) }

}

fun createZipArchiveFileName(): String {
    val formattedDate = createFormattedDate()
    val uuid = UUID.randomUUID().toString().replace("-", "")
    return "${uuid}_${formattedDate}.zip"
}

fun createFormattedDate(): String {
    val DATE_FORMAT_STR = "yyyy-MM-dd_HH'h'_mm'm'_ss's'"
    val simpleDateFormat = SimpleDateFormat(DATE_FORMAT_STR, Locale.getDefault())
    return simpleDateFormat.format(Date())
}