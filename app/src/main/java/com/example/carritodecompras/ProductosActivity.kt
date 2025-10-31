package com.example.carritodecompras

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductosActivity : AppCompatActivity() {

    private val carrito = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        val recycler = findViewById<RecyclerView>(R.id.recyclerProductos)
        recycler.layoutManager = LinearLayoutManager(this)

        val btnVerCarrito = findViewById<Button>(R.id.btnIrCarrito)

        val productos = listOf(
            Producto("Camiseta deportiva", "$40.000", R.drawable.camisa),
            Producto("Tenis blancos", "$120.000", R.drawable.tennis),
            Producto("Chaqueta impermeable", "$95.000", R.drawable.chaqueta),
            Producto("Gorra ajustable", "$30.000", R.drawable.gorra)
        )

        recycler.adapter = ProductoAdapter(productos) { producto ->
            carrito.add("${producto.nombre} - ${producto.precio}")
        }

        btnVerCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            intent.putStringArrayListExtra("carrito", carrito)
            startActivity(intent)
        }
    }
}
