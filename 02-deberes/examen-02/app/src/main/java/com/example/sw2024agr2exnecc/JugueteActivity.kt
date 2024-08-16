package com.example.sw2024agr2exnecc


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

class JugueteActivity : AppCompatActivity() {

    private lateinit var toyList: MutableList<Juguete>
    private lateinit var adapter: JugueteAdapter
    private lateinit var dbHelper: ESqliteHelperJuguete
    private lateinit var jugueteria: Jugueteria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juguete)
        val listViewToys = findViewById<ListView>(R.id.listViewToys)

        dbHelper = ESqliteHelperJuguete(this)
        jugueteria = intent.getParcelableExtra<Jugueteria>("JUGUETERIA")!!

        toyList = dbHelper.consultarJuguetesPorTienda(jugueteria.id).toMutableList()
        adapter = JugueteAdapter(this, toyList)
        listViewToys.adapter = adapter

        registerForContextMenu(listViewToys)

        val buttonCreateToy = findViewById<Button>(R.id.buttonCreateToy)
        buttonCreateToy.setOnClickListener {
            // Open activity to create a new toy
            val intent = Intent(this, EditJugueteActivity::class.java)
            intent.putExtra("JUGUETERIA_ID", jugueteria.id)
            startActivityForResult(intent, REQUEST_CODE_CREATE_TOY)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu_toy, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedItem = toyList[info.position]

        return when (item.itemId) {
            R.id.edit_toy -> {
                // Open EditJugueteActivity
                val intent = Intent(this, EditJugueteActivity::class.java)
                intent.putExtra("JUGUETE", selectedItem)
                startActivityForResult(intent, REQUEST_CODE_EDIT_TOY)
                true
            }
            R.id.delete_toy -> {
                // Delete the selected toy
                dbHelper.eliminarJuguete(selectedItem.id)
                toyList.removeAt(info.position)
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh the toy list when returning to this activity
        toyList.clear()
        toyList.addAll(dbHelper.consultarJuguetesPorTienda(jugueteria.id))
        adapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_CREATE_TOY, REQUEST_CODE_EDIT_TOY -> {
                    toyList.clear()
                    toyList.addAll(dbHelper.consultarJuguetesPorTienda(jugueteria.id))
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    companion object {
        const val REQUEST_CODE_CREATE_TOY = 1
        const val REQUEST_CODE_EDIT_TOY = 2
    }
}