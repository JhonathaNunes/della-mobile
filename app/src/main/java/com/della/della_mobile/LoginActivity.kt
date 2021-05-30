package com.della.della_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
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

    private fun login() {
        val username = txtUsername.text.toString()
        val password = txtPassword.text.toString()

        if (username == "aluno" && password == "impacta") {
            val intent = Intent(this, MainActivity::class.java)

            Prefs.setBoolean("Remember", check_remember.isChecked)

            if (check_remember.isChecked) {
                Prefs.setString("UserName", username)
                Prefs.setString("Password", password)
            } else {
                Prefs.setString("UserName", "")
                Prefs.setString("Password", "")
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
}
