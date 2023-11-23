package com.example.flores.compras

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.flores.compras.adapter.Agregar_producto
import com.example.flores.compras.adapter.Lista_Compras

class Producto_agregado : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregado)

        mediaPlayer = MediaPlayer.create(this, R.raw.precionar)

        val productoEditText = findViewById<EditText>(R.id.editTextText)
        val precioEditText = findViewById<EditText>(R.id.editTextNumber)
        val cantidadEditText = findViewById<EditText>(R.id.editTextNumber2)
        val marcaEditText = findViewById<EditText>(R.id.editTextText2)

        val producto = "Producto:"+productoEditText.text.toString()
        val precio = "Precio$: " + precioEditText.text.toString()
        val cantidad = "Cantidad: " + cantidadEditText.text.toString()
        val marca = "Marca: " + marcaEditText.text.toString()

        val item = Lista_Compras(producto, precio, cantidad, marca)
        Lista.add(item)

        val buttonOKAbout = findViewById<Button>(R.id.button_guardar)

        buttonOKAbout.setOnClickListener {
            mediaPlayer.start()
            finish()
        }
    }
}