package com.example.flores.compras

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity

class Biemvenida : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biemvenida)
        mediaPlayer = MediaPlayer.create(this, R.raw.inicio)
        VerBienvenida()
    }
    fun VerBienvenida() {
        object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {
                mediaPlayer.start()
            }
            override fun onFinish() {
                mediaPlayer.release()
                val intent = Intent(this@Biemvenida, Inicio::class.java)
                startActivity(intent)
                finishAffinity()
            }
        }.start()
    }
}