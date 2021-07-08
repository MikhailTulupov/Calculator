package mikhail.tulupov.application.calculator.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import mikhail.tulupov.application.calculator.R
import mikhail.tulupov.application.calculator.databinding.FragmentSimpleCalculatorBinding

class SimpleCalculatorFragment : Fragment() {

    private lateinit var binding: FragmentSimpleCalculatorBinding
    private lateinit var numBtnMap: Map<String, ImageButton>
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
            "zero" to binding.ibNumberZero,
            "one" to binding.ibNumberOne,
            "two" to binding.ibNumberTwo,
            "three" to binding.ibNumberThree,
            "four" to binding.ibNumberFour,
            "five" to binding.ibNumberFive,
            "six" to binding.ibNumberSix,
            "seven" to binding.ibNumberSeven,
            "eight" to binding.ibNumberEight,
            "nine" to binding.ibNumberNine
        )

        for ((number, view) in numBtnMap) {
            view.setOnClickListener {
                numbersText.text = numbersText.text.append(addNumber(number))
            }
        }

        return binding.root
    }

    //method return equal number to key
    private fun addNumber(number: String): Char = when (number) {
        "zero" -> '0'
        "one" -> '1'
        "two" -> '2'
        "three" -> '3'
        "four" -> '4'
        "five" -> '5'
        "six" -> '6'
        "seven" -> '7'
        "eight" -> '8'
        else -> '9'
    }
}