package dev.cubel.campusmoba.tarea5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch

class MainActivity : AppCompatActivity() {


    var btn_cambiar_imagen: Button? = null
    var iv_contenedor: ImageView? = null
    var contador_imagen = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Se castea el Boton de la vista en la variable y el Image View
        btn_cambiar_imagen = findViewById<Button>(R.id.btn_cambiar_imagen)
        iv_contenedor = findViewById<ImageView>(R.id.iv_contenedor)



        //Preparamos el "onclick" del boton
        btn_cambiar_imagen?.setOnClickListener {
            cambiar_imagen()
        }


        //Cargamos la primera imagen
        cargar_imagen(contador_imagen)
    }

    private fun cambiar_imagen(){
        //Sumamos uno al contador actual
        contador_imagen++

        //En caso de que el contador actual sea igual a 4 o superior, lo reseteamos a 1 porque solo tenemos 3 imagenes
        if (contador_imagen >=4) contador_imagen = 1

        //Aquí hacemos la acción de cargar imagen pasandole el numero de imagen a cargar
        cargar_imagen(contador_imagen)
    }

    private fun cargar_imagen(numero_imagen: Int){
        when (numero_imagen) {
            1 -> iv_contenedor?.setImageResource(R.drawable.img_1)
            2 -> iv_contenedor?.setImageResource(R.drawable.img_2)
            3 -> iv_contenedor?.setImageResource(R.drawable.img_3)
        }
    }
}