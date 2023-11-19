package com.example.flores.compras.adapter

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flores.compras.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast

data class Lista_Compras(
    val producto: String,
    val cantidad: String,
    val precio: String
)

class Agregar_producto(private val LC: MutableList<Lista_Compras>): RecyclerView.Adapter<Agregar_producto.Producto>(){

    private lateinit var colorSwitch: Switch
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Producto {
        val View = LayoutInflater.from(parent.context).inflate(R.layout.activity_agregado, parent, false)
        return Producto(View)

    }
    override fun onBindViewHolder(holder: Producto, position: Int) {
        val item = LC[position]
        holder.bind(item)
    }
    override fun getItemCount(): Int {
      return LC.size
    }


    inner class Producto(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(producto:Lista_Compras) {
            itemView.findViewById<TextView>(R.id.textView).text = producto.producto
            itemView.findViewById<TextView>(R.id.textView2).text = producto.cantidad.toString()
            itemView.findViewById<TextView>(R.id.textView3).text = producto.precio.toString()

            colorSwitch = itemView.findViewById(R.id.switch1)

            colorSwitch.setOnCheckedChangeListener{_,isChecked ->
                if(isChecked){
                    itemView.setBackgroundColor(itemView.context.resources.getColor(R.color.Purple))
                }
                else
                {
                    itemView.setBackgroundColor(itemView.context.resources.getColor(R.color.white))
                }
            }
            itemView.findViewById<Button>(R.id.button1).setOnClickListener {
                // Lógica para borrar el producto de la lista
                val borrarProducto = itemView.context.getString(R.string.borrar)
                Toast.makeText(itemView.context,borrarProducto, Toast.LENGTH_SHORT).show()

                val posicion = LC.indexOf(producto)
                if(posicion != -1)
                {
                    LC.removeAt(posicion)
                    notifyDataSetChanged()
                }
            }
        }
    }
}
