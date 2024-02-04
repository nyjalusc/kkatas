package maps.lc.`383`

class RansomNote {
    /**
     * Create char count map using magazine. Process ransomNote by decrementing char count map.
     * If at any point count for char is < 0 then it means magazine does not enough char to crate the ransomNote
     * Time: O(M + N), Space: O(M)
     */
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val map = HashMap<Char, Int>()

        magazine.forEach {
            map[it] = map.getOrElse(it) { 0 } + 1
        }

        ransomNote.forEach {
            val charCount = map.getOrElse (it) { 0 }
            if (charCount == 0) return false
            map[it] = charCount - 1
        }
        return true
    }
}