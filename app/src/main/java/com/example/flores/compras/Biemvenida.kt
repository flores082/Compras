package com.example.flores.compras

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity

class Biemvenida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biemvenida)
        VerBienvenida()
    }
    fun VerBienvenida() {
        object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {
                val intent = Intent(this@Biemvenida, Inicio::class.java)
                startActivity(intent)
                finishAffinity()
            }
        }.start()
    }
}