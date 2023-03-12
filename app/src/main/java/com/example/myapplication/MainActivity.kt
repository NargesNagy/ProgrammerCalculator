package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

   // lateinit var numberEditText: EditText
    lateinit var neditText: TextInputEditText
    lateinit var binaryNumberTextView: TextView
    lateinit var decimalNumberTextView: TextView
    lateinit var octalNumberTextView: TextView
    lateinit var hexDecimalNumberTextView: TextView
    lateinit var spinner: Spinner
    lateinit var converterButton: Button
    lateinit var clearButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        var convertFrom = "Decimal"

        val customSpinnerList = listOf("Decimal", "Binary", "Octal", "Hex")
        val spinnerAdapter = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, customSpinnerList)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                convertFrom = adapterView?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        converterButton.setOnClickListener {
            if (convertFrom == "Decimal"){
                convertFromDecimal(neditText.text.toString())
            }else if (convertFrom == "Binary"){
                convertFromBinary(neditText.text.toString())
            }else if (convertFrom == "Octal"){
                convertFromOctal(neditText.text.toString())
            }else {
                convertFromHex(neditText.text.toString())
            }
        }

        clearButton.setOnClickListener {
            clearAll()
        }

    }

    fun initialize() {
        neditText = findViewById(R.id.editText_number)
        binaryNumberTextView = findViewById(R.id.binary_value_tv)
        decimalNumberTextView = findViewById(R.id.decimal_value_tv)
        octalNumberTextView = findViewById(R.id.octal_value_tv)
        hexDecimalNumberTextView = findViewById(R.id.hex_decimal_value_tv)
        spinner = findViewById(R.id.spinner)
        converterButton = findViewById(R.id.coverter_button)
        clearButton = findViewById(R.id.clear_button)
    }

    fun clearAll(){
        neditText.text?.clear()
        binaryNumberTextView.text = ""
        octalNumberTextView.text = ""
        hexDecimalNumberTextView.text = ""
        decimalNumberTextView.text = ""
    }

    fun convertFromDecimal(number: String) {
        if(! number.isNullOrEmpty() && number.length <= 8 ){ //  && 'a'..'z' in number)
            val num = number.toInt()
            var binary = Integer.toBinaryString(num)
            var octal = Integer.toOctalString(num)
            var hex = Integer.toHexString(num)

            binaryNumberTextView.text = binary
            octalNumberTextView.text = octal
            hexDecimalNumberTextView.text = hex
            decimalNumberTextView.text = number
        }else{
            Toast.makeText(this,"Please enter a valid number",Toast.LENGTH_SHORT).show()
        }

    }

    fun convertFromBinary(number: String)  {

//        var char = '2'..'9'
//        var charArray = listOf('a'..'z' , 0..9)
//        for ( i in 'a'..'z' ){
//            if(number.contains(i)){
//
//            }
//        }
//        if(number.contains('2')){
//
//        }
//        number.
        // from binary to decimal
        var num = number.toLong()
        var decimalNumber = 0
        var i = 0
        var reminder : Long

        while (num.toInt() != 0 ){
            reminder = num % 10
            num /= 10
            decimalNumber += (reminder * Math.pow(2.0 , i.toDouble())).toInt()
            i++
        }
        // from decimal to other systems
        var octal = Integer.toOctalString(decimalNumber)
        var hex = Integer.toHexString(decimalNumber)

        binaryNumberTextView.text = number
        octalNumberTextView.text = octal
        hexDecimalNumberTextView.text = hex
        decimalNumberTextView.text = decimalNumber.toString()
    }

    fun convertFromOctal(number: String)  {

        // from octal to decimal
        var num = number.toInt()
        var decimalNumber = 0
        var i = 0

        while (num != 0 ){
            decimalNumber += (num % 10 * Math.pow(8.0 , i.toDouble())).toInt()
            ++i
            num /= 10
        }
        // from decimal to other systems
        var binary = Integer.toBinaryString(decimalNumber)
        var hex = Integer.toHexString(decimalNumber)

        binaryNumberTextView.text = binary
        octalNumberTextView.text = number
        hexDecimalNumberTextView.text = hex
        decimalNumberTextView.text = decimalNumber.toString()
    }

    fun convertFromHex(number: String) {

        val decimal = Integer.parseInt(number,16)
        var binary = Integer.toBinaryString(decimal)
        var octal = Integer.toOctalString(decimal)

        binaryNumberTextView.text = binary
        octalNumberTextView.text = octal
        hexDecimalNumberTextView.text = number
        decimalNumberTextView.text = decimal.toString()

    }

}
