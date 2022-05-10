package br.com.fiap.acid.cap4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private val btnToken by lazy { findViewById<Button>(R.id.btn_token) }
    private val tvToken by lazy { findViewById<TextView>(R.id.tv_token) }

    private var storedToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        handleIntentExtras(intent)
    }

    override fun onStart() {
        super.onStart()

        btnToken.setOnClickListener { requestUserToken() }
    }

    private fun requestUserToken() {
        btnToken.isEnabled = false

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                storedToken = task.result

                Log.i(TAG, "Device Token: $storedToken")

                tvToken.text = storedToken
                tvToken.visibility = View.VISIBLE
                btnToken.isEnabled = true
            }
    }

    private fun handleIntentExtras(intent: Intent) {
        // Título (title) e corpo (body) da notificação que vai pra bandeja do sistema
        // não é recuperável pela Activity, apenas os dados que vem como extras:

        intent.getStringExtra(EXTRA_USER_ID)?.let { userId ->
            Log.i(TAG, "ID do usuário: $userId")
        }

        intent.getStringExtra(EXTRA_MESSAGE)?.let { message ->
            Log.i(TAG, "Mensagem: $message")
        }
    }

    companion object {
        private const val TAG = "Main"
        private const val EXTRA_USER_ID = "id_user"
        private const val EXTRA_MESSAGE = "mensagem"
    }
}