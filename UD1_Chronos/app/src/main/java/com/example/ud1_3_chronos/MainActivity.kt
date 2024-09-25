package com.example.ud1_3_chronos

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val RUNNING_KEY= "running"
    val OFFSET_KEY= "offset"
    val BASE_KEY= "base"

    lateinit var chrono: Chronometer //iniciar variable mas tarde
    var running = false
    var offset = 0L

    //Este método almacena el estado de la aplicación cuando cambia de estado (girar, ...)
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(RUNNING_KEY, running)
        outState.putLong(OFFSET_KEY, offset)
        outState.putLong(BASE_KEY, chrono.base)
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Empezamos el código desde aquí

        //Inicializamos cronometro
        chrono = findViewById<Chronometer>(R.id.chrTemporizador)

        //Para girar el movil
        if (savedInstanceState != null){
            offset = savedInstanceState.getLong(OFFSET_KEY)
            running = savedInstanceState.getBoolean(RUNNING_KEY)
            if(running){
                chrono.base = savedInstanceState.getLong(BASE_KEY)
                chrono.start()
            }else{
                chrono.base = SystemClock.elapsedRealtime() - offset
            }
        }
        //Funcionalidad Botones
        val btnStart= findViewById<Button>(R.id.btnStart)
        btnStart.setOnClickListener{
            if(!running){
                chrono.base = SystemClock.elapsedRealtime() - offset
                chrono.start()
                running = true
            }
        }
        val btnStop=findViewById<Button>(R.id.btnPause)
        btnStop.setOnClickListener{
            if(running){
                chrono.stop()
                offset = SystemClock.elapsedRealtime() - chrono.base
                running = false
            }
        }
        val btnRestart = findViewById<Button>(R.id.btnRestart)
        btnRestart.setOnClickListener{
            offset = 0L
            chrono.base = SystemClock.elapsedRealtime()
            chrono.stop()

        }
    }

    //Aplicacion pasa a segundo plano
    override fun onStop() {
        //SI estaba encendido, lo paro y guardo la variable offset
        if(running){
            offset = SystemClock.elapsedRealtime() - chrono.base
            chrono.stop()
        }
        super.onStop()
    }

    //Aplicacion vuelve a primer plano
    override fun onRestart() {
        //Si estaba encendido lo vuelvo a ejecutar
        if(running){
            chrono.base= SystemClock.elapsedRealtime() - offset
            chrono.start()
        }
        super.onRestart()
    }

    //Pierde el foco
    override fun onPause() {
        if(running){
            offset = SystemClock.elapsedRealtime() - chrono.base
            chrono.stop()
        }
        super.onPause()
    }
}