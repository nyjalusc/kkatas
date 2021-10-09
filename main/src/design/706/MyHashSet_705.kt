package design.`706`

class MyHashSet_705 {
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
    private val buckets = Array<MutableList<Int>>(size) { mutableListOf() }

    private fun hash(key: Int) = key % size

    fun add(key: Int) {
        val index = hash(key)
        val bucket = buckets[index]
        if(!bucket.contains(key)) bucket.add(key)
    }

    fun remove(key: Int) {
        val index = hash(key)
        val bucket = buckets[index]
        bucket.remove(key)
    }

    fun contains(key: Int): Boolean {
        val index = hash(key)
        return buckets[index].contains(key)
    }

}
