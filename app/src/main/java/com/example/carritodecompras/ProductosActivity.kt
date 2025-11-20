package com.example.carritodecompras

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ProductosActivity : AppCompatActivity(), LocationListener {

    private lateinit var recycler: RecyclerView
    private lateinit var txtUbicacion: TextView
    private lateinit var db: DBHelper
    private lateinit var locationManager: LocationManager

    private val carrito = ArrayList<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        db = DBHelper(this)
        recycler = findViewById(R.id.recyclerProductos)
        txtUbicacion = findViewById(R.id.txtUbicacion)

        recycler.layoutManager = LinearLayoutManager(this)

        // Lista de productos inicial
        val productos = listOf(
            Producto(nombre = "Camiseta deportiva", precio = 40000, imagenResId = R.drawable.camisa),
            Producto(nombre = "Tenis blancos", precio = 120000, imagenResId = R.drawable.tennis),
            Producto(nombre = "Chaqueta impermeable", precio = 95000, imagenResId = R.drawable.chaqueta),
            Producto(nombre = "Gorra ajustable", precio = 30000, imagenResId = R.drawable.gorra)
        )

        // Guardar productos en DB solo si no existen
        for (p in productos) {
            if (!db.existeProducto(p.nombre)) {
                db.crearProducto(p.nombre, p.precio, p.imagenResId)
            }
        }

        recycler.adapter = ProductoAdapter(db.obtenerProductos()) { producto ->
            carrito.add(producto)
            Toast.makeText(this, "${producto.nombre} agregado", Toast.LENGTH_SHORT).show()
        }

        findViewById<android.widget.Button>(R.id.btnIrCarrito).setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            // Pasamos solo los IDs del carrito
            db.obtenerProductos() // asegura DB actualizada
            startActivity(intent)
        }

        solicitarPermisosUbicacion()
    }

    private fun solicitarPermisosUbicacion() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                100
            )
        } else {
            iniciarGPS()
        }
    }

    private fun iniciarGPS() {
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                2000,
                5f,
                this
            )
        }
    }

    override fun onLocationChanged(location: Location) {
        txtUbicacion.text = "Latitud: ${location.latitude}\nLongitud: ${location.longitude}"
    }
}
