package stack.`156`

class EvaluateReversePolish_156 {
    fun evalRPN(tokens: Array<String>): Int {
        val stack = ArrayDeque<Int>()
        val operatorsSet = setOf("+", "-", "*", "/")
        for (token in tokens) {
            if (operatorsSet.contains(token)) {
                val operandB = stack.removeFirst()
                val operandA = stack.removeFirst()

                when (token) {
                    "+" -> stack.addFirst(operandA + operandB)
                    "-" -> stack.addFirst(operandA - operandB)
                    "*" -> stack.addFirst(operandA * operandB)
                    "/" -> stack.addFirst(operandA / operandB)
                }
            } else stack.addFirst(token.toInt())
        }
        return stack.first()
    }
}