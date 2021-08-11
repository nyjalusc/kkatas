package arrays.lc.lc1089


/**
 * https://leetcode.com/problems/duplicate-zeros/
 */
class DuplicateZeroes {
    /**
     * Time: O(n)
     * Space: O(n/2) (worst case)
     * Use a queue to keep track of the elements that are being overwritten.
     * Poll elements from queue to be processed later.
     */
    fun solve(arr: IntArray): Unit {
        val queue = ArrayDeque<Int>()
        var i = 0
        var num = -1
        while(i < arr.size) {
            queue.addLast(arr[i])
            num = queue.removeFirst()
            arr[i] = num
            if (num == 0) {
                if (i + 1 < arr.size) {
                    i++
                    queue.addLast(arr[i])
                    arr[i] = 0
                }
            }
            i++
        }
    }

    fun duplicateZeros(arr: IntArray): Unit {
        val deque = ArrayDeque<Int>()

        for (i in arr.indices) {
            if (arr[i] == 0) {
                deque.add(arr[i])
                if (!deque.isEmpty()) arr[i] = deque.removeFirst()
            } else {
                if (deque.isEmpty()) continue
                deque.add(arr[i])
                arr[i] = deque.removeFirst()
            }
        }
    }
}