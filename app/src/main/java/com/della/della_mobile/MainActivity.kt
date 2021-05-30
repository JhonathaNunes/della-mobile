package com.della.della_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        configureMenuLateral()

        btn_client.setOnClickListener { startClientActivity() }
        btn_orders.setOnClickListener {
            Toast.makeText(this, R.string.order, Toast.LENGTH_SHORT).show()
        }
        btn_material.setOnClickListener {
            Toast.makeText(this, R.string.materials, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem: SearchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView

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

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.actionLogout -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configureMenuLateral() {
        val toggle = ActionBarDrawerToggle(
                this,
                layoutMenuLateral,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        )

        layoutMenuLateral.addDrawerListener(toggle)
        toggle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_client -> {
                startClientActivity()
            }
            R.id.nav_order -> {
                Toast.makeText(this, R.string.order, Toast.LENGTH_SHORT).show()
            }
            R.id.nav_materials -> {
                Toast.makeText(this, R.string.materials, Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                finish()
            }
        }

        layoutMenuLateral.closeDrawer(GravityCompat.START)

        return true
    }
    
    private fun startClientActivity() {
        val intent = Intent(applicationContext, ClientsListActivity::class.java)

        startActivity(intent)
    }
}