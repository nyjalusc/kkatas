package maps.lc.`1570`


/**
 * https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
 *
 * Process the constructor input intArray to create a map of Index -> Value <Int, Int>
 * For dotProduct method, I am simplifying it by using intersect() over keys set from both the maps.
 * This will help me eliminate all the indices that are present in one map but absent in the other, which
 * will result into a product with 0 (this is wasteful computation).
 * Time: O(N)
 * Space: O(N)
 */
class DotProductOfTwoSparseVectors(nums: IntArray) {
    val map = mutableMapOf<Int, Int>()

    init {
        val map = mutableMapOf<Int, Int>()
        nums.indices.filter { nums[it] != 0 }.forEach { map[it] = nums[it] }
    }

    fun dotProduct(vec: DotProductOfTwoSparseVectors): Int {
        val intersectingKeys = map.keys.intersect(vec.map.keys)
        return intersectingKeys.fold(0) {acc, key ->  acc + (map.getOrDefault(key, 0) * vec.map.getOrDefault(key, 0))}
    }
}

/**
 * Alternate solution:
 * Mistake: I created two arrays one for value and one for index hoping I will access it as value[index[i]]
 * but this is not possible because the value array doesn't contain the relation with its index. They need to
 * be saved as List<List<Int>>, eg if the input is a huge matrix with just two non-zero values at index 0 and 3
 * it can be compressed to [[0, 2], [3, 5]], here value 2 is at iindex 0 and value 5 is at index 3.
 *
 * In kotlin, there is a datastructure IndexValue which acts like a Pair(Index, Value).
 * I am using List<IndexedValue<Int>> instead of List<List<Int>> but the concept is the same.
 *
 * If the followup question is what if one of the list is smaller than the other, then we can optimize the solution
 * by iterating over just the smaller list and performing binary search over the larger list to find the element.
 * An alternate could be to create a set of index values from the larger list and use contains() of the set, but that
 * will be at and additional space cost.
 * Runtime: O(m * log n)
 */
class DotProductOfTwoSparseVectorsAlternate(nums: IntArray) {

    private val _nonZeroIndexedValues = mutableListOf<IndexedValue<Int>>()
    val nonZeroIndexedValues: List<IndexedValue<Int>> = _nonZeroIndexedValues

    init {
        val map = mutableMapOf<Int, Int>()
        nums.withIndex().filter { it.value != 0 }.forEach {
            _nonZeroIndexedValues.add(it)
        }
    }

    fun dotProduct(vec: DotProductOfTwoSparseVectorsAlternate): Int {
        val input = vec.nonZeroIndexedValues
        val given = _nonZeroIndexedValues

        var sum = 0
        var i = 0
        var j = 0
        while (i < given.size && j < input.size) {
            when {
                given[i].index == input[j].index -> sum += given[i++].value * input[j++].value
                given[i].index < input[j].index -> i++
                else -> j++
            }
        }
        return sum
    }

    /**
     * Sample Binary search over List<IndexedValue<Int>>
     */
    private fun search(list: List<IndexedValue<Int>>, start: Int, end: Int, target: Int): Int {
        var start = start
        var end = end
        while (start + 1 < end) {
            val mid = start + (end - start) / 2
            val indexedValue = list[mid]
            if (indexedValue.index == target) {
                return mid
            } else if (indexedValue.index < target) {
                start = mid
            } else {
                end = mid
            }
        }
        return if (list[end].index == target) {
            end
        } else start
    }
}