package com.example.carritodecompras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(
    private val listaProductos: List<Producto>,
    private val onAgregarClick: (Producto) -> Unit
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombreProducto)
        val txtPrecio: TextView = itemView.findViewById(R.id.txtPrecioProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = listaProductos[position]
<<<<<<< HEAD

        holder.imgProducto.setImageResource(producto.imagenResId)
        holder.txtNombre.text = producto.nombre
        holder.txtPrecio.text = producto.precio.toString()

=======
        holder.imgProducto.setImageResource(producto.imagenResId)
        holder.txtNombre.text = producto.nombre
        holder.txtPrecio.text = producto.precio

        // 👉 Al hacer clic en cualquier parte del producto, lo agrega al carrito
>>>>>>> bc547816ffba9d7318a0de34bba542e1e7d0d242
        holder.itemView.setOnClickListener {
            onAgregarClick(producto)
            Toast.makeText(holder.itemView.context, "Agregado al carrito", Toast.LENGTH_SHORT).show()
        }
    }

<<<<<<< HEAD

=======
>>>>>>> bc547816ffba9d7318a0de34bba542e1e7d0d242
    override fun getItemCount() = listaProductos.size
}
