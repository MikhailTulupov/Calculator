package mikhail.tulupov.application.calculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import mikhail.tulupov.application.calculator.R
import mikhail.tulupov.application.calculator.ui.fragments.SimpleCalculatorFragment

class CalculatorActivity : AppCompatActivity(R.layout.activity_calculator) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                /*
                optimizes the state changes of the fragments involved in the
                transaction so that animations and transitions work correctly
                 */
                setReorderingAllowed(true)
                add<SimpleCalculatorFragment>(R.id.fragment_container_view)
            }
        }
    }
}