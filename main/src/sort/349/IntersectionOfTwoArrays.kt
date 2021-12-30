package sort.`349`

import java.util.*


class IntersectionOfTwoArrays {
    /**
     * Using Two sets. Load arr1 in a set, iterate over arr2 to check if element is present in the set.
     * Use result as a set to avoid dups.
     * Time: O(m + n)
     * Space: O(m + n)
     */
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray? {
        val set = HashSet<Int>()
        val result = HashSet<Int>()
        for (i in nums1) {
            set.add(i)
        }
        for (i in nums2) {
            if (set.contains(i)) {
                result.add(i)
            }
        }
        return result.toIntArray()
    }

    // Follow up: Solve it under these constraints: O(n) time and O(1) space (the resulting array of intersections is not taken into consideration).
    // You are told the lists are sorted.
    fun intersectionFollowup(nums1: IntArray, nums2: IntArray): IntArray? {
        Arrays.sort(nums1)
        Arrays.sort(nums2)
        val overlaps: MutableList<Int> = ArrayList()
        var idx1 = 0
        var idx2 = 0
        while (idx1 < nums1.size && idx2 < nums2.size) {
            // Iterating the first pointer is enough. We do not need a check on the second pointer
            if (idx1 != 0 && nums1[idx1] == nums1[idx1 - 1]) {
                idx1++
                continue
            }
            if (nums1[idx1] == nums2[idx2]) {
                overlaps.add(nums1[idx1])
                idx1++
                idx2++
            } else if (nums1[idx1] < nums2[idx2]) {
                idx1++
            } else {
                idx2++
            }
        }
        return overlaps.stream().mapToInt { i: Int? -> i!! }.toArray()
    }
}