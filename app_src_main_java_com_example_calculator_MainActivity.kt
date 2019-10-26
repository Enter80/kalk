package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var spinner: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner
    private lateinit var result: TextView
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText2 = findViewById(R.id.editText2)
        editText3 = findViewById(R.id.editText3)

        result = findViewById(R.id.result)
        btn = findViewById(R.id.resultBtn)
        createSpinner()
        buttonAction()
    }

    fun createSpinner(){
        spinner = findViewById(R.id.spinner)
        val spinnerAdapter = ArrayAdapter.createFromResource(this,
            R.array.types, android.R.layout.simple_spinner_item)

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        spinner2 = findViewById(R.id.spinner2)
        val spinnerAdapter2 = ArrayAdapter.createFromResource(this,
            R.array.function, android.R.layout.simple_spinner_item)

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = spinnerAdapter2
        spinner3 = findViewById(R.id.spinner3)

        val spinnerAdapter3 = ArrayAdapter.createFromResource(this,
            R.array.result, android.R.layout.simple_spinner_item)

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner3.adapter = spinnerAdapter3

    }
    fun convertFromAnyToDecimal(num: Int,from:Double): Int {
        var num = num
        var decimalNumber = 0
        var i = 0
        var remainder: Int

        while (num.toInt() != 0) {
            remainder = num % 10
            num /= 10
            decimalNumber += (remainder * Math.pow(from, i.toDouble())).toInt()
            ++i
        }
        return decimalNumber
    }
    fun convertFromDecimalToAny(n: Int,to:Int): Int {
        var n = n
        var binaryNumber: Int = 0
        var remainder: Int
        var i = 1
        var step = 1

        while (n != 0) {
            remainder = n % to
            n /= to
            binaryNumber += (remainder * i).toInt()
            i *= 10
        }
        return binaryNumber
    }
    fun buttonAction(){
        btn.setOnClickListener{
            var firstText = editText2.text.toString().toInt()
            var secondText = editText3.text.toString().toInt()
            var text = ""
            var finalResult = 0
            when(spinner.selectedItemPosition){
                0 -> text = whatToDo(firstText,secondText,2.0)
                1 -> text = whatToDo(firstText,secondText,3.0)
                2 -> text = whatToDo(firstText,secondText,4.0)
                3 -> text = whatToDo(firstText,secondText,5.0)
                4 -> text = whatToDo(firstText,secondText,6.0)
                5 -> text = whatToDo(firstText,secondText,7.0)
                6 -> text = whatToDo(firstText,secondText,8.0)
                7 -> text = whatToDo(firstText,secondText,9.0)
                8 -> text = whatToDo(firstText,secondText,10.0)
            }

            when(spinner3.selectedItemPosition){
                0 -> finalResult = convertFromDecimalToAny(text.toInt(),2)
                1 -> finalResult = convertFromDecimalToAny(text.toInt(),3)
                2 -> finalResult = convertFromDecimalToAny(text.toInt(),4)
                3 -> finalResult = convertFromDecimalToAny(text.toInt(),5)
                4 -> finalResult = convertFromDecimalToAny(text.toInt(),6)
                5 -> finalResult = convertFromDecimalToAny(text.toInt(),7)
                6 -> finalResult = convertFromDecimalToAny(text.toInt(),8)
                7 -> finalResult = convertFromDecimalToAny(text.toInt(),9)
                8 -> finalResult = convertFromDecimalToAny(text.toInt(),10)
            }

            var res = convertFromDecimalToAny(editText2.text.toString().toInt(),2)
            result2.text = res.toString()
            result.text = finalResult.toString()

            var res1 = convertFromDecimalToAny(editText3.text.toString().toInt(),2)
            result3.text = res1.toString()
            result.text = finalResult.toString()
        }
    }
    fun whatToDo(firstNumber:Int,secondNumber:Int,numeral:Double):String{
        var result = ""
        var firstNumber = convertFromAnyToDecimal(firstNumber,numeral)
        var secondNumber = convertFromAnyToDecimal(secondNumber,numeral)

        when(spinner2.selectedItemPosition){
            0 -> result = (firstNumber + secondNumber).toString()
            1 -> result = (firstNumber - secondNumber).toString()
            2 -> result = (firstNumber * secondNumber).toString()
            3 -> result = (firstNumber / secondNumber).toString()
        }
        return result
    }

}