package com.example.flores.compras.adapter

import android.database.DataSetObserver
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flores.compras.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Switch
import android.widget.Toast

data class Lista_Compras(
    val producto: String,
    val precio: String,
    val cantidad: String,
    val marca: String
)

class Agregar_producto(private val LC: MutableList<Lista_Compras>):
    RecyclerView.Adapter<Agregar_producto.ProductoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val View = LayoutInflater.from(parent.context).inflate(R.layout.activity_agregado_vista, parent, false)
        return ProductoViewHolder(View)

    }
    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val item = LC[position]
        holder.bin(item)
    }
    override fun getItemCount(): Int {
      return LC.size
    }


    inner class ProductoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bin(producto:Lista_Compras) {
            itemView.findViewById<TextView>(R.id.textView).text = producto.producto
            itemView.findViewById<TextView>(R.id.textView6).text = producto.precio
            itemView.findViewById<TextView>(R.id.textView7).text = producto.cantidad
            itemView.findViewById<TextView>(R.id.textView8).text = producto.marca
        }
    }

}
