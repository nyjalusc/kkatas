package strings.lc.`8`

class StringToInteger {
    fun myAtoi(str: String?): Int {
        if (str == null || str.isEmpty()) return 0
        var result = 0L
        var sign = 0 // 0 means sign not parsed, 1 means positive, -1 means negative
        var nonWhiteSpaceFound = false
        for (i in str.indices) {
            // We have started processing digit so if we encounter anything that is not a digit will be skipped
            if (nonWhiteSpaceFound && !(str[i] in '0'..'9')) break
            if (str[i] == ' ') continue
            if (str[i] == '-') {
                if (sign != 0) return 0
                sign = -1
                nonWhiteSpaceFound = true
                continue
            } else if (str[i] == '+') {
                if (sign != 0) return 0
                sign = 1
                nonWhiteSpaceFound = true
                continue
            }
            if (str[i] in '0'..'9') {
                nonWhiteSpaceFound = true
                // isDigit
                result = result * 10 + str[i].toLong() - '0'.toLong()

                // Handle Overflow
                if (result > Int.MAX_VALUE) return if (sign >= 0) Int.MAX_VALUE else Int.MIN_VALUE
            } else {
                break
            }
        }
        return (if (sign >= 0) result else -result).toInt()
    }
}