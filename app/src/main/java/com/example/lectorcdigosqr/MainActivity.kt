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
//  ver algunas de las propiedades más interesantes
        IntentIntegrator(this).initiateScan()
        val integrator = IntentIntegrator(this)
//  setDesiredBarcodeFormats: Podemos definir el tipo de códigos que queremos poder escanear.
//  Simplemente pon IntentIntegrator. (con el punto) y te saldrán todas las opciones disponibles.
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
//  setPrompt: Añade un mensaje en la parte inferior de la pantalla del escaner.
        integrator.setPrompt("YAHWEH")
//  setTorchEnabled: Recibe un booleano, si es true, se activará el flash para escanear, por defecto viene a false.
        integrator.setTorchEnabled(true)
//  setBeepEnabled: Si lo ponemos a true sonará un pitido cuando se haga un escaneo correctamente.
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }


//    onActivityResult() nos avisa si lanzamos una nueva activity y
//    le decimos al sistema que estamos esperando respuesta.
//    Para ello en vez de usar startActivity como siempre habría que usar
//    startActivityForResult que será lo que tiene la librería por dentro al iniciar el escaner.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//    lleva un override al principio de la función, es una función del sistema que
//    ya genera una lógica por debajo. Cuando la función se llame, traerá tres valores,
//    pero no vamos a entrar en detalle con esto porque nuestra librería
//    tiene una función que nos simplificará también esta parte.

//  dentro de la función onActivityResult una variable que la igualaremos
//  con la clase IntentIntegrator llamando a la función parseActivityResult()
//  que recibirá los tres parámetros que onActivityResult nos manda.
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
//    si el valor no es nulo, significará que viene de la pantalla del escaner.
        if (result != null) {
//  Si por el contrario result.contents es null, significará que el usuario
            //  ha entrado en la pantalla del escaner, pero no ha escaneado nada y ha vuelto a la pantalla anterior.
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "El valor escaneado es: " + result.contents, Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}


//  Zxing es una librería que llevo años utilizando en todo tipo de aplicaciones,
//   desde personales hasta profesionales. Esta librería nos permite de una forma
//   muy sencilla poder leer una gran cantidad de códigos,
//   pero nosotros nos centraremos en el código QR y el código de barras.

//  ZXing Android Embedded y es básicamente una librería que contiene ZXing y
//  por encima de eso, controla la gestión de este,
//  como los permisos de la cámara y los ciclos de vida de la app.
//
//  Lo primero que haremos será añadir la librería,
//  para ello iremos al GitHub de Zxing Android Embedded y
//  buscaremos la dependencia y la última versión estable.
//  la última versión estable es la 4.0.2.
//