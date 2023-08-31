package com.example.taller1_cercado_parra_villamil

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.taller1_cercado_parra_villamil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Botón de titctactoe redirige a titctactoe.
        binding.tictactoe.setOnClickListener {
            startActivity(Intent(baseContext, TicTacToeActivity::class.java)) }
        //Botón de countries redirige a countries.
        binding.countries.setOnClickListener {
            startActivity(Intent(baseContext, CountriesActivity::class.java)) }
        //Spinner.
        val listLanguages = arrayOf("Español", "Ingles", "Japones", "Italiano", "Portugues")
        //Adapter para el spinner
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, listLanguages)
        //Diseńo del spinner.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.languages.adapter = adapter
        //Botón de greet redirige a greet.
        binding.greet.setOnClickListener {
            //Obtener el idioma del Spinner
            val selectedLanguage = binding.languages.selectedItem.toString()
            //Abrir GreetActivity.
            val intent = Intent(baseContext, GreetActivity::class.java)
            intent.putExtra("selectedLanguage", selectedLanguage)
            //Inicia la actividad GreetActivity }
            startActivity(intent)
        }
    }
}