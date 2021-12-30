package sort.`349`

class IntersectionOfTwoArraysII_350 {
    /**
     * Load array into a map of <Element, Count>. For optimization make sure you load the smaller array in memory.
     * Then iterate over the second array to find the intersection and update the map count for the value.
     * Time: O(m + n)
     * Space: O(m), where m < n
     *
     * Follow ups:
     * What if the given array is already sorted? How would you optimize your algorithm? -> Check solution below
     * What if nums1's size is small compared to nums2's size? Which algorithm is better? -> Map solution, load smaller one in memory
     * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
     * -> We could create subarrays and use two pointer solution, write the intermediate results on the disk and repeat
     */
    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        val list = ArrayList<Int>()
        val map = HashMap<Int, Int>()

//        val loadInMap = listOf(nums1, nums2).minBy { it.size }!!
        val loadInMap = listOf(nums1, nums2).minByOrNull { it.size }!!
        for (num in loadInMap) {
            val count = map.getOrPut(num) { 0 }
            map[num] = count + 1
        }

        val itrArray = if(loadInMap == nums1) nums2 else nums1
        for (num in itrArray) {
            if(map.containsKey(num)) {
                list.add(num)
                val count = map[num]
                if(count!! == 1) map.remove(num)
                else {
                    map[num] = count - 1
                }
            }
        }

        return list.toIntArray()
    }

    /**
     * What if both the arrays are sorted?
     * Use two pointer strategy.
     * Time: O(m log m) + O(n log n) + O(m) + O(n)
     * Space: O(m), where m < n
     * It can also be performed in O(1) if you store the result in one of the array (in-place)
     * Not ideal but it is possible and then use nums1.copyOfRange(fromIndex = 0, toIndex(exclusive) = k)
     */
    fun intersectFollowUp(nums1: IntArray, nums2: IntArray): IntArray {
        val result = mutableListOf<Int>()
        nums1.sort()
        nums2.sort()

        var i = 0
        var j = 0
        while (i < nums1.size && j < nums2.size) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i])
            } else if (nums1[i] < nums2[j]) i++
            else j++
        }
        return result.toIntArray()
    }
}