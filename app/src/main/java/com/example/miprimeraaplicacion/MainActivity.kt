package com.example.miprimeraaplicacion

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val nombreUsuario: String = "Ana"
    var edadUsuario: Int = 20
    var promedioNotas: Double = 6.5
    val esMayorDeEdad: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saludo = crearSaludo(nombreUsuario, edadUsuario)
        val esMayor = calcularMayoriaEdad(edadUsuario)

        // Usamos esMayorDeEdad y promedioNotas para quitar los warnings
        mostrarResultado(saludo + ". ¿Es mayor? " + esMayor + " (Promedio: " + promedioNotas + ")")
    }
    fun crearSaludo(nombre: String, edad: Int): String {
        val mensaje = "Hola " + nombre + ", tienes " + edad + " años"
        return mensaje
    }

    fun calcularMayoriaEdad(edad: Int): Boolean {
        if (edad >= 18) {
            return true
        } else {
            return false
        }
    }

    fun mostrarResultado(mensaje: String) {
        val textView = findViewById<TextView>(android.R.id.text1)
        if (textView == null) {
            // Si el ID del texto en activity_main.xml tiene un ID propio,
            // intenta buscar cualquier TextView disponible en pantalla:
            val root = findViewById<android.view.ViewGroup>(android.R.id.content)
            findAndSetTextView(root, mensaje)
        } else {
            textView.text = mensaje
        }
    }

    private fun findAndSetTextView(viewGroup: android.view.ViewGroup, mensaje: String) {
        for (i in 0 until viewGroup.childCount) {
            val child = viewGroup.getChildAt(i)
            if (child is TextView) {
                child.text = mensaje
                break
            } else if (child is android.view.ViewGroup) {
                findAndSetTextView(child, mensaje)
            }
        }
    }
}