package graphs.`127`

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashSet
import kotlin.collections.List
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.isNotEmpty
import kotlin.collections.listOf
import kotlin.collections.mutableSetOf


class WordLadder {

    /**
     * Time: O(M * N) - where M is the length word and N is the number of words in the list
     * Space: O(M * N)
     */
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>?): Int {
        val wordSet: Set<String> = HashSet(wordList)
        if (!wordSet.contains(endWord)) return 0
        if (beginWord == endWord) return 1
        val queue = ArrayDeque<String>()
        queue.add(beginWord)
        var level = 1
        val visited: MutableSet<String> = HashSet()
        visited.add(beginWord)
        var nodesAtNextLevel = 0
        var nodesAtCurrentLevel = 1
        while (!queue.isEmpty()) {
            val word = queue.removeFirst()
            nodesAtCurrentLevel--
            for (i in word.indices) {
                val chars = word.toCharArray()
                var c = 'a'
                while (c <= 'z') {
                    chars[i] = c
                    val node = String(chars)
                    if (node == endWord) return level + 1
                    if (!visited.contains(node) && wordSet.contains(node)) {
                        visited.add(node)
                        queue.add(node)
                        nodesAtNextLevel++
                    }
                    c++
                }
            }
            if (nodesAtCurrentLevel == 0) {
                level++
                nodesAtCurrentLevel = nodesAtNextLevel
                nodesAtNextLevel = 0
            }
        }
        return 0
    }

    fun ladderLengthKt(beginWord: String, endWord: String, wordList: List<String>): Int {
        if(!wordList.contains(endWord)) return 0
        if(beginWord == endWord) return 1

        val visitedSet = HashSet<String>()
        val queue = ArrayDeque<String>().also { it.addLast(beginWord) }
        var currentLevel = 1
        var nodesAtCurrentLevel = queue.size
        var nodesAtNextLevel = 0

        while (queue.isNotEmpty()) {
            val word = queue.removeFirst()
            for (i in word.indices) {
                val wordCharArray = word.toCharArray()
                for (character in 'a'..'z') {
                    wordCharArray[i] = character
                    val node = String(wordCharArray) // toString() doesn't work


                    // Found the result
                    if(node == endWord) return currentLevel + 1

                    // Otherwise add the word to visited set if its a valid word
                    if (!visitedSet.contains(node) && wordList.contains(node)) {
                        visitedSet.add(node)
                        queue.addLast(node)
                        nodesAtNextLevel++
                    }
                }
            }
            nodesAtCurrentLevel--
            if (nodesAtCurrentLevel == 0) {
                nodesAtCurrentLevel = nodesAtNextLevel
                nodesAtNextLevel = 0
                currentLevel++
            }
        }
        return 0
    }


}

fun main() {
    val solution = WordLadder()
    val result = solution.ladderLength("hit", "cog", listOf("hot","dot","dog","lot","log","cog"))
    println(result)
}