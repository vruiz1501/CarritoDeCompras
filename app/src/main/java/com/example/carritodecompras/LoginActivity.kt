package com.example.carritodecompras

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txtNombre = findViewById<EditText>(R.id.txtUsuario)
        val txtPassword = findViewById<EditText>(R.id.txtClave)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegistrar = findViewById<Button>(R.id.btnIrRegistro)

        val db = DBHelper(this)

        btnLogin.setOnClickListener {
            val nombre = txtNombre.text.toString().trim()
            val password = txtPassword.text.toString().trim()

            if (nombre.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val usuarioValido = db.validarUsuario(nombre, password)

            if (usuarioValido) {
                Toast.makeText(this, "Bienvenido $nombre 🎉", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ProductosActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Nombre o contraseña incorrectos ❌", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegistrar.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }
}
