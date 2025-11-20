package com.example.carritodecompras

<<<<<<< HEAD
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
=======
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductosActivity : AppCompatActivity() {

    private val carrito = ArrayList<String>()
>>>>>>> bc547816ffba9d7318a0de34bba542e1e7d0d242

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

<<<<<<< HEAD

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


=======
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

>>>>>>> bc547816ffba9d7318a0de34bba542e1e7d0d242
        btnVerCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            intent.putStringArrayListExtra("carrito", carrito)
            startActivity(intent)
        }
<<<<<<< HEAD


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
=======
>>>>>>> bc547816ffba9d7318a0de34bba542e1e7d0d242
    }
}
