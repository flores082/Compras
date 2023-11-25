package com.example.flores.compras

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flores.compras.adapter.Agregar_producto
import com.example.flores.compras.adapter.Lista_Compras
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Lista : AppCompatActivity(), Producto_agregado.ListaChangeListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Agregar_producto
    private lateinit var editTextSearch: EditText
    private lateinit var buttonSearch: Button
    companion object {
        fun cargarDesdeSharedPreferences(context: Context): Int {
            val sharedPreferences = context.getSharedPreferences("MiInventarioPrefs", Context.MODE_PRIVATE)
            return sharedPreferences.getInt("contador_elementos", 0)
        }

        const val REQUEST_CODE_PRODUCTO_AGREGADO = 1
        const val REQUEST_CODE_EDIT_PRODUCTO = 2
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        editTextSearch = findViewById(R.id.editBuscar)
        buttonSearch = findViewById(R.id.button_b)

        buttonSearch.setOnClickListener {
            val searchTerm = editTextSearch.text.toString().trim()
            if (searchTerm.isNotEmpty()) {
                moveItemToTop(searchTerm)
            }
        }
        cargarDesdeSharedPreferences()

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

    fun cargarDesdeSharedPreferences() {

        val sharedPreferences = getSharedPreferences("MiInventarioPrefs", Context.MODE_PRIVATE)
        val listaJsonGuardada = sharedPreferences.getString("lista_compras", null)
        try {
            actualizarContadorElementos()
            val gson = Gson()
            val listaComprasType = object : TypeToken<MutableList<Lista_Compras>>() {}.type
            val listaCompras = gson.fromJson<MutableList<Lista_Compras>>(listaJsonGuardada, listaComprasType)
            ListaComprasSingleton.setList(listaCompras)
        } catch (e: Exception) {
            Log.e("TAG", "Error al deserializar JSON", e)
        }

    }

    private fun guardarDatos() {
        val gson = Gson()
        val listaComprasJson = gson.toJson(ListaComprasSingleton.getList())

        val sharedPreferences = getSharedPreferences("MiInventarioPrefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("lista_compras", listaComprasJson)
            putInt("contador_elementos", Inicio.contadorElementos)
            apply()
        }

        Toast.makeText(this, R.string.b, Toast.LENGTH_SHORT).show()
    }

    private fun moveItemToTop(searchTerm: String) {
        val originalList = ListaComprasSingleton.getList()

        val matchingItem = originalList.find {
            it.producto.contains(searchTerm, ignoreCase = true)
        }

        if (matchingItem != null) {
            val nonMatchingItems = originalList.filterNot {
                it.producto.contains(searchTerm, ignoreCase = true)
            }

            val updatedList = mutableListOf<Lista_Compras>()
            updatedList.add(matchingItem) // agrega el elemento coincidente al principio de la lista

            updatedList.addAll(nonMatchingItems)
            ListaComprasSingleton.setList(updatedList)
            adapter.notifyDataSetChanged()

            // deveria mover el elemento al principio de la lista visualmente
            val position = originalList.indexOf(matchingItem)
            if (position != -1) {
                recyclerView.scrollToPosition(0)
                adapter.notifyItemMoved(position, 0)
            }
        }
    }

    object ListaComprasSingleton {
        private var lista: MutableList<Lista_Compras> = mutableListOf()

        fun getList(): MutableList<Lista_Compras> {
            return lista
        }

        fun addToList(item: Lista_Compras) {
            lista.add(item)
        }
        fun setList(newList: MutableList<Lista_Compras>) {
            lista = newList
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
                guardarDatos()
                return true
            }

            R.id.menu_agregar -> {
                Inicio.contadorElementos++
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
        }else if (requestCode == REQUEST_CODE_EDIT_PRODUCTO && resultCode == Activity.RESULT_OK) {
            // Producto editado
            val position = data?.getIntExtra("position", -1)
            val editedProducto = data?.getSerializableExtra("editedProducto") as? Lista_Compras

            if (position != null && editedProducto != null && position != -1) {
                ListaComprasSingleton.getList()[position] = editedProducto
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun actualizarContadorElementos() {
        Inicio.Elementos.text = getString(R.string.CP) +"${Inicio.contadorElementos}"
    }


}


