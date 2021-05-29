package com.della.della_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.della.della_mobile.models.Client
import com.della.della_mobile.services.ClientsService
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val args = intent.extras

        supportActionBar?.title = "Novo cliente"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_save.setOnClickListener { onClick() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun onClick() {
        val client = Client(
            fullName = field_fullname.text.toString(),
            email = field_email.text.toString(),
            cpf = field_cpf.text.toString(),
            cnpj = field_email.text.toString(),
        )

        Thread {
            ClientsService.create(client)
            runOnUiThread {
                Toast.makeText(this, R.string.client_added, Toast.LENGTH_SHORT)
                finish()
            }
        }.start()
    }
}