package com.example.flores.compras

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private lateinit var adapter: Agregar_producto

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        Lista = mutableListOf()
        recyclerView = findViewById<RecyclerView>(R.id.Lista_p)
        adapter = Agregar_producto(Lista)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_menu_lista,menu)
        return true
    }

    @SuppressLint("WrongViewCast")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Guardar -> {
                return true
            }
            R.id.menu_agregar -> {
                val intent = Intent(this@Lista, Producto_agregado::class.java)
                startActivity(intent)
                /*val item = Lista_Compras(
                        "Producto:"+findViewById<RecyclerView>(R.id.editTextText),
                "Precio$:"+findViewById<RecyclerView>(R.id.editTextNumber),
                    "Cantidad:"+findViewById<RecyclerView>(R.id.editTextNumber2),
                "Marca:"+findViewById<RecyclerView>(R.id.editTextText2)
                )
                Lista.add(item)
                adapter.notifyDataSetChanged()*/
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}