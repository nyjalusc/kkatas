package design.`380`

import kotlin.random.Random

/**
 * We could've solved the problem by simply using a HashSet, but then the second part requires supporting duplicate values.
 * That is where it gets tricky
 *
 * We managed an ArrayList to keep track of all input values, coupled with a map of <Value, index>
 * remove() is the tricky part where in order to achieve O(1) complexity, we overwrite the value being
 * deleted in the arraylist with the last element (swap) and then remove the last element from the list,
 * update its index in the map, and remove the key for the value that needs to be deleted.
 */
class RandomizedSet() {
    private val list = ArrayList<Int>()
    // value -> index in the list
    val map = mutableMapOf<Int, Int>()

    val random = Random(1)

    fun insert(`val`: Int): Boolean {
        if(map.containsKey(`val`)) return false
        list.add(`val`)
        map[`val`] = list.lastIndex
        return true
    }

    fun remove(`val`: Int): Boolean {
        val value = `val`
        if(!map.containsKey(value)) return false
        val index = map[value]!!
        if (index < list.lastIndex) {
            list[index] = list.last()
            map[list.last()] = index
        }
        list.removeAt(list.lastIndex)
        map.remove(value)
        return true
    }

    fun getRandom(): Int = list[random.nextInt(list.size)]
}

fun main() {
    val randomizedSet = RandomizedSet()
    /**
    ["RandomizedSet","insert","insert","remove","insert","remove","getRandom"]
    [[],[0],[1],[0],[2],[1],[]]
     */
    println(randomizedSet.insert(0))
    println(randomizedSet.insert(1))
    println(randomizedSet.remove(0))
    println(randomizedSet.insert(2))
    println(randomizedSet.remove(1))
    println(randomizedSet.getRandom())

    // [null,true,true,true,true,true,2]
}