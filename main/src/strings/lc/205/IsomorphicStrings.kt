package strings.lc.`205`


class IsomorphicStrings {
    fun isIsomorphic(s: String, t: String): Boolean {
        val map: MutableMap<Char, Char> = HashMap()
        val set: MutableSet<Char> = HashSet()
        for (i in s.indices) {
            val a = s[i]
            val b = t[i]
            if (map.containsKey(a)) {
                if (map[a] != b) {
                    return false
                }
            } else {
                if (set.contains(b)) return false
                map[a] = b
                set.add(b)
            }
        }
        return true
    }
}