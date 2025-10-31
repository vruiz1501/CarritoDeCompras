package com.example.carritodecompras

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtEmail = findViewById<EditText>(R.id.txtEmailRegistro)
        val txtPassword = findViewById<EditText>(R.id.txtPasswordRegistro)
        val txtConfirmar = findViewById<EditText>(R.id.txtConfirmarPassword)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            val nombre = txtNombre.text.toString().trim()
            val email = txtEmail.text.toString().trim()
            val pass = txtPassword.text.toString().trim()
            val confirm = txtConfirmar.text.toString().trim()

            if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass != confirm) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Aquí podrías guardar los datos localmente o en una BD
            Toast.makeText(this, "Registro exitoso 🎉", Toast.LENGTH_LONG).show()

            // Opcional: regresar al Login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
