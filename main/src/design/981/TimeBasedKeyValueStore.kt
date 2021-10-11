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