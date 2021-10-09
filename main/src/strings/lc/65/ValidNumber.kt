package strings.lc.`65`

class ValidNumber {
    /**
     * Runtime: O(N)
     * Space: O(1)
     */
    fun isNumberRulesBasedApproach(s: String): Boolean {
        var seenDigit = false
        var seenExponent = false
        var seenDot = false
        s.forEachIndexed { i, curr ->
            if (Character.isDigit(curr)) {
                seenDigit = true
            } else if (curr == '+' || curr == '-') {
                if (i > 0 && s[i - 1] != 'e' && s[i - 1] != 'E') {
                    return false
                }
            } else if (curr == 'e' || curr == 'E') {
                if (seenExponent || !seenDigit) {
                    return false
                }
                seenExponent = true
                seenDigit = false // Very important to reset otherwise input "21e" will be processed as valid
            } else if (curr == '.') {
                if (seenDot || seenExponent) {
                    return false
                }
                seenDot = true
            } else {
                return false
            }
        }

        return seenDigit
    }
}