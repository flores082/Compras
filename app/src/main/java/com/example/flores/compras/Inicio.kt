package com.example.flores.compras

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flores.compras.adapter.Lista_Compras
import com.example.flores.compras.adapter.Agregar_producto
import java.util.Locale

class Inicio : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private var isSpanish = true
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        mediaPlayer = MediaPlayer.create(this, R.raw.precionar)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_menu,menu)
        return true
    }
    private fun toggleLanguage() {
        isSpanish = !isSpanish
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
    /*private fun initRecyclerView(){
        val recyclerView: RecyclerView = findViewById(R.id.Lista_p)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = Agregar_producto(Lista_C)
        recyclerView.adapter = adapter
    }*/
}