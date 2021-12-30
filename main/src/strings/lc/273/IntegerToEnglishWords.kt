package strings.lc.`273`

class IntegerToEnglishWords {
    // We read 3 digits from the back, process it and adjust it cleverly in the StringBuilder.
    // https://leetcode.com/problems/integer-to-english-words/discuss/70625/My-clean-Java-solution-very-easy-to-understand
    private val LESS_THAN_20 = arrayOf(
        "",
        "One",
        "Two",
        "Three",
        "Four",
        "Five",
        "Six",
        "Seven",
        "Eight",
        "Nine",
        "Ten",
        "Eleven",
        "Twelve",
        "Thirteen",
        "Fourteen",
        "Fifteen",
        "Sixteen",
        "Seventeen",
        "Eighteen",
        "Nineteen"
    )
    private val TENS = arrayOf("", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")
    private val THOUSANDS = arrayOf("", "Thousand", "Million", "Billion")

    fun numberToWords(num: Int): String {
        var num = num
        if (num == 0) return "Zero"
        val sb = StringBuilder()
        var index = 0
        while (num > 0) {
            if (num % 1000 > 0) {
                // Step 2: Process 3 or less digits at a time
                val tmp = StringBuilder()
                helper(tmp, num % 1000)
                // Step 3: Very tricky to get this sequence right, trial and error
                // THOUSANDS[0] plays a big role, it is empty. index is used in a very
                // clever way.
                // eg. One Million "Two Hundred Thirty Four" Thousand "Five Hundred Sixty Seven"
                // Parts in Double quotes come from helper(), the rest is manipulated here based on index
                sb.insert(0, tmp.toString().trim { it <= ' ' } + " " + THOUSANDS[index] + " ")
            }
            num /= 1000
            index++
        }
        return sb.toString().trim { it <= ' ' }
    }

    // Step 1: Implement this method. It will process 3 digits at a time recursively
    // and will insert the result in StringBuilder
    private fun helper(sb: StringBuilder, num: Int) {
        if (num == 0) {
            return
        } else if (num < 20) {
            sb.append(LESS_THAN_20[num])
        } else if (num < 100) {
            sb.append(TENS[num / 10]).append(" ")
            helper(sb, num % 10)
        } else {
            sb.append(LESS_THAN_20[num / 100]).append(" Hundred ")
            helper(sb, num % 100)
        }
    }
}