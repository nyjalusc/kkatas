package design.`380`

import kotlin.random.Random

/**
 * Minor bug in the code
 * Same as part 1 but you deal with set of indices here. If the set is empty after removal, the entry should be
 * removed from the map
 */
class RandomizedCollection_381 {
    private val list = ArrayList<Int>()

    // value -> set of all indices of the value in the list
    val map = mutableMapOf<Int, MutableSet<Int>>()

    val random = Random(1)

    fun insert(`val`: Int): Boolean {
        val value = `val`
        val result = map.containsKey(value)
        val set = map.getOrPut(value) { mutableSetOf() }
        list.add(value)
        set.add(list.lastIndex)
        return !result
    }

    fun remove(`val`: Int): Boolean {
        val value = `val`
        if(!map.containsKey(value)) return false
        val removeIndex = map[value]!!.first()

        // Swap & overwrite last element at the index of element value
        list[removeIndex] = list.last()

        println("Remove $value at $removeIndex")
        println("Placed ${list.last()} at $removeIndex")

        // Update map with new location of last element
        map[list[removeIndex]]!!.add(removeIndex)

        println("${list.last()} is now at ${map[list.last()]!!.joinToString { "," }}")

        // Remove index from set present in the map
        map[value]!!.remove(removeIndex)
        println("Updated indices of $value (removed) ${map[value]!!.joinToString { "," }}")

        // If the set is empty remove the key as well
        if(map[value]!!.isEmpty()) map.remove(value)

        // Resize the list to remove last element
        list.removeAt(list.lastIndex)

        println("New size of list: ${list.size}")

        return true
    }

    fun getRandom(): Int = list[random.nextInt(list.size)]

}

fun main() {
    val randomizedSet = RandomizedCollection_381()
    /**
    ["RandomizedCollection","insert","insert","remove","insert","remove","getRandom"]
    [[],[0],[1],[0],[2],[1],[]]

     */
    println(randomizedSet.insert(1))
    println(randomizedSet.insert(1))
    println(randomizedSet.remove(1))
    println(randomizedSet.insert(1))
    println(randomizedSet.remove(1))
    println(randomizedSet.getRandom())

    // [null,true,true,true,true,true,2]
}