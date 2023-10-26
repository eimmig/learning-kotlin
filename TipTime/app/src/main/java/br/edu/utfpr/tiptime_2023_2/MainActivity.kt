package br.edu.utfpr.tiptime_2023_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.utfpr.tiptime_2023_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView(binding.root )

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }

    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()

        val selectedId = binding.tipOption.checkedRadioButtonId
        val tipPercentage = when ( selectedId ) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else ->  0.15
        }

        var tip = cost * tipPercentage

        val roundUp = binding.roundUpSwitch.isChecked

        if ( roundUp ) {
            tip = kotlin.math.ceil( tip )
        }

        //como valorizar o TextView que está com %s???????
    }
}