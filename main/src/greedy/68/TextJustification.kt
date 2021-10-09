package greedy.`68`

class TextJustification {
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        val result = mutableListOf<String>()
        var i = 0
        while (i < words.size) {
            var j = i + 1
            var lineLength = words[i].length

            /**
             * j is exclusive pointer, linelength does not consider the word currently pointed by j.
             * j - i gives the number of words counted in lineLength and j - i - 1, gives the total number
             * gaps between words already counted
             */
            while (j < words.size && (lineLength + (j - i - 1) + words[j].length < maxWidth)) {
                lineLength += words[j].length
                j++
            }

            // number of spaces we need to apply to the line
            val diff = maxWidth - lineLength
            val numOfWords = j - i

            // Left justify
            if (numOfWords == 1 || j >= words.size) {
                result.add(leftJustify(words, diff, i, j))
            } else {
                // Middle Justify
                result.add(middleJustify(words, diff, i, j))
            }
            i = j
        }

        return result
    }

    private fun middleJustify(words: Array<String>, diff: Int, i: Int, j: Int): String {
        val spacesNeeded = j - i - 1
        val spaces = diff / spacesNeeded
        var extraSpaces = diff % spacesNeeded

        val sb = StringBuilder(words[i]) // init
        var k = i + 1
        while (k < j) {
            var spacesToApply = spaces
            if (extraSpaces > 0) {
                spacesToApply++
                extraSpaces--
            }

            repeat(spacesToApply) {
                sb.append(" ")
            }
            sb.append(words[k])
            k++
        }
        return sb.toString()
    }

    private fun leftJustify(words: Array<String>, diff: Int, i: Int, j: Int): String {
        val spacesNeededOnRight = diff - (j - i - 1)
        val sb = StringBuilder(words[i]) // init
        var k = i + 1
        while (k < j) {
            sb.append(" " + words[k])
            k++
        }

        repeat(spacesNeededOnRight) {
            sb.append(" ")
        }

        return sb.toString()
    }
}