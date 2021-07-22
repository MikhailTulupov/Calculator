package mikhail.tulupov.application.calculator.model

import mikhail.tulupov.application.calculator.extensions.toTokens
import java.lang.IndexOutOfBoundsException

class Calculator {

    companion object {
        val instance = Calculator()

    }

    private lateinit var tokens: ArrayList<String>
    private var pos: Int = 0

    fun calculate(expression: String): String {
        tokens = String().toTokens(expression)
        pos = 0
        val result = parse()
        return when {
            result.toString().endsWith("0") -> result.toInt().toString()
            result.isNaN() -> "Can`t divide by zero"
            result.isInfinite() -> "Can`t divide by zero"
            else -> result.toString()
        }
    }

    // method parse str array and return Double result
    private fun parse(): Double = expression()

    // E -> T+-T+-T+-...+-T
    private fun expression(): Double {
        var first = term()
        while (pos < tokens.size) {
            val operator = tokens[pos]

            if(operator != "+" && operator != "-")
                break
            else
                pos++

            val second: Double

            try {
                second = term()
            } catch (exc: IndexOutOfBoundsException) {
                return first
            }

            if(operator == "+")
                first += second
            else
                first -= second
        }
        return first
    }

    // T -> F*/F*/F*/...*/F
    private fun term(): Double {
        var first = factor()

        while (pos < tokens.size) {
            val operator = tokens[pos]

            if(operator != "*" && operator != "/")
                break
            else
                pos++

            val second: Double

            try {
                second = factor()
            } catch (exc: IndexOutOfBoundsException) {
                return first
            }

            if(operator == "*")
                first *= second
            else
                first /= second
        }
        return first
    }

    // F -> N
    private fun factor(): Double {
        val next = tokens[pos]

        if(next == "-" && pos - 1 == -1) {
            pos += 2
            return -tokens[pos - 1].toDouble()
        }
        pos++
        return  next.toDouble()
    }

    fun percentNum(str: String): Double = str.toDouble().div(100)
}