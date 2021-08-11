package binarysearch.`852`

/**
 *
 */
class PeakIndexInMountainArray {
    /**
     * Given an array [0, 1, 2, 5, 3, 1] we can use condition: arr[i] < arr[i + 1] to create an array of true and false
     * [T, T, T, F, F, F] Goal is to indentify first index of first F. Classic Binary search problem. Using implemention
     * type 2 for this
     *
     * Time: O(log N)
     * Space: O(1)
     */
    fun peakIndexInMountainArray(arr: IntArray): Int {
        if(arr.size < 3) return -1

        var left = 0
        var right = arr.lastIndex
        while(left < right) {
            val mid = left + (right - left) / 2
            if(arr[mid] <= arr[mid + 1]) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        return left
    }
}