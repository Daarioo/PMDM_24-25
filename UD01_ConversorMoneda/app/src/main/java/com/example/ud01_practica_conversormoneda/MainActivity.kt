package com.example.ud01_practica_conversormoneda

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dolares = findViewById<EditText>(R.id.Dolar)
        val resultado = findViewById<TextView>(R.id.Euro)
        val DolarenEuros= 0.89

        val btnConvertir = findViewById<Button>(R.id.Conversion)
        btnConvertir.setOnClickListener{
            val dolaresInput = dolares.text.toString().toDouble()
            val euros = (DolarenEuros * dolaresInput )
            resultado.setText(euros.toString())
        }
    }
}