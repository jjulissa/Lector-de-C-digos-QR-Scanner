package com.example.lectorcdigosqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lectorcdigosqr.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScanner.setOnClickListener { initScanner()}
    }


    private fun initScanner() {
        IntentIntegrator(this).initiateScan()
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Sigue aprendiendo en CursoKotlin.com")
        integrator.setTorchEnabled(true)
        integrator.setBeepEnabled(true)
        integrator.initiateScan()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "El valor escaneado es: " + result.contents, Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


//    https://www.youtube.com/watch?v=Wdb5okugwmU&list=PL8ie04dqq7_OcBYDpvHrcSFVoggLi3cm_&index=21
//    7:00
//    https://cursokotlin.com/zxing-leer-qr-codigos-de-barras-en-kotlin/
//

//https://cursokotlin.com/curso-programacion-kotlin-android/

//    https://cursokotlin.com/capitulo-22-fragments-en-kotlin/




}