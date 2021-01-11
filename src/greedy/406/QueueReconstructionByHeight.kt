package greedy.`406`

/**
 *
 */
class QueueReconstructionByHeight {
    /**
     *
     * Time: O(N * N): O(N Log N) for sorting + O(N^2) for insertion sort, we insert N items at a given index in a linked list.
     * Each insert is O(N).
     *
     * Space: O(N)
     */
    fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
        // Descending sort by tallest person, if there are multiple tall people perform secondary sort by number of people
        // in front of them.
        // Chaining two comparators to form a high level comparator
        val comparator = compareByDescending<IntArray> { it.first() }.then(compareBy { it.last() })
        people.sortWith(comparator)

        val list = mutableListOf<IntArray>()
        people.forEach {
            list.add(it.last(), it)
        }
        return list.toTypedArray()
    }
}