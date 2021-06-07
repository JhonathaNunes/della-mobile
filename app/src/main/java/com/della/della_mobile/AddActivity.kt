package com.della.della_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.della.della_mobile.models.Client
import com.della.della_mobile.services.ClientsService
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private var client: Client? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val args = intent.extras

        client = args?.getSerializable("CLIENT") as? Client

        supportActionBar?.title = client?.fullName ?: "Novo cliente"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (client !== null) fillFields()

        btn_save.setOnClickListener { onClick() }
    }

    private fun fillFields() {
        field_fullname.setText(client?.fullName)
        field_email.setText(client?.email)
        field_cpf.setText(client?.cpf)
        field_cnpj.setText(client?.cnpj)

        field_fullname.isEnabled = false
        field_email.isEnabled = false
        field_cpf.isEnabled = false
        field_cnpj.isEnabled = false
        btn_save.visibility = View.GONE
    }

    private fun onClick() {
        val newClient = Client(
            fullName = field_fullname.text.toString(),
            email = field_email.text.toString(),
            cpf = field_cpf.text.toString(),
            cnpj = field_cnpj.text.toString(),
        )

        Thread {
            ClientsService.create(newClient)
            runOnUiThread {
                Toast.makeText(this, R.string.client_added, Toast.LENGTH_SHORT).show()
                finish()
            }
        }.start()
    }

    private fun taskDelete() {
        Thread {
            client?.let { ClientsService.delete(it) }
            runOnUiThread {
                Toast.makeText(
                    this,
                    getString(R.string.client_deleted).format(client?.fullName),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }.start()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (client !== null) menuInflater.inflate(R.menu.edit_model_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
            R.id.action_delete -> taskDelete()
        }

        return super.onOptionsItemSelected(item)
    }
}