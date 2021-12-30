package sort.`215`

import kotlin.random.Random
import kotlin.random.nextInt

class FindKthLargestElement {
    /**
     * Solution 1: Sort
     * Solution 2: Heap (using max heap of size N) OR optimized (use min Heap of size k)
     * Solution 3: Quick select, implemented below.
     * You randomly select an index and partition the array based on the value at that index. At the end you will know
     * where the array was partitioned. Use that info to see if that is the index where result will lie,
     * if you already found the resultIndex great otherwise infer whether to continue looking in the left/right partition
     * Time: O(N)
     * Space: O(1)
     */
    fun findKthLargest(nums: IntArray, k: Int): Int {
        // This tells us once the array is sorted at which index the result will be located
        val resultIndex = nums.size - k

        var left = 0
        var right = nums.lastIndex
        val random = Random(0)
        while (left <= right) {
//            val pivotIndex = random.nextInt((right - left) + 1) + left
            // We pick a random index and the value at that index will partition the array
            // Elements smaller than nums[partitionIndex] will be on the left and larger on the right
            // Meaning we already know the final position of the value, it won't move, it could also be our result
            // since we already know where the result will lie (resultIndex = nums.size - k)
//            val randPivotIndex = random.nextInt(left..right)
            val randPivotIndex = random.nextInt((right - left) + 1) + left
            val partitionIndex = partition(nums, left, right, randPivotIndex)
            // Check if this is the location of result, if yes then we know the result
            if (resultIndex == partitionIndex) {
                return nums[resultIndex]
            } else if (partitionIndex < resultIndex) {
                // result is in the right partition
                left = partitionIndex + 1
            } else {
                // result is in the left partition
                right = partitionIndex - 1
            }
        }
        return -1
    }

    /**
     * Quick Select Algorithm: Partitions array elements between left..right indices based on nums[pivotIndex] value
     */
    private fun partition(nums: IntArray, left: Int, right: Int, pivotIndex: Int): Int {
        val pivotValue = nums[pivotIndex]
        // Move the pivotValue at the last location so that we can process elments from left..(right - 1), our pivot
        // element is at nums[right] after swap
        swap(nums, pivotIndex, right)

        var i = left
        for (j in left until right) {
            // If nums[j] < pivotvalue we swap.
            // Pointer will keep track of the partition index, at the end this is where we perform partition
            if (nums[j] < pivotValue) {
                swap(nums, i, j)
                i++
            }
        }
        // Perform partition by inserting the indexValue at position i
        swap(nums, i, right)
        return i
    }

    private fun swap(nums: IntArray, index1: Int, index2: Int) {
        val temp = nums[index1]
        nums[index1] = nums[index2]
        nums[index2] = temp
    }



    fun kthLargestUpsolve(arr: IntArray, k: Int): Int {
        val n = arr.size
        var left = 0
        var right = n - 1
        val rand = Random(0)
        while (left <= right) {
//            val choosenPivotIndex: Int = rand.nextInt(right - left + 1) + left
            val choosenPivotIndex: Int = rand.nextInt(left..right)
            val finalIndexOfChoosenPivot = partitionUpsolve(arr, left, right, choosenPivotIndex)

            // What does the 'finalIndexOfChoosenPivot' tell us?
            if (finalIndexOfChoosenPivot == n - k) {
                /*
         * Found. The pivot is index on index n - k. This is literally its final
         * position if the array we were given had been sorted. See how we saved work?
         * We don't need to sort the whole array
         */
                return arr[finalIndexOfChoosenPivot]
            } else if (finalIndexOfChoosenPivot > n - k) {
                /*
         * k'th largest must be in the left partition. We "overshot" and need to go left
         * (and we do this by narrowing the right bound)
         */
                right = finalIndexOfChoosenPivot - 1
            } else {
                /*
         * finalIndexOfChoosenPivot < n - k
         *
         * k'th largest must be in the right partition. We "undershot" and need to go
         * right (and we do this by narrowing the left bound)
         */
                left = finalIndexOfChoosenPivot + 1
            }
        }
        return -1
    }

    private fun partitionUpsolve(arr: IntArray, left: Int, right: Int, pivotIndex: Int): Int {
        val pivotValue = arr[pivotIndex]
        // i represents lesserItemsTailIndex
        var i = left

        /*
     * Move the item at the 'pivotIndex' OUT OF THE WAY. We are about to bulldoze
     * through the partitioning space and we don't want it in the way
     */
        swapUpsolve(arr, pivotIndex, right)
         // j keeps moving, i is also incremented after swap()
        for (j in left until right) {
            if (arr[j] < pivotValue) {
                swapUpsolve(arr, j, i)
                i++
            }
        }

        /*
     * Ok...partitioning is done. Swap the pivot item BACK into the space we just
     * partitioned at the 'lesserItemsTailIndex'...that's where the pivot item
     * belongs
     *
     * In the middle of the "sandwich".
     */
        swapUpsolve(arr, right, i)
        return i
    }

    private fun swapUpsolve(arr: IntArray, first: Int, second: Int) {
        val temp = arr[first]
        arr[first] = arr[second]
        arr[second] = temp
    }
}

fun main() {
    val solution = FindKthLargestElement()
    val input = intArrayOf(3,2,1,5,6,4)
    println(solution.findKthLargest(input, 2))
}