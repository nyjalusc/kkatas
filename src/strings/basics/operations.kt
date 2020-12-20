package strings.basics

/**
 * String operation basics
 */
fun main() {
    // 1. joinToString
    // Reverse every word
    val str = "Hi my name is Foo"
    println(str.split(" ").map { it.reversed() }.joinToString(" "))

    // Better solution using joinToString with a lambda that acts like a map
    println(str.split(" ").joinToString(" ") { it.reversed() })

    // 2. replace
    println("Foo is replaced: ${str.replace("Foo", "Bar")}")

    // 3. split() can have multiple delimiters
    val multiDelimiter = "Hi-this-is_a_multi_delimited-string"
    println(multiDelimiter.split("-", "_"))

    // 4. Check if given string is a substring of another string
    val s1 = "alike"
    val s2 = "like"
    println("Is \"like\" a substring of \"alike\": ${s1.contains(s2)}")

    // 5. windowed
    val nums = "1234567890"
    println(nums.windowed(3, 3, true))

    // 6. Partition
    val partitionMe = "hello"
    val partitionPair = partitionMe.partition { it == 'e' }
    println(partitionPair)

    // 7.
    val charA = 'a'
    println("Last index of $charA in string: $str is: " + str.indexOfLast { it == charA })

    val set = hashSetOf<Char>()
}

fun compressWord(word: String, k: Int): String {
    var output = word
    var index: Int
    while (output.windowed(k).indexOfFirst { it.allCharactersEqual }.also { index = it } != -1) {
        output = output.removeRange(index, index + k)
    }
    return output
}

val String.allCharactersEqual get() = all { it == this[0] }