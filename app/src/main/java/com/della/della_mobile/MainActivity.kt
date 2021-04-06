package com.della.della_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(applicationContext, "Search", Toast.LENGTH_LONG).show()
            }
            R.id.action_add -> {
                Toast.makeText(applicationContext, "Add", Toast.LENGTH_LONG).show()
            }
            R.id.action_logout -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}