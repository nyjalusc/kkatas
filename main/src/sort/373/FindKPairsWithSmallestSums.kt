package sort.`373`

import com.sun.tools.javac.jvm.ByteCodes.ret
import java.util.*


class FindKPairsWithSmallestSums {
    /**
     * We take the first k elements of nums1 and paired with nums2[0] as the starting pairs so that we have
     * (0,0), (1,0), (2,0),.....(k-1,0) in the heap. Each time after we pick the pair with min sum, we put the new pair
     * with the second index +1. ie, pick (0,0), we put back (0,1). Therefore, the heap always maintains at
     * most min(k, len(nums1)) elements.
     */
    fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (nums1.isEmpty() || nums2.isEmpty()) return result
        val len1: Int = nums1.size
        val len2: Int = nums2.size

        val pq = PriorityQueue<IndexedPair>(compareBy { it.a + it.b })
        var i = 0
        while (i < len1 && i < k) {
            pq.add(IndexedPair(nums1[i++], nums2[0], 0))
        }

        var j = 0
        while (j < k && pq.isNotEmpty()) {
            val smallestSumPair = pq.poll()
            result.add(listOf(smallestSumPair.a, smallestSumPair.b))

            val nextIndex = smallestSumPair.index + 1
            if (nextIndex < len2) {
                pq.add(IndexedPair(smallestSumPair.a, nums2[nextIndex], nextIndex))
            }
            j++
        }
        return result
    }

    private data class IndexedPair(val a: Int, val b: Int, val index: Int)
}