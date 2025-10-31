package com.example.carritodecompras

import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CarritoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

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

