package com.example.flores.compras

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class Inicio : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    companion object {
        lateinit var Elementos: TextView
        var contadorElementos: Int = 0
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        mediaPlayer = MediaPlayer.create(this, R.raw.precionar)
        Elementos = findViewById<TextView>(R.id.Muestra_cantidad)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        Elementos.text= getString(R.string.CP) + contadorElementos.toString()
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_ir_a_lista -> {
                mediaPlayer.start()
                val intent = Intent(this@Inicio, Lista::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}