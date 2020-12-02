package arrays.basics

import java.util.*

/**
 * Array<T> is a typed Array maps to T[] (boxed) on JVM (eg. Integer[], String[] etc)
 * whereas primitive types are represented as *Type*Array (where type could be double, float, long, int, char, short, byte, boolean)
 * so the actual kotlin primitive types are IntArray, FloatArray, BooleanArray etc.
 */
fun main() {
    // Using arrayOf(), null is allowed
    val num: Array<Int?> = arrayOf(1, 2, 3, null)
    println(num.contentToString())

    // Init empty array
    val empty: Array<String> = emptyArray<String>()
    println(empty.contentToString())

    // Late initialization with indices
    val numWithNulls: Array<Int?> = arrayOfNulls<Int>(2)
    for (i in numWithNulls.indices) {
        if (i == 0) numWithNulls[i] = null
        else numWithNulls[i] = 1
    }
    println(numWithNulls.contentToString())

    // maps to int[], nulls are obviously not allowed
    val intArray: IntArray = intArrayOf(1, 2, 3)
    println(intArray.contentToString())

    // Generate values with init lambda
    // i starts with 0, imagine all the indexes being passed
    val generatedArray = IntArray(10) { index -> index * index }
    println(generatedArray.contentToString())

    val generatedStringArray = Array(10) { i -> "Number of index: $i" }
    println(generatedStringArray.contentToString())

    // Initialize from range
    val rangeIntArray = (1..5).toList().toIntArray()
    println(rangeIntArray.contentToString())

    val rangeTypedIntArray = (1..5).toList().toTypedArray()
    println(rangeTypedIntArray.contentToString())

    // Multi-dimensional array
    val multiArray = arrayOf(arrayOf(1, 2), arrayOf(3, 4, 5, 6))
    for (arr in multiArray) {
        for (num in arr)
            print("$num ")
    }
    println()

    for (i in 0 until multiArray.size)
        for (j in 0 until multiArray[i].size)
            print("${multiArray[i][j]} ")
    println()

    for (i in multiArray.indices)
        for (element in multiArray[i])
            print("$element ")
    println()

    val multiIntArray = Array(5) { r -> IntArray(3) {i -> i * i} }

}