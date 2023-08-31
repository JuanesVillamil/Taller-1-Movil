package com.example.taller1_cercado_parra_villamil

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taller1_cercado_parra_villamil.adapters.CountryAdapter
import com.example.taller1_cercado_parra_villamil.databinding.ActivityCountriesBinding
import org.json.JSONObject
import android.widget.ListView


class CountriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountriesBinding
    private lateinit var listView: ListView
    private lateinit var countries: List<Country>

    // Clase Country del .json.
    data class Country(
        val nombre_pais: String,
        val nombre_capital: String,
        val nombre_pais_int: String,
        val sigla: String
    )
    // Inicialización del código.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listView = findViewById(R.id.listView) // Enlaza el ListView desde XML.
        loadCountries()

        // Crear una instancia del adaptador de paísesy trabaja con Countries.
        val adapter = CountryAdapter(this, android.R.layout.simple_list_item_1, countries)
        listView.adapter = adapter // Acá la listView utiliza al adaptador CountryAdapter para mostrar los elementos.
    }
    // Cargar archivos
    fun loadCountries() {
        countries = mutableListOf<Country>() // Acá creo una lista mutable por si necesito cambiar algo luego.
        val jsonread = this.assets.open("paises.json").bufferedReader().use { // Acá leo todo el archivo json.
            it.readText()
        }
        var json = JSONObject(jsonread) // Acá convierto "jsonread" en objetos como tal.
        var paisesJsonArray = json.getJSONArray("paises") // Acá accedo al "paises" del .json.
        for (i in 0 until paisesJsonArray.length()) { // Se va creando objetos Country por cada país del .json.
            val jsonObject = paisesJsonArray.getJSONObject(i)
            val capital = jsonObject.getString("capital")
            val name = jsonObject.getString("nombre_pais")
            val nameInt = jsonObject.getString("nombre_pais_int")
            val sigla = jsonObject.getString("sigla")
            val country = Country(name, capital, nameInt, sigla)
            (countries as MutableList).add(country) // Acá son agregados estos objetos Country a la lista countries.
        }
        // Listener para acceder a cualquier país de la lista.
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedCountry = countries[position]
            val intent = Intent(this, CountryDetailActivity::class.java)
            // Pasar datos del país en el Intent.
            intent.putExtra("countryName", selectedCountry.nombre_pais)
            intent.putExtra("countryCapital", selectedCountry.nombre_capital)
            intent.putExtra("countryNameInt", selectedCountry.nombre_pais_int)
            intent.putExtra("countrySigla", selectedCountry.sigla)
            // Inicia la actividad del país
            startActivity(intent)
        }
    }
}