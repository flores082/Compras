package com.example.flores.compras

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
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
    var idiomaActual : String = "es"

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
        /*val nombreProducto = getString(R.string.producto)
        val cantidadProducto = getString(R.string.cantidad)
        val precioProducto = getString(R.string.precio)
        val item = Lista_Compras(nombreProducto,cantidadProducto,precioProducto)
        Lista_C.add(item)
        recyclerView.adapter?.notifyDataSetChanged()*/
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
        if (idiomaActual != "es"){
            idiomaActual = "es"
        }
        else {
            idiomaActual = "en"
        }
        LocaleHelper.setLocale(this,idiomaActual)
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
    object LocaleHelper{
        fun setLocale(context: Context, language: String){
            val newLocale = Locale(language)
            Locale.setDefault(newLocale)
            val resources = context.resources
            val configuration = Configuration(resources.configuration)
            configuration.setLocale(newLocale)
            resources.updateConfiguration(configuration, resources.displayMetrics)
            configuration.locale = newLocale
            resources.updateConfiguration(configuration, resources.displayMetrics)

            /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                configuration.setLocale(newLocale)
            }
            else{
                configuration.locale = newLocale
            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                context.createConfigurationContext(configuration)
            }
            else {
                resources.updateConfiguration(configuration, resources.displayMetrics)
            }*/

            (context as Activity).recreate()
        }
    }
}