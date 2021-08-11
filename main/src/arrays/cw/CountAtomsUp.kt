package arrays.cw

/**
 *
 */
class CountAtomsUp {
    fun getAtoms(formula: String): Map<String, Int> {
        val result = mutableMapOf<String, Int>()
        val numbers = mutableListOf(1)
        var atom = ""; var digit = ""

        for (char in formula.reversed()) {
            when {
                char == ')' || char == ']' || char == '}' -> {
                    numbers.add(digit.toIntOrNull() ?: 1)
                    digit = ""
                }
                char == '(' || char == '[' || char == '{' -> numbers.removeAt(numbers.lastIndex)
                char.isDigit() -> digit = "$char$digit"
                char.isLowerCase() -> atom = "$char$atom"
                char.isUpperCase() -> {
                    atom = "$char$atom"
                    val count = numbers.reduce(Int::times) * (digit.toIntOrNull() ?: 1)
                    result[atom] = result[atom]?.let { it + count } ?: count
                    atom = ""
                    digit = ""
                }
            }
        }
        return result.takeIf { it.isNotEmpty() } ?: throw IllegalArgumentException()
    }
}