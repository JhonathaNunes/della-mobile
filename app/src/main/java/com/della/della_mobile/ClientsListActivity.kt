package com.della.della_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.della.della_mobile.adapters.ClientAdapter
import com.della.della_mobile.models.Client
import com.della.della_mobile.services.ClientsService
import kotlinx.android.synthetic.main.activity_clients_list.*

class ClientsListActivity : AppCompatActivity() {
    private var clients = listOf<Client>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clients_list)

        supportActionBar?.title = getString(R.string.client)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recycler_clients.layoutManager = LinearLayoutManager(this)
        recycler_clients.itemAnimator = DefaultItemAnimator()
        recycler_clients.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()

        taskClients()
    }

    fun taskClients() {
        clients = ClientsService.getClients()
        recycler_clients.adapter = ClientAdapter(clients) { onClickClient(it) }
    }

    fun onClickClient(client: Client) {
        Toast.makeText(this, client.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}