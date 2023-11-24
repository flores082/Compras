package com.example.flores.compras

import android.annotation.SuppressLint
import android.app.Activity
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

class Lista : AppCompatActivity(), Producto_agregado.ListaChangeListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Agregar_producto

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        recyclerView = findViewById<RecyclerView>(R.id.Lista_p)
        adapter = Agregar_producto(ListaComprasSingleton.getList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onListaChanged(changeType: Producto_agregado.ChangeType) {
        when (changeType) {
            Producto_agregado.ChangeType.ITEM_ADDED -> {
                // Actualiza el adaptador
                adapter.notifyDataSetChanged()
            }
        }
    }

    object ListaComprasSingleton {
        private val lista: MutableList<Lista_Compras> = mutableListOf()

        fun getList(): MutableList<Lista_Compras> {
            return lista
        }

        fun addToList(item: Lista_Compras) {
            lista.add(item)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_menu_lista, menu)
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
                startActivityForResult(intent, REQUEST_CODE_PRODUCTO_AGREGADO)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PRODUCTO_AGREGADO && resultCode == Activity.RESULT_OK) {
            // Actualiza la lista según sea necesario
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        const val REQUEST_CODE_PRODUCTO_AGREGADO = 1
    }

}

