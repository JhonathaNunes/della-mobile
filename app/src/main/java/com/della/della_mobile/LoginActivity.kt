package com.della.della_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import com.della.della_mobile.services.ClientsService
import com.della.della_mobile.utils.Prefs
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (Prefs.getBoolean("Remember")) {
            val username = Prefs.getString("Username")
            val password = Prefs.getString("Password")
            txtUsername.setText(username)
            txtPassword.setText(password)
            check_remember.isChecked = true
        }

        btn_login.setOnClickListener {
            login()
        }

        txtPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()

                return@setOnEditorActionListener true
            }
            false
        }
    }

    override fun onResume() {
        super.onResume()

        openClient()
    }

    private fun login() {
        val username = txtUsername.text.toString()
        val password = txtPassword.text.toString()

        if (username == "aluno" && password == "impacta") {
            val intent = Intent(this, MainActivity::class.java)

            Prefs.setBoolean("Remember", check_remember.isChecked)

            if (check_remember.isChecked) {
                Prefs.setString("Username", username)
                Prefs.setString("Password", password)
            } else {
                Prefs.setString("Username", "")
                Prefs.setString("Password", "")
                Prefs.setString("profilePhoto", "")
            }

            startActivity(intent)
        } else {
            val alertDialog = AlertDialog.Builder(this)

            alertDialog.setTitle(R.string.login_failed)
            alertDialog.setMessage(R.string.incorrect_username_password)

            alertDialog.setPositiveButton(R.string.ok) { dialog, _: Int ->
                dialog.dismiss()
            }

            alertDialog.show()
        }
    }

    private fun openClient() {
        if (intent.hasExtra("clientId")) {
            Thread {
                val clientId = intent.getStringExtra("clientId")?.toLong() ?: -1
                val client = ClientsService.getClient(clientId)
                runOnUiThread {
                    intent.removeExtra("clientId")
                    val clientIntent = Intent(this, AddActivity::class.java)
                    clientIntent.putExtra("CLIENT", client)
                    startActivity(clientIntent)
                }
            }.start()
        }
    }
}
