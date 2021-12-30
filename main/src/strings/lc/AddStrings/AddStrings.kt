package strings.lc.AddStrings

class AddStrings {
    fun addStrings(num1: String, num2: String): String {
        val res = StringBuilder()
        var carry = 0
        var p1 = num1.length - 1
        var p2 = num2.length - 1
        while (p1 >= 0 || p2 >= 0) {
            val x1 = if (p1 >= 0) num1[p1] - '0' else 0
            val x2 = if (p2 >= 0) num2[p2] - '0' else 0
            val sum = x1 + x2 + carry
            val value = sum % 10
            carry = sum / 10
            res.append(value)
            p1--
            p2--
        }
        if (carry != 0) res.append(carry)
        return res.reverse().toString()
    }

}