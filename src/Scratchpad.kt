import java.text.SimpleDateFormat
import java.util.*


fun main() {
    val arr = arrayOf(2, 4, 6, 7, 8)
    println(arr.binarySearch(3))

    val sortMe = arrayOf(arrayOf(5, 10), arrayOf(0, 100), arrayOf(3, 7))
    val sortedListByFirst = sortMe.sortedBy { it.first() }
    sortedListByFirst.forEach { print(it.contentToString()) }
    println("\n----------")

    val sortedArrayByLast = sortMe.sortedArrayWith(compareBy { it.last() })
    sortedArrayByLast.forEach { print(it.contentToString())}
    println("\n----------")

    sortMe.sortBy { it.average() }
    sortMe.forEach { print(it.contentToString())}
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