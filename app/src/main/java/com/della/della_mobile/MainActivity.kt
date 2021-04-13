package com.della.della_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem: SearchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView

        if (searchItem != null) {
            searchItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    Toast.makeText(applicationContext, newText, Toast.LENGTH_SHORT).show()

                    return false
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    Toast.makeText(applicationContext, query, Toast.LENGTH_SHORT).show()

                    return false
                }
            })
        }

        btnClient.setOnClickListener { startActivity(getString(R.string.client)) }
        btnOrders.setOnClickListener { startActivity(getString(R.string.order)) }
        btnMaterial.setOnClickListener { startActivity(getString(R.string.materials)) }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.actionAdd -> {
                startActivity("Add")
            }
            R.id.actionLogout -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startActivity(activity: String) {
        val intent = Intent(applicationContext, AddActivity::class.java)

        intent.putExtra("action", activity)

        startActivity(intent)
    }
}