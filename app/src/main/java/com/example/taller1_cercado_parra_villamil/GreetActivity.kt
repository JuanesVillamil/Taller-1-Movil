package com.example.taller1_cercado_parra_villamil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taller1_cercado_parra_villamil.databinding.GreetBinding

class GreetActivity : AppCompatActivity() {
    private lateinit var binding: GreetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GreetBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // Obtener idioma seleccionado.
        val selectedLanguage = intent.getStringExtra("selectedLanguage")
        // Obtener saludo y imagen correspondiente al idioma seleccionado.
        val greeting = getGreetingForLanguage(selectedLanguage)
        val imageResource = getImageResourceForLanguage(selectedLanguage)
        // Mostrar el saludo y la imagen en la vista.
        binding.textView.text = greeting
        binding.imageView.setImageResource(imageResource)
    }
    // Seleccionado el lenguaje, saluda al usuaio en dicho lenguaje.
    private fun getGreetingForLanguage(language: String?): String {
        return when (language) {
            "Español" -> "¡Hola!"
            "Ingles" -> "Hello!"
            "Japones" -> "こんにちは！"
            "Italiano" -> "Ciao!"
            "Portugues" -> "Olá!"
            else -> "Hola!"
        }
    }
    // Se lanza la imagen dependiendo del idioma seleccionado

    private fun getImageResourceForLanguage(language: String?): Int {
        return when (language) {
            "Español" -> R.drawable.flag_es
            "Ingles" -> R.drawable.flag_in
            "Japones" -> R.drawable.flag_ja
            "Italiano" -> R.drawable.flag_it
            "Portugues" -> R.drawable.flag_po
            else -> R.drawable.flag_es
        }
    }
}
