package com.example.flores.compras

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flores.compras.adapter.Lista_Compras
import com.example.flores.compras.adapter.Agregar_producto
import java.util.Locale

class Inicio : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    var Lista_C = mutableListOf<Lista_Compras>()
    lateinit var recyclerView:RecyclerView

    private var idiomaActual: String = "es"
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        mediaPlayer = MediaPlayer.create(this, R.raw.precionar)

        recyclerView = findViewById<RecyclerView>(R.id.Lista_p)
        initRecyclerView()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun agregar() {
        // Implementar lógica para agregar
        mediaPlayer.start()
        val nombreProducto = getString(R.string.producto)
        val cantidadProducto = getString(R.string.cantidad)
        val precioProducto = getString(R.string.precio)
        val item = Lista_Compras(nombreProducto,cantidadProducto,precioProducto)
        Lista_C.add(item)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun guardar() {
        // Implementar lógica para guardar
        mediaPlayer.start()
        val guardarProducto = getString(R.string.guardar)
        Toast.makeText(this,guardarProducto,Toast.LENGTH_SHORT).show()
    }

    private fun cambiarIdioma() {
        mediaPlayer.start()
        // Implementar lógica para cambiar el idioma
        idiomaActual = if (idiomaActual == "es") {
            // Si el idioma actual es español, cambiar a inglés
            actualizarIdioma("en")
            "en"
        } else {
            // Si el idioma actual no es español (puede ser inglés u otro), cambiar a español
            actualizarIdioma("es")
            "es"
        }

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
    private fun actualizarIdioma(idioma: String){
        val recursos = resources
        val displayMetrics = recursos.displayMetrics
        val configuracion = resources.configuration
        configuracion.setLocale(Locale(idioma))
        recursos.updateConfiguration(configuracion, displayMetrics)
        configuracion.locale = Locale(idioma)
        resources.updateConfiguration(configuracion, displayMetrics)

        recreate()
    }
}