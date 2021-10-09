package design.`706`

class MyHashMap {

    /**
     * Don't create List<List>> because we want the entire array created to access the index
     * By using Array<Bucket>(size), we can access the bucket based on index which is obtained by the modulo operator based hash function
     * We are using modulo operator because the key is integer
     *
     * We'll get a max of 10000 elements but traditionally hashmaps are rehashed after they are 75% full, ie. a load-factor
     * of 0.75 is used for rebalancing. In this process, they size is doubled and all the keys will be rehashed, it is an
     * expensive operation so we should try to reduce it as much as possible
     * https://stackoverflow.com/questions/10901752/what-is-the-significance-of-load-factor-in-hashmap
     *
     */
    private val size = 13000
    private val buckets = Array(size) { Bucket() }

    fun hash(key: Int) = key % size

    fun put(key: Int, value: Int) {
        val index = hash(key)
        val bucket = buckets[index]
        bucket.put(key, value)
    }

    fun get(key: Int): Int {
        val index = hash(key)
        return buckets[index].find(key)
    }

    fun remove(key: Int) {
        val index = hash(key)
        buckets[index].remove(key)
    }

    private class Bucket {
        val list = mutableListOf<Pair>()

        fun put(key: Int, value: Int) {
            val pair = list.find { it.first == key }
            if(pair == null) list.add(Pair(key, value))
            else pair.second = value
        }

        fun find(key: Int): Int {
            val pair = list.find { it.first == key }
            return pair?.second ?: -1
        }

        fun remove(key: Int) {
            list.removeIf { it.first == key }
        }
    }

    private data class Pair(val first: Int, var second: Int)
}

