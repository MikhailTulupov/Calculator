package mikhail.tulupov.application.calculator.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import mikhail.tulupov.application.calculator.R
import mikhail.tulupov.application.calculator.databinding.FragmentSimpleCalculatorBinding
import java.lang.IndexOutOfBoundsException
import java.lang.StringBuilder

class SimpleCalculatorFragment : Fragment() {

    private lateinit var binding: FragmentSimpleCalculatorBinding
    private lateinit var numBtnMap: Map<Num, ImageButton>
    private lateinit var mathOperationsBtnMap: Map<Operation, ImageButton>
    private lateinit var numbersText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil
                .inflate(
                    inflater,
                    R.layout.fragment_simple_calculator,
                    container,
                    false
                )

        numbersText = binding.etNumber

        numBtnMap = mapOf(
            Num.ZERO to binding.ibNumberZero,
            Num.ONE to binding.ibNumberOne,
            Num.TWO to binding.ibNumberTwo,
            Num.THREE to binding.ibNumberThree,
            Num.FOUR to binding.ibNumberFour,
            Num.FIVE to binding.ibNumberFive,
            Num.SIX to binding.ibNumberSix,
            Num.SEVEN to binding.ibNumberSeven,
            Num.EIGHT to binding.ibNumberEight,
            Num.NINE to binding.ibNumberNine
        )

        for ((number, button) in numBtnMap) {
            button.setOnClickListener {
                when {
                    isZeroFirst() -> numbersText.setText(addNumber(number).toString())
                    isZeroStandBeforeOperator() -> numbersText.text
                    else -> numbersText.text = numbersText.text.append(addNumber(number))
                }
            }
        }

        mathOperationsBtnMap = mapOf(
            Operation.DIVIDE to binding.ibBtnDivide,
            Operation.MULTIPLE to binding.ibBtnMultiple,
            Operation.MINUS to binding.ibBtnMinus,
            Operation.PLUS to binding.ibBtnPlus
        )

        for ((operation, button) in mathOperationsBtnMap) {
            button.setOnClickListener {
                val symbol = when (operation) {
                    Operation.DIVIDE -> '/'
                    Operation.MULTIPLE -> '*'
                    Operation.MINUS -> '-'
                    Operation.PLUS -> '+'
                }

                numbersText.setText(
                    when {
                        isMathSymbolStands() -> StringBuilder(numbersText.text).deleteCharAt(
                            numbersText.length() - 1
                        ).append(symbol)
                        numbersText.text.isEmpty() -> numbersText.text.append('0').append(symbol)

                        else -> StringBuilder(numbersText.text).append(symbol).toString()
                    }
                )
            }
        }

        binding.apply {
            ibBtnClearAll.setOnClickListener {
                setEmptyText()
            }
            ibBtnBackspace.setOnClickListener {
                try {
                    numbersText.text =
                        numbersText.text.delete(numbersText.length() - 1, numbersText.length())
                } catch (exc: IndexOutOfBoundsException) {
                    setEmptyText()
                }

            }
        }

        return binding.root
    }

    private fun isZeroStandBeforeOperator(): Boolean =
        try {
            numbersText.text.endsWith('0')
                .and(numbersText.text[numbersText.length() - 2].code < 48)
        } catch (exc: IndexOutOfBoundsException) {
            false
        }

    private fun isMathSymbolStands(): Boolean {

        val mathOperations = charArrayOf('/', '*', '+', '-')

        val sb = StringBuilder(numbersText.text)

        for (operation in mathOperations) {
            try {
                if (sb[sb.length - 1] == operation)
                    return true
            } catch (exc: StringIndexOutOfBoundsException) {
                return false
            }
        }
        return false
    }

    private fun setEmptyText() {
        numbersText.setText("")
    }

    private fun isZeroFirst(): Boolean =
        (numbersText.length() == 1).and(numbersText.text.startsWith('0'))

    //method return equal number to key
    private fun addNumber(number: Num): Char = when (number) {
        Num.ZERO -> '0'
        Num.ONE -> '1'
        Num.TWO -> '2'
        Num.THREE -> '3'
        Num.FOUR -> '4'
        Num.FIVE -> '5'
        Num.SIX -> '6'
        Num.SEVEN -> '7'
        Num.EIGHT -> '8'
        Num.NINE -> '9'
    }

    private enum class Num {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE


    }

    private enum class Operation {
        DIVIDE,
        MULTIPLE,
        MINUS,
        PLUS
    }
}