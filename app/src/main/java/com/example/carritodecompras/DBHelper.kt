package com.example.carritodecompras

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "usuarios.db", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE usuarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, " +
                    "email TEXT UNIQUE, " +
                    "password TEXT)"
        )

        db.execSQL(
            "CREATE TABLE productos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, " +
                    "precio INTEGER, " +
                    "imagenResId INTEGER)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        db.execSQL("DROP TABLE IF EXISTS productos")
        onCreate(db)
    }



    fun insertUsuario(nombre: String, email: String, password: String): Boolean {
        val db = writableDatabase

        // Verificar si el email ya existe
        val cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = ?", arrayOf(email))
        if (cursor.moveToFirst()) {
            cursor.close()
            return false // usuario ya existe
        }
        cursor.close()

        val values = ContentValues()
        values.put("nombre", nombre)
        values.put("email", email)
        values.put("password", password)

        return db.insert("usuarios", null, values) != -1L
    }

    fun validarUsuario(nombre: String, password: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM usuarios WHERE nombre = ? AND password = ?"
        val cursor = db.rawQuery(query, arrayOf(nombre, password))

        val existe = cursor.moveToFirst()
        cursor.close()
        return existe
    }



    fun crearProducto(nombre: String, precio: Int, imagenResId: Int): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("nombre", nombre)
        values.put("precio", precio)
        values.put("imagenResId", imagenResId)

        return db.insert("productos", null, values) != -1L
    }

    fun obtenerProductos(): ArrayList<Producto> {
        val lista = ArrayList<Producto>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM productos", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nombre = cursor.getString(1)
                val precio = cursor.getInt(2)
                val imagenRes = cursor.getInt(3)

                lista.add(Producto(id, nombre, precio, imagenRes))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

    fun actualizarProducto(id: Int, nombre: String, precio: Int, imagenResId: Int): Boolean {
        val db = writableDatabase
        val values = ContentValues()
        values.put("nombre", nombre)
        values.put("precio", precio)
        values.put("imagenResId", imagenResId)

        return db.update("productos", values, "id=?", arrayOf(id.toString())) > 0
    }

    fun eliminarProducto(id: Int): Boolean {
        val db = writableDatabase
        return db.delete("productos", "id=?", arrayOf(id.toString())) > 0
    }

    fun existeProducto(nombre: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT id FROM productos WHERE nombre = ?", arrayOf(nombre))
        val existe = cursor.count > 0
        cursor.close()
        db.close()
        return existe
    }
}
