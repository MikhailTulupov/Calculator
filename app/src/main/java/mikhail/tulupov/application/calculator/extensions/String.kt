package mikhail.tulupov.application.calculator.extensions

import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.ArrayList

fun String.toTokens(expression: String): ArrayList<String> {
        val pattern: Pattern =
            Pattern.compile("((\\d+[.]\\d+)|(\\d+[.])|([.]\\d+)|(\\d+)|([+*-/])|([()]))")
        val matcher: Matcher = pattern.matcher(expression)
        val tokens = ArrayList<String>()
        while (matcher.find()) {
            when {
                Objects.requireNonNull(matcher.group(1)).endsWith(".") -> tokens.add(
                    matcher.group(1).toString() + "0"
                )
                Objects.requireNonNull(matcher.group(1))
                    .startsWith(".") -> tokens.add("0" + matcher.group(1))
                else -> tokens.add(matcher.group(1))
            }
        }

        return tokens
    }