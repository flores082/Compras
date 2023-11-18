package com.example.flores.compras

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flores.compras.ViewHolder.Lista_Compras
import com.example.flores.compras.adapter.Agregar_producto

class Inicio : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    var Lista_C = mutableListOf<Lista_Compras>()
    lateinit var recyclerView:RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        mediaPlayer = MediaPlayer.create(this, R.raw.precionar)

        initRecyclerView()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun agregar() {
        // Implementar lógica para agregar
        mediaPlayer.start()
        val item = Lista_Compras("Producto","Cantidad")
        Lista_C.add(item)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun guardar() {
        // Implementar lógica para guardar
        mediaPlayer.start()
        Toast.makeText(this,"Guardado",Toast.LENGTH_SHORT).show()
    }

    private fun cambiarIdioma() {
        mediaPlayer.start()
        // Implementar lógica para cambiar el idioma
    }
    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Guardar -> {
                guardar()
                return true
            }
            R.id.menu_agregar -> {
                agregar()
                return true
            }
            R.id.menu_cambiar_idioma -> {
                cambiarIdioma()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    private fun initRecyclerView(){
        val recyclerView: RecyclerView = findViewById(R.id.Lista_p)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = Agregar_producto(Lista_C)
        recyclerView.adapter = adapter
    }
}