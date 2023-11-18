package com.example.flores.compras.adapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flores.compras.Inicio
import com.example.flores.compras.R
import com.example.flores.compras.ViewHolder.Lista
import com.example.flores.compras.ViewHolder.Lista_Compras
import android.view.LayoutInflater
import android.view.ViewGroup


class Agregar_producto(private val LC: List<Lista_Compras>): RecyclerView.Adapter<Lista>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Lista {
        val View = LayoutInflater.from(parent.context).inflate(R.layout.activity_agregado, parent, false)
        return Lista(View)

    }
    override fun onBindViewHolder(holder: Lista, position: Int) {
        val item = LC[position]
        holder.render(item)
    }
    override fun getItemCount(): Int = LC.size


    /*inner class Producto(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(producto:Lista_Compras) {
            itemView.findViewById<Button>(R.id.button1).setOnClickListener {
                // Lógica para borrar el producto de la lista
                //LC.remove(producto)
                notifyDataSetChanged()
            }
        }
    }*/
}
