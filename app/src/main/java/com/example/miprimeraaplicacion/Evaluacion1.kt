package com.example.miprimeraaplicacion

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

open class Entrada(val id: String, val precio: Double) {
    open fun mostrarDetalle() {
        println("ID: $id | Precio: $$precio")
    }
}

class EntradaGeneral(id: String, precio: Double, val zona: String) : Entrada(id, precio) {
    override fun mostrarDetalle() {
        println("Entrada General - ID: $id | Precio: $$precio | Zona: $zona")
    }
}

class EntradaVIP(id: String, precio: Double, val beneficioExtra: String) : Entrada(id, precio) {
    override fun mostrarDetalle() {
        println("Entrada VIP - ID: $id | Precio: $$precio | Beneficio: $beneficioExtra")
    }
}

sealed class EstadoValidacion {
    object Validando : EstadoValidacion()
    class Valida(val entrada: Entrada) : EstadoValidacion()
    class NoValida(val mensaje: String) : EstadoValidacion()
}

suspend fun validarEntrada(id: String, lista: List<Entrada>): EstadoValidacion {
    delay(2000)
    for (entrada in lista) {
        if (entrada.id == id) {
            return EstadoValidacion.Valida(entrada)
        }
    }
    return EstadoValidacion.NoValida("No se encontro la entrada con ID: $id")
}

fun main() = runBlocking {
    val listaEntradas = listOf(
        EntradaGeneral("G1", 15000.0, "Cancha"),
        EntradaGeneral("G2", 15000.0, "Platea"),
        EntradaVIP("V1", 45000.0, "Acceso preferencial"),
        EntradaVIP("V2", 60000.0, "Estacionamiento exclusivo")
    )

    println("--- Lista de Entradas ---")
    for (entrada in listaEntradas) {
        entrada.mostrarDetalle()
    }

    var totalDinero = 0.0
    var cantidadVip = 0

    for (entrada in listaEntradas) {
        totalDinero += entrada.precio
        if (entrada is EntradaVIP) {
            cantidadVip++
        }
    }

    println("\nIngreso total: $$totalDinero")
    println("Cantidad de entradas VIP: $cantidadVip")

    println("\n--- Validando entradas ---")

    val resultado1 = validarEntrada("V1", listaEntradas)
    mostrarResultado(resultado1)

    val resultado2 = validarEntrada("G99", listaEntradas)
    mostrarResultado(resultado2)
}

fun mostrarResultado(estado: EstadoValidacion) {
    when (estado) {
        is EstadoValidacion.Validando -> println("Validando...")
        is EstadoValidacion.Valida -> {
            println("Entrada Valida:")
            estado.entrada.mostrarDetalle()
        }
        is EstadoValidacion.NoValida -> println("Error: ${estado.mensaje}")
    }
}