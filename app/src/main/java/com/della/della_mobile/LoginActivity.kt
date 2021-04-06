package com.della.della_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            login()
        }
    }

    fun login() {
        val username = txtUsername.text.toString()
        val password = txtPassword.text.toString()

        if (username == "aluno" && password == "impacta") {
            val intent = Intent(this, MainActivity::class.java)

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
}