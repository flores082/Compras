package com.example.flores.compras

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flores.compras.adapter.Agregar_producto
import com.example.flores.compras.adapter.Lista_Compras

class Lista : AppCompatActivity() {


    private lateinit var Lista : MutableList<Lista_Compras>
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        recyclerView = findViewById<RecyclerView>(R.id.Lista_p)

        //initRecyclerView()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_menu_lista,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Guardar -> {
                return true
            }
            R.id.menu_agregar -> {

                val item = Lista_Compras(
                        "Producto:",
                "Precio$:",
                    "Cantidad:",
                "Marca:"
                )
                Lista.add(item)
                recyclerView.adapter?.notifyDataSetChanged()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    @SuppressLint("WrongViewCast")
    private fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.Lista_p)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Agregar_producto(Lista)
    }

}