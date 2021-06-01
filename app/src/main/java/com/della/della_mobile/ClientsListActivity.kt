package com.della.della_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.della.della_mobile.adapters.ClientAdapter
import com.della.della_mobile.models.Client
import com.della.della_mobile.services.ClientsService
import com.della.della_mobile.utils.NotificationUtil
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

    private fun sendNotification(client: Client) {
        val intent = Intent(this, AddActivity::class.java)

        intent.putExtra("CLIENT", client)
        NotificationUtil.create(
            1,
            intent,
            getString(R.string.app_name),
            "O cliente ${client.toString()} tem uma mensagem"
        )
    }

    private fun taskClients() {
        Thread {
            clients = ClientsService.getClients()
            runOnUiThread {
                recycler_clients.adapter = ClientAdapter(clients) { onClickClient(it) }
                sendNotification(clients[0])
            }
        }.start()
    }

    private fun onClickClient(client: Client) {
        val intent = Intent(this, AddActivity::class.java)

        intent.putExtra("CLIENT", client)

        startActivity(intent)
    }

    private fun startAddClientActivity() {
        val intent = Intent(this, AddActivity::class.java)

        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_activity_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
            R.id.actionAdd -> startAddClientActivity()
        }

        return super.onOptionsItemSelected(item)
    }
}