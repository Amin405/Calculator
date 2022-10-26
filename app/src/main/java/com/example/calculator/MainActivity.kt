package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Numbers
        binding.zero.setOnClickListener { appendOnExpression("0",true) }
        binding.one.setOnClickListener { appendOnExpression("1",true) }
        binding.two.setOnClickListener { appendOnExpression("2",true) }
        binding.three.setOnClickListener { appendOnExpression("3",true) }
        binding.four.setOnClickListener { appendOnExpression("4",true) }
        binding.five.setOnClickListener { appendOnExpression("5",true) }
        binding.six.setOnClickListener { appendOnExpression("6",true) }
        binding.seven.setOnClickListener { appendOnExpression("7",true) }
        binding.eight.setOnClickListener { appendOnExpression("8",true) }
        binding.nine.setOnClickListener { appendOnExpression("9",true) }
        binding.point.setOnClickListener { appendOnExpression(".",true) }

        //Operator
        binding.plus.setOnClickListener { appendOnExpression("+",false) }
        binding.minus.setOnClickListener { appendOnExpression("-",false) }
        binding.multiply.setOnClickListener { appendOnExpression("*",false) }
        binding.divide.setOnClickListener { appendOnExpression("/",false) }
        binding.openBracket.setOnClickListener { appendOnExpression("(",false) }
        binding.closeBracket.setOnClickListener { appendOnExpression(")",false) }

        binding.clear.setOnClickListener {
            clearExpressionField()
            clearResultField()
        }

        binding.equal.setOnClickListener {
            calculateTerm()
        }

        binding.remove.setOnClickListener{
           removeTerm()
        }


    }

    private fun getExpressionText(): String {
        return binding.expression.text.toString()
    }

    private fun clearResultField(){
        binding.result.text =""
    }

    private fun clearExpressionField(){
        binding.expression.text =""
    }


    private fun appendOnExpression(input: String, canClear:Boolean){
        if (binding.result.text.isNotEmpty()){
            clearExpressionField()
        }
        if (canClear){
            clearResultField()
            binding.expression.append(input)
        }else{
            binding.expression.append(binding.result.text)
            binding.expression.append(input)
            clearResultField()
        }
    }

    //Use ExpressionBuilder to calculate terms
    private fun calculateTerm(){
        try {
            val expression = ExpressionBuilder(binding.expression.text.toString()).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            if (result == longResult.toDouble()) {
                binding.result.text = longResult.toString()
            } else {
                binding.result.text = longResult.toString()
            }
        } catch (e: Exception) {
            Log.d("Exception", " message : " + e.message)
        }
    }

    private fun removeTerm(){
        val term = getExpressionText()
        if (term.isNotEmpty()){
            binding.expression.text = term.substring(0,term.length-1)
        }else {
            clearResultField()
        }
    }

}


