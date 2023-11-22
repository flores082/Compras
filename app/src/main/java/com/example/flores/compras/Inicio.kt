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

        // Obtén el color del archivo colors.xml
        val colorBackground = ContextCompat.getColor(this, R.color.Blue)

        // Establece el color de fondo de la vista principal (puedes cambiar "miConstraintLayout" al ID de tu vista principal)
        findViewById<View>(R.id.relativeLayout).setBackgroundColor(colorBackground)


        mediaPlayer = MediaPlayer.create(this, R.raw.precionar)

        /*switch.setOnClickListener {
            if (switch.isChecked) {
                mediaPlayer.start()
                LocaleHelper.setLocale(this, "en")
            }
            else{
                LocaleHelper.setLocale(this, "es")
            }
        }*/

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
            R.id.idioma -> {
                mediaPlayer.start()
                toggleLanguage()
                val newLanguage = if (isSpanish) "es" else "en"
                changeLanguage(this, newLanguage)
                recreate()
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
    /*object LocaleHelper{
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
    }*/
    private fun changeLanguage(context: Context, language: String) {
        val newlocale = Locale(language)
        Locale.setDefault(newlocale)

        val configuration = Configuration()
        configuration.setLocale(newlocale)

        val resources = context.resources
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}