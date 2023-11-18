package com.example.flores.compras.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flores.compras.R
data class Lista_Compras(
    val producto: String,
    val cantidad: String,
    val precio: String
)
class Lista(view: View): RecyclerView.ViewHolder(view) {

    val Producto = view.findViewById<TextView>(R.id.textView)
    val Cantidad = view.findViewById<TextView>(R.id.textView2)
    val Precio = view.findViewById<TextView>(R.id.textView3)

    fun render(Lista_de_Compras: Lista_Compras){
        Producto.text = Lista_de_Compras.producto
        Cantidad.text = Lista_de_Compras.cantidad
        Precio.text = Lista_de_Compras.precio
    }
}