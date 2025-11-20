package com.example.carritodecompras

import android.os.Bundle
import android.widget.TextView
<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

class CarritoActivity : AppCompatActivity() {

    private lateinit var recyclerCarrito: RecyclerView
    private lateinit var txtTotal: TextView
    private lateinit var adapter: CarritoAdapter
    private lateinit var db: DBHelper

=======
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CarritoActivity : AppCompatActivity() {
>>>>>>> bc547816ffba9d7318a0de34bba542e1e7d0d242
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

<<<<<<< HEAD
        db = DBHelper(this)

        recyclerCarrito = findViewById(R.id.recyclerCarrito)
        txtTotal = findViewById(R.id.txtTotal)

        recyclerCarrito.layoutManager = LinearLayoutManager(this)

        val listaCarrito = db.obtenerProductos()
            .toMutableList()

        adapter = CarritoAdapter(
            listaCarrito,

            // 🔹 AQUI SE REEMPLAZÓ EL onActualizar ORIGINAL
            onActualizar = { producto ->

                val dialog = android.app.AlertDialog.Builder(this)
                dialog.setTitle("Editar nombre del producto")

                val input = android.widget.EditText(this)
                input.hint = "Nuevo nombre"
                input.setText(producto.nombre)
                input.setPadding(40, 40, 40, 40)

                dialog.setView(input)

                dialog.setPositiveButton("Guardar") { _, _ ->
                    val nuevoNombre = input.text.toString()

                    if (nuevoNombre.isEmpty()) {
                        Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }

                    val actualizado = db.actualizarProducto(
                        producto.id,
                        nuevoNombre,          // ← Cambia solo el nombre
                        producto.precio,      // ← Deja el precio igual
                        producto.imagenResId  // ← Deja la imagen igual
                    )

                    if (actualizado) {
                        Toast.makeText(this, "Nombre actualizado", Toast.LENGTH_SHORT).show()
                        recargarCarrito()
                    } else {
                        Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show()
                    }
                }

                dialog.setNegativeButton("Cancelar", null)
                dialog.show()
            },

            // 🔹 Eliminar producto
            onEliminar = { producto ->
                val eliminado = db.eliminarProducto(producto.id)

                if (eliminado) {
                    Toast.makeText(this, "Producto eliminado", Toast.LENGTH_SHORT).show()
                    recargarCarrito()
                }
            }
        )

        recyclerCarrito.adapter = adapter

        mostrarTotal(listaCarrito)
    }

    private fun recargarCarrito() {
        val nuevaLista = db.obtenerProductos().toMutableList()
        adapter.actualizarLista(nuevaLista)
        mostrarTotal(nuevaLista)
    }

    private fun mostrarTotal(lista: List<Producto>) {
        val total = lista.sumOf { it.precio }
        txtTotal.text = "Total: $$total"
    }
}
=======
        val carrito = intent.getStringArrayListExtra("carrito")
        val txtCarrito = findViewById<TextView>(R.id.txtCarrito)
        val txtTotal = findViewById<TextView>(R.id.txtTotal)

        if (carrito != null && carrito.isNotEmpty()) {
            txtCarrito.text = carrito.joinToString("\n")

            var total = 0
            for (item in carrito) {
                when {
                    item.contains("40.000") -> total += 40000
                    item.contains("120.000") -> total += 120000
                    item.contains("95.000") -> total += 95000
                    item.contains("30.000") -> total += 30000
                }
            }

            txtTotal.text = "Total: $${String.format("%,d", total)}"
        } else {
            txtCarrito.text = "No hay productos en el carrito."
            txtTotal.text = ""
        }


        val btnFinalizar = findViewById<Button>(R.id.btnFinalizar)
        btnFinalizar.setOnClickListener {
            Toast.makeText(this, "¡Compra finalizada exitosamente!", Toast.LENGTH_LONG).show()
        }
    }
}

>>>>>>> bc547816ffba9d7318a0de34bba542e1e7d0d242
