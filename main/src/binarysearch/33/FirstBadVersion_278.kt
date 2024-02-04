package binarysearch.`33`

/**
 *
 */
class FirstBadVersion_278 {
    /**
     * My Attempt using classical style
     * Time: O(log N)
     * Space: O(1)
     */
    fun firstBadVersion2(n: Int) : Int {
        var start = 1
        var end = n
        var result = -1
        while (start <= end) {
            val mid = start + (end - start) / 2
            if(isBadVersion(mid)) {
                result = mid
                end = mid - 1
            } else {
                start = mid + 1
            }
        }
        return result
    }

    /**
     * Modified version without using temporary variable
     */
    fun firstBadVersion(n: Int) : Int {
        var start = 1
        var end = n
        while (start < end) {
            val mid = start + (end - start) / 2
            if(isBadVersion(mid)) {
                end = mid // if you do end = mid - 1 and if mid was pointing to the first bad version you'll never find it again
            } else {
                start = mid + 1
            }
        }
        return start // you can return end also because loop breaks when start == end
    }

    private fun isBadVersion(mid: Int): Boolean {
        TODO("Implementd ")
    }
}