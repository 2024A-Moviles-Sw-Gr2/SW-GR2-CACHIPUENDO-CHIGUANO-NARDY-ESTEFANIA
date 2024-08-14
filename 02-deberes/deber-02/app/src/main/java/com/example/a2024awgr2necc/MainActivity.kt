package com.example.a2024awgr2necc

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.a2024awgr2necc.ESqliteHelperJugueteria
import com.example.a2024awgr2necc.EditJugueteriaActivity
import com.example.a2024awgr2necc.JugueteActivity
import com.example.a2024awgr2necc.Jugueteria
import com.example.a2024awgr2necc.R

class MainActivity : AppCompatActivity() {

    private lateinit var toyStoreList: MutableList<Jugueteria>
    private lateinit var adapter: JugueteriaAdapter
    private lateinit var dbHelper: ESqliteHelperJugueteria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.listView)

        dbHelper = ESqliteHelperJugueteria(this)
        toyStoreList = dbHelper.getAllToyStores().toMutableList()
        adapter = JugueteriaAdapter(this, toyStoreList)
        listView.adapter = adapter

        registerForContextMenu(listView)

        val buttonCreate = findViewById<Button>(R.id.buttonCreate)
        buttonCreate.setOnClickListener {
            // Open activity to create a new toy store
            val intent = Intent(this, EditJugueteriaActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_CREATE_TOY_STORE)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedItem = toyStoreList[info.position]

        return when (item.itemId) {
            R.id.edit -> {
                // Open EditJugueteriaActivity
                val intent = Intent(this, EditJugueteriaActivity::class.java)
                intent.putExtra("JUGUETERIA", selectedItem)
                startActivityForResult(intent, REQUEST_CODE_EDIT_TOY_STORE)
                true
            }
            R.id.delete -> {
                // Delete the selected toy store
                dbHelper.eliminarJugueteria(selectedItem.id)
                toyStoreList.removeAt(info.position)
                adapter.notifyDataSetChanged()
                true
            }
            R.id.view_toys -> {
                // Open JugueteActivity to view toys in the selected toy store
                val intent = Intent(this, JugueteActivity::class.java)
                intent.putExtra("JUGUETERIA", selectedItem)
                startActivity(intent)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh the toy store list when returning to this activity
        toyStoreList.clear()
        toyStoreList.addAll(dbHelper.getAllToyStores())
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_CREATE_TOY_STORE, REQUEST_CODE_EDIT_TOY_STORE -> {
                    // Refresh the toy store list when returning to this activity
                    toyStoreList.clear()
                    toyStoreList.addAll(dbHelper.getAllToyStores())
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    companion object {
        const val REQUEST_CODE_CREATE_TOY_STORE = 1
        const val REQUEST_CODE_EDIT_TOY_STORE = 2
    }
}