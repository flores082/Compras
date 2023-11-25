package com.example.flores.compras

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.flores.compras.adapter.Lista_Compras
import kotlin.properties.Delegates

class Producto_agregado : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var imagenproducto: ImageView
    private var cambio by Delegates.notNull<Int>()
    private var listaChangeListener: ListaChangeListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregado)

        mediaPlayer = MediaPlayer.create(this, R.raw.precionar)
        imagenproducto = findViewById(R.id.imageView2)

        val productoEditText = findViewById<EditText>(R.id.editTextText)
        val precioEditText = findViewById<EditText>(R.id.editTextNumber)
        val cantidadEditText = findViewById<EditText>(R.id.editTextNumber2)
        val marcaEditText = findViewById<EditText>(R.id.editTextText2)

        val buttonOKAbout = findViewById<Button>(R.id.button_guardar)
        val buttonOkAbout2 = findViewById<Button>(R.id.button_producto)

        cambio = 0

        listaChangeListener = intent.getSerializableExtra("listener") as? ListaChangeListener

        buttonOKAbout.setOnClickListener {
            val producto = "Producto:" + productoEditText.text.toString()
            val precio = "Precio$: " + precioEditText.text.toString()
            val cantidad = "Cantidad: " + cantidadEditText.text.toString()
            val marca = "Marca: " + marcaEditText.text.toString()

            val item = Lista_Compras(producto, precio, cantidad, marca, cambio, true)
            Lista.ListaComprasSingleton.addToList(item)

            val intent = Intent()
            intent.putExtra("editedProducto", item)
            intent.putExtra("position", intent.getIntExtra("position", -1))
            setResult(Activity.RESULT_OK, intent)

            listaChangeListener?.onListaChanged(ChangeType.ITEM_ADDED)
            mediaPlayer.start()

            setResult(Activity.RESULT_OK)
            finish()
        }
        buttonOkAbout2.setOnClickListener {
            cambio++
            Log.d("TAG", "Valor de cambio: $cambio")
            when (cambio) {
                0 -> imagenproducto.setImageResource(R.drawable.baseline_emoji_objects_24)
                1 -> imagenproducto.setImageResource(R.drawable.baseline_emoji_objects_24)
                2 -> imagenproducto.setImageResource(R.drawable.baseline_food_bank_24)
                3-> imagenproducto.setImageResource(R.drawable.baseline_emoji_food_beverage_24)
                else -> {
                    cambio = 0
                }
            }
        }
    }

    interface ListaChangeListener {
        fun onListaChanged(changeType: ChangeType)
    }

    enum class ChangeType {
        ITEM_ADDED
    }
}