import java.text.SimpleDateFormat
import java.util.*


fun main() {
    for (i in 0 until 5 step 2) println(i)
    println("123".toInt())

    println(createZipArchiveFileName())

    println("%s/%s".format("hello", "world"))

    val x = Int.MAX_VALUE/2
    println(x)


    val heap = PriorityQueue<Int>(3, naturalOrder())
    val reverseHeap = PriorityQueue<Int>(3, reverseOrder())

    (1..5).forEach {
        heap.add(it)
        if (heap.size > 3) {
            println("Size limit exceeded, evicted smallest")
        }
        reverseHeap.add(it)
    }

    println("Heap Peek: ${heap.peek()}")
    while (heap.isNotEmpty()) {
        println("Heap Poll: ${heap.poll()}")
    }

    println("Reverse Peek: ${reverseHeap.peek()}")
    while (reverseHeap.isNotEmpty()) {
        println("ReverseHeap Poll: ${reverseHeap.poll()}")
    }
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