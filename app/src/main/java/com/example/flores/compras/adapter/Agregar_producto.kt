package com.example.flores.compras.adapter

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import com.example.flores.compras.Lista
import com.example.flores.compras.Lista.Companion.REQUEST_CODE_EDIT_PRODUCTO
import com.example.flores.compras.Producto_agregado
import com.example.flores.compras.R
import java.io.Serializable
import java.util.Collections
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.flores.compras.Inicio
import com.google.gson.Gson
import kotlin.coroutines.coroutineContext

data class Lista_Compras(
    val producto: String,
    val precio: String,
    val cantidad: String,
    val marca: String,
    var cambio: Int
): Serializable

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
        val imageViewProducto = itemView.findViewById<ImageView>(R.id.imageView3)
        val colorSwitch = itemView.findViewById<Switch>(R.id.switch1)

        val deleteButton = itemView.findViewById<Button>(R.id.button_borrar)
        val editButton = itemView.findViewById<Button>(R.id.button_editar)

        val Up = itemView.findViewById<Button>(R.id.Up)
        val Down = itemView.findViewById<Button>(R.id.Down)
        fun bin(producto: Lista_Compras) {
            itemView.findViewById<TextView>(R.id.textView).text = producto.producto
            itemView.findViewById<TextView>(R.id.textView6).text = producto.precio
            itemView.findViewById<TextView>(R.id.textView7).text = producto.cantidad
            itemView.findViewById<TextView>(R.id.textView8).text = producto.marca

            when (producto.cambio) {
                0 -> imageViewProducto.setImageResource(R.drawable.baseline_emoji_objects_24)
                1 -> imageViewProducto.setImageResource(R.drawable.baseline_emoji_objects_24)
                2 -> imageViewProducto.setImageResource(R.drawable.baseline_food_bank_24)
                3-> imageViewProducto.setImageResource(R.drawable.baseline_emoji_food_beverage_24)
                else -> {
                    producto.cambio = 0
                }
            }

            deleteButton.setOnClickListener {
                Inicio.contadorElementos--
                if(Inicio.contadorElementos<0){
                    Inicio.contadorElementos=0
                }
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    Lista.ListaComprasSingleton.getList().removeAt(position)
                    notifyItemRemoved(position)
                }
            }

            editButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val intent = Intent(itemView.context, Producto_agregado::class.java)
                    intent.putExtra("producto", producto)
                    intent.putExtra("position", position)
                    (itemView.context as Activity).startActivityForResult(
                        intent,
                        REQUEST_CODE_EDIT_PRODUCTO
                    )
                }
            }

            colorSwitch.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    itemView.setBackgroundColor(itemView.context.resources.getColor(R.color.Green))
                }
                else{
                    itemView.setBackgroundColor(itemView.context.resources.getColor(R.color.Red))
                }
            }

            Up.setOnClickListener{
                val position = adapterPosition
                if (position > 0) {
                    Collections.swap(LC, position, position - 1)
                    notifyItemMoved(position, position - 1)
                }
            }
            Down.setOnClickListener{
                val position = adapterPosition
                if (position < LC.size - 1) {
                    Collections.swap(LC, position, position + 1)
                    notifyItemMoved(position, position + 1)
                }
            }
        }

    }

}
