package design.`981`

import java.util.*


/**
 * Simple solution using TreeMap, all operations are O(log N)
 * Some key TreeMap operations to keep in mind:
 * 1. map.firstKey() or map.firstEntry() - get the smallest key/entry
 * 2. map.lastKey() or map.lastEntry() - get the largest key/entry
 * 3. map.floorKey/Entry(val) - gets the largest key/entry smaller than the input val
 * 4. map.ceilKey/Entry(val) - gets the largest key/entry larger than the input val
 */
class TimeBasedKeyValueStore() {
    private var map: MutableMap<String, TreeMap<Int, String>> = HashMap()

    fun set(key: String, value: String, timestamp: Int) {
        if (!map.containsKey(key)) {
            map[key] = TreeMap()
        }
        map[key]!![timestamp] = value
    }

    fun get(key: String, timestamp: Int): String? {
        val treeMap = map[key] ?: return ""
        val floor = treeMap.floorKey(timestamp) ?: return ""
        return treeMap[floor]
    }
}

class TimeMapWithTreeMap() {
    /**
     * In-memory key-value store with timestamped entries.
     *
     * This data structure maintains a mapping of keys to sorted timestamp-value pairs,
     * allowing efficient retrieval of the latest value associated with a key at a specific
     * timestamp or earlier.
     *
     * Time Complexity:
     *   - set: O(log n), where n is the number of timestamps for the given key.
     *   - get: O(log n), where n is the number of timestamps for the given key.
     *
     * Space Complexity: O(n * m), where n is the number of keys and m is the average
     * number of timestamps per key.
     */
    private val dataStore = mutableMapOf<String, TreeMap<Int, String>>()

    /**
     * Stores a new value for a key at a specific timestamp.
     *
     * This function uses an atomic get-or-put approach to retrieve the existing
     * TreeMap for the given key, or create a new one if it doesn't exist. Then, it
     * inserts the new value under the provided timestamp while maintaining the sorted
     * order of timestamps within the TreeMap.
     *
     * Time Complexity: O(log n), where n is the number of timestamps for the given key.
     */
    fun set(key: String, value: String, timestamp: Int) {
        val timeStore = dataStore.getOrPut(key) { TreeMap<Int, String>() }
        timeStore[timestamp] = value
    }

    /**
     * Retrieves the latest value associated with a key at a specific timestamp or earlier.
     *
     * This function leverages the floorEntry method of the TreeMap to efficiently find
     * the entry with the largest timestamp less than or equal to the provided timestamp.
     * If found, it returns the associated value. Otherwise, it returns an empty string.
     *
     * Time Complexity: O(log n), where n is the number of timestamps for the given key.
     */
    fun get(key: String, timestamp: Int): String {
        return dataStore.get(key)?.floorEntry(timestamp)?.value ?: ""
    }

}

/**
 * Using custom binary search implementation. The input time is always sorted
 * so the resulting list stored is also by default sorted. Hence it is easy to apply binary search over the list
 * using time as a key
 */
class Data(var value: String, var time: Int)
class TimeMap {
    var map: MutableMap<String, MutableList<Data>> = HashMap()

    fun set(key: String, value: String, timestamp: Int) {
        val list = map.getOrPut(key) { ArrayList() }
        list.add(Data(value, timestamp))
    }

    fun get(key: String, timestamp: Int): String {
        return if (!map.containsKey(key)) "" else binarySearch(map[key]!!, timestamp)
    }

    private fun binarySearch(list: List<Data>, time: Int): String {
        var low = 0
        var high = list.size - 1
        while (low < high) {
            val mid = low + (high - low) / 2
            if (list[mid].time == time) return list[mid].value
            if (list[mid].time < time) {
                // If the next value is larger then this is the best result we can ahve
                if (list[mid + 1].time > time) return list[mid].value
                low = mid + 1
            } else high = mid - 1
        }
        return if (list[low].time <= time) list[low].value else ""
    }
}

