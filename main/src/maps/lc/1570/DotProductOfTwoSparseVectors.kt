package maps.lc.`1570`


/**
 * SOLUTION 1: Start exploring solutions in the order mentioned here
 * Store input array as is, do not filter anything. Compute by simply iterating over the smaller array
 */
class DotProductOfTwoSparseVectorsUsingArray(nums: IntArray) {
    val input = nums

    fun dotProduct(vec: DotProductOfTwoSparseVectorsUsingArray): Int {
        val iterate = if (input.size < vec.input.size) input else vec.input
        return iterate.indices.fold(0) { acc, index -> acc + input[index] * vec.input[index] }
    }
}


/**
 * SOLUTION #2: NOT ACCEPTED, EXPECTED TO IMPLEMENT IT QUICKLY
 *
 * When dealing with really large maps, the map internally uses an array which provides a contiguous block of memory
 * for buckets. Each bucket is a reference to an object called Entry which wraps the Key and value. This entry object
 * may or may not be in the memory, hence a cache miss. Fetching values from large hashmaps can cause lot of random access,
 * resulting in cache miss. On the other hand, if an array is used, it will be contiguous. In case of a cache miss,
 * next page is load loaded. With the array solution cache misses are minimized.
 *
 * Preprocess the input to create a map<index, value> to only store non-zero index-value pairs.
 * Iterate over the smaller map and lookup the same index as a key in the second map. If it is found compute
 * otherwise skip OR use intersect() as shown below to find the common indices (keys) in both maps
 */
class DotProductUsingMap(nums: IntArray) {

    val map = mutableMapOf<Int, Int>()

    init {
        nums.forEachIndexed { index, value -> if (value != 0) map[index] = value }
    }

    // Return the dotProduct of two sparse vectors
    fun dotProduct(vec: DotProductUsingMap): Int {
        // var sum = 0
        // map.keys.toList().forEach { if (vec.map.containsKey(it)) sum += map[it]!! * vec.map[it]!!  }
        val commonIndices = map.keys.intersect(vec.map.keys)
        return commonIndices.fold(0) { acc, index -> acc + (map[index]!! * vec.map[index]!!) }
        // return sum
    }
}


/**
 * SOLUTION #3: ACCEPTABLE FOR THE FIRST PART NOT FOLLOWUP

Input processed to store nonzero values as List<IndexedValue<Int>>. IndexedValue is a Kotlin class initialized
as IndexedValue(index, value)
iterate through the nonZeroValues lists of both vectors simultaneously with two pointers, i and j.
It compares the indices of the current non-zero elements:
If the indices match, it multiplies the values and adds the result to sum.
If the index of the current element in this vector is less than the index in vec, it increments
i to move to the next element in this vector.
If the index of the current element in vec is less than in this vector, it increments j.

Time Complexity: O(N + M), where N is the number of non-zero elements in the first vector and
M is the number of non-zero elements in the second vector. In the worst case, we iterate through
all non-zero elements of both vectors exactly once.

Space Complexity: O(N + M) for storing the non-zero elements of both vectors. Note that this space is
already allocated when the vectors are instantiated, so the dotProduct function itself uses O(1)
additional space.
 */
class DotProductUsingArrayList(nums: IntArray) {

    private val nonZeroValues = mutableListOf<IndexedValue<Int>>()

    init {
        nums.forEachIndexed { index, value ->
            if (value != 0) nonZeroValues.add(IndexedValue(index, value))
        }
    }

    fun dotProduct(vec: DotProductUsingArrayList): Int {
        var i = 0
        var j = 0
        var sum = 0

        while (i < nonZeroValues.size && j < vec.nonZeroValues.size) {
            val currentIndexThis = nonZeroValues[i].index
            val currentIndexVec = vec.nonZeroValues[j].index

            when {
                currentIndexThis == currentIndexVec -> {
                    sum += nonZeroValues[i].value * vec.nonZeroValues[j].value
                    i++
                    j++
                }
                currentIndexThis < currentIndexVec -> i++
                else -> j++
            }
        }

        return sum
    }
}


/**
 * THIS IS THE ONLY ACCEPTABLE SOLUTION TO THE FOLLOWUP QUESTION
 *
 * In kotlin, there is a data structure IndexValue which acts like a Pair(Index, Value).
 * I am using List<IndexedValue<Int>> instead of List<List<Int>> but the concept is the same.
 *
 * If the followup question is what if one of the list is smaller than the other, then we can optimize the solution
 * by iterating over just the smaller list and performing binary search over the larger list to find the element.
 * An alternate could be to create a set of index values from the larger list and use contains() of the set, but that
 * will be at and additional space cost.
 * Time Complexity: The dot product operation's time complexity is O(N log M), where N is the number of non-zero elements
 * in the first vector and M is the number of non-zero elements in the second vector. For each of the N elements in the first vector,
 * a binary search is performed on the M elements of the second vector.
 *
 * Space Complexity: The space complexity remains O(N + M) for storing the non-zero elements of both vectors.
 * The dotProduct function itself uses O(1) additional space, not counting the space used to store the non-zero elements.
 */
class DotProductUsingBinarySearch(nums: IntArray) {

    private val nonZeroValues = mutableListOf<IndexedValue<Int>>()

    init {
        nums.forEachIndexed { index, value ->
            if (value != 0) nonZeroValues.add(IndexedValue(index, value))
        }
    }

    fun dotProduct(vec: DotProductUsingBinarySearch): Int {
        var sum = 0
        val smallVec = if (this.nonZeroValues.size < vec.nonZeroValues.size) this else vec
        val largeVec = if (smallVec == vec) this else vec

        // Iterate over the small vector
        for ((index, value) in smallVec.nonZeroValues) {
            val vecIndex = largeVec.binarySearchForIndex(index)
            if (vecIndex >= 0) { // Found a match
                sum += value * largeVec.nonZeroValues[vecIndex].value
            }
        }
        return sum
    }

    private fun binarySearchForIndex(targetIndex: Int): Int {
        var low = 0
        var high = nonZeroValues.size - 1

        while (low <= high) {
            val mid = low + (high - low) / 2
            val midIndex = nonZeroValues[mid].index

            when {
                midIndex == targetIndex -> return mid
                midIndex < targetIndex -> low = mid + 1
                else -> high = mid - 1
            }
        }
        return -1 // Not found
    }
}