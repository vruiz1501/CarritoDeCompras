package com.example.carritodecompras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarritoAdapter(
    private var listaCarrito: MutableList<Producto>,
    private val onActualizar: (Producto) -> Unit,
    private val onEliminar: (Producto) -> Unit
) : RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombreCarrito)
        val txtPrecio: TextView = itemView.findViewById(R.id.txtPrecioCarrito)
        val btnActualizar: Button = itemView.findViewById(R.id.btnActualizar)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carrito, parent, false)
        return CarritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val producto = listaCarrito[position]

        holder.txtNombre.text = producto.nombre
        holder.txtPrecio.text = "$${producto.precio}"

        holder.btnActualizar.setOnClickListener {
            onActualizar(producto)
        }

        holder.btnEliminar.setOnClickListener {
            onEliminar(producto)
        }
    }

    override fun getItemCount(): Int = listaCarrito.size

    fun actualizarLista(nuevaLista: MutableList<Producto>) {
        listaCarrito = nuevaLista
        notifyDataSetChanged()
    }
}
