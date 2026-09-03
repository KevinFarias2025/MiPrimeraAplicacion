package com.example.miprimeraaplicacion

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val nombreUsuario: String = "Ana"
    var edadUsuario: Int = 20
    var promedioNotas: Double = 6.5
    var esMayorDeEdad: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(promedioNotas)

        val saludo = crearSaludo(nombreUsuario, edadUsuario)
        esMayorDeEdad = calcularMayoriaEdad(edadUsuario)

        mostrarResultado(saludo + ". ¿Es mayor de edad? " + esMayorDeEdad)
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