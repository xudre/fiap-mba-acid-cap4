package br.com.fiap.acid.cap4.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class AppMessageService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.i(TAG, "Título: ${message.notification?.title}")
        Log.i(TAG, "Texto: ${message.notification?.body}")
        Log.i(TAG, "Dados: ${message.data}")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        // Enviar o novo token do device para o backend
        Log.i(TAG, "Novo token recebido: $token")
    }

    companion object {
        private const val TAG = "AppMessage"
    }

}