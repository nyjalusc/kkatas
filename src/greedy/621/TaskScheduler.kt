package greedy.`621`

import java.util.*

/**
 *
 */
class TaskScheduler {
    fun leastIntervalMyBuggyImpl(tasks: CharArray, n: Int): Int {
        val map = mutableMapOf<Char, Int>()
        tasks.forEach {
            map[it] = map.getOrElse(it) {0} + 1
        }

        var start = 0
        val result = CharArray(tasks.size * (n + 1)) {'#'}
        while (map.isNotEmpty()) {
            val longestTask = map.maxBy { it.value }!!
            // Place at alternate positions
            for(i in start..result.lastIndex step (n + 1)) {
                if (map[longestTask.key]!! > 0) {
                    result[i] = longestTask.key
                    map[longestTask.key] = map[longestTask.key]!! - 1
                } else {
                    start = if(n == 0) {
                        (start + i) % result.size
                    } else {
                        if(result[start + 1] == '#') start + 1 else i - n
                    }
                    map.remove(longestTask.key)
                    break
                }
            }
        }

        println(result)

        var lastIndex = result.lastIndex
        for (i in result.lastIndex downTo 0) {
            if(result[i] != '#') {
                lastIndex = i
                break
            }
        }
        return lastIndex + 1
    }

    /**
     * Same solution as [358] and [767] https://leetcode.com/problems/rearrange-string-k-distance-apart/
     * Here we create interval of size n + 1. eg. if input is AAAABBBC with n = 2, it means A can be repeated every i + (n + 1)
     * position: A _ _ A _ _ A _ _ A, here A _ _ forms an interval and it has size 3.
     * Using the following algo we create one interval at a time and fill the blank spaces, it is possible that the interval
     * is not fully occupied, empty spaces will be idle units of time and should be counted as such.
     *
     * 1. Create a map of Char to Count
     * 2. Insert all the map entries into a PQ. It will sort entries by highest count (max heap)
     * 3. While PQ is non-empty do the following
     * 3a. Poll and get most frequent char and shrink the interval and increment the spaces counter.
     * 3b. Every map entry that is processed should be moved to a temp list so that we do not pick it again while
     * processing the current interval.
     * 3c. Once the current interval is fully processed, move the entries from list, adjust the freq value and add it back
     * to the queue if value of char count > 0.
     * 3d. At any point if queue becomes empty, do an early termination.
     * 3e. If interval > 0, it means there are idle units. Add it to the final space counter.
     *
     * Time: O(N)
     * Space: O(26) --> O(1), map entries will not grow beyond 26
     */
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val map = mutableMapOf<Char, Int>()
        tasks.forEach {
            map[it] = map.getOrElse(it) { 0 } + 1
        }

        val queue = PriorityQueue<Map.Entry<Char, Int>>(compareByDescending { it.value })
        queue.addAll(map.entries)

        var spaces = 0
        // This list is used to temporarily hold the characters which are already counted
        // After one interval is processed we'll re-insert these elements back in the queue if the value of entry > 0
        val list = mutableListOf<Map.Entry<Char, Int>>()
        while (queue.isNotEmpty()) {
            var intervalSize = n + 1

            while (queue.isNotEmpty() && intervalSize > 0) {
                val entry = queue.poll() as MutableMap.MutableEntry
                intervalSize--
                spaces++

                // Decrement the count
                entry.setValue(entry.value - 1)
                // Temporarily hold it in a separate queue so that we do not repeat it in the same interval
                list.add(entry)
            }

            // Add all valid entries from the list back into the queue
            list.forEach {
                if(it.value > 0) queue.add(it)
            }

            // Clear the temp list
            list.clear()

            // Exit if there are no more elements in the queue
            if(queue.isEmpty()) break

            // This means we have idle entries in our interval
            if(intervalSize > 0)
                spaces += intervalSize
        }

        return spaces
    }

}

fun main() {
    val sol = TaskScheduler()
    var input = charArrayOf('A', 'A', 'A', 'B', 'B', 'B')
//    println(sol.leastInterval(input, 0))

    input = charArrayOf('A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E')
    println(sol.leastIntervalMyBuggyImpl(input, 2))

}