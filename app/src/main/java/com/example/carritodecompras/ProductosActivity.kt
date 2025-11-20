package com.example.carritodecompras

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class ProductosActivity : AppCompatActivity(), LocationListener {

    private val carrito = arrayListOf<String>()
    private lateinit var txtUbicacion: TextView
    private lateinit var locationManager: LocationManager
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)


        db = DBHelper(this)


        val btnAgregarCamiseta = findViewById<Button>(R.id.btnCamiseta)
        val btnAgregarTenis = findViewById<Button>(R.id.btnTenis)
        val btnAgregarChaqueta = findViewById<Button>(R.id.btnChaqueta)
        val btnAgregarGorra = findViewById<Button>(R.id.btnGorra)
        val btnVerCarrito = findViewById<Button>(R.id.btnIrCarrito)
        txtUbicacion = findViewById(R.id.txtUbicacion)


        btnAgregarCamiseta.setOnClickListener {
            db.crearProducto("Camiseta deportiva", 40000, R.drawable.camisa)
            Toast.makeText(this, "Camiseta agregada", Toast.LENGTH_SHORT).show()
        }

        btnAgregarTenis.setOnClickListener {
            db.crearProducto("Tenis blancos", 120000, R.drawable.tennis)
            Toast.makeText(this, "Tenis agregados", Toast.LENGTH_SHORT).show()
        }

        btnAgregarChaqueta.setOnClickListener {
            db.crearProducto("Chaqueta impermeable", 95000, R.drawable.chaqueta)
            Toast.makeText(this, "Chaqueta agregada", Toast.LENGTH_SHORT).show()
        }

        btnAgregarGorra.setOnClickListener {
            db.crearProducto("Gorra ajustable", 30000, R.drawable.gorra)
            Toast.makeText(this, "Gorra agregada", Toast.LENGTH_SHORT).show()
        }


        btnVerCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            intent.putStringArrayListExtra("carrito", carrito)
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
        txtUbicacion.text =
            "Latitud: ${location.latitude}\nLongitud: ${location.longitude}"
    }
}
