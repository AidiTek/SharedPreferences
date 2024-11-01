package com.example.sharedpreferences

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val saredPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editer = saredPref.edit()

        binding.btnSave.setOnClickListener {
            try {
                val name = binding.edName.text.toString()
                val age = binding.edAge.text.toString().toInt()
                val isAdult = binding.checkBox.isChecked

                editer.apply() {
                    putString("name", name)
                    putInt("age", age)
                    putBoolean("isAdult", isAdult)
                    apply()

                }
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    this,
                    "Ошибка: введенная значение в поле \"Age\" не являетсья числом.",
                    Toast.LENGTH_LONG
                ).show()
            }


        }

        binding.btnLoad.setOnClickListener {
            val name = saredPref.getString("name", null)
            val age = saredPref.getInt("age", 0)
            val isAdult = saredPref.getBoolean("isAdult", false)

            binding.edName.setText(name)
            binding.edAge.setText(age.toString())
            binding.checkBox.isChecked = isAdult
        }

    }
}