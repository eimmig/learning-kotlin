package br.edu.utfpr.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import br.edu.utfpr.tiptime_2023_2.R
import br.edu.utfpr.tiptime_2023_2.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView(binding.root )

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }

        binding.costOfService.setOnKeyListener(View.OnKeyListener{ view, i, _ -> handleKeyEvent(view, i)

        })

        if (savedInstanceState != null) {
            val tipResult = savedInstanceState.getString("tip_result")
            binding.tipsResult.text = tipResult
        } else {
            binding.tipsResult.text = getString(R.string.tip_amount_s, "0")

        }
    }

    private fun handleKeyEvent(view: View?, keycode: Int): Boolean {
        if (keycode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
            return true
        } else {
            return false
        }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()

        val cost = stringInTextField.toDoubleOrNull() ?: return

        val tipPercentage = when (binding.tipOption.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else ->  0.15
        }

        var tip = cost * tipPercentage

        val roundUp = binding.roundUpSwitch.isChecked

        if ( roundUp ) {
            tip = kotlin.math.ceil( tip )
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipsResult.text = getString(R.string.tip_amount_s, formattedTip)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("tip_result", binding.tipsResult.text.toString())
    }
}