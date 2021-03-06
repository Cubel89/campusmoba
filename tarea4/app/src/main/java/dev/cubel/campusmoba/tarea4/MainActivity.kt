package dev.cubel.campusmoba.tarea4

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {



    /*Creamos la variable que hará referencia al elemento en pantalla.
            Se podria crear en el onCreate porque el proyecto es pequeño*/
    var btn_enviar_notificacion:Button? = null

    //Variable para en CHANNEL_ID de la notificación
    val CHANNEL_ID: String = "channel_de_prueba"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Se castea el Boton de la vista en la variable
        btn_enviar_notificacion = findViewById<Button>(R.id.btn_enviar_notificacion)

        //Creamos en canal de notificaciones necesario para las ultimas versiones de Android
        createNotificationChannel()

        //Preparamos el "onclick" del boton
        btn_enviar_notificacion?.setOnClickListener {
            crearNotificacion()
        }
    }


    private fun crearNotificacion(){


        //Creamos la accion al pulsar la notificacion
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        //Aqui creamos la notificacion
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Título notificación")
                .setContentText("Esto es el texto corto")
                .setStyle(NotificationCompat.BigTextStyle()
                        .bigText("Aquí va un texto largo que se puede poner multilinea"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)


                //La mostramos
                with(NotificationManagerCompat.from(this)) {
                    notify(1, builder.build())
                }
    }


    private fun createNotificationChannel() {
        //Comprobamos la version de SDK porque si es mayor a la API 26, tenemos que crear el canal, si no lo es, no hacemos nada.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Nombre Canal"
            val descriptionText = "Descripcion Canal"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            //Registramos el canal en el sistema
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}