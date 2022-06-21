package slidingwindow.`239`

class SlidingWindowMaximum {
    /**
     * We use a deque for keeping the max elements in a window. First element is the max element in the window
     * where as other elements could possibly become max elements in the future.
     *
     * When processing new element if it is larger than any of the existing elements in the queue (ie. window) then
     * its obvious they'll never become the max elements so we can safely remove them.
     *
     * While sliding the window make sure you remove the element from queue if it was the max element
     * Time: O(N)
     * Space: O(K)
     */
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val result = mutableListOf<Int>()
        val queue = ArrayDeque<Int>()
        var i = 0
        var j = 0
        while (j < nums.size) {
            // Calculation, values smaller than incoming element will never be included in the result
            // we can safely remove them
            while (queue.isNotEmpty() && queue.last() < nums[j]) queue.removeLast()
            queue.addLast(nums[j++])

            // Window size reached
            if (j - i == k) {
                // Get the computed result
                result.add(queue.first())

                // Slide the window and adjust the calculated queue
                if(nums[i] == queue.first()) queue.removeFirst()
                i++
            }
        }

        return result.toIntArray()
    }
}