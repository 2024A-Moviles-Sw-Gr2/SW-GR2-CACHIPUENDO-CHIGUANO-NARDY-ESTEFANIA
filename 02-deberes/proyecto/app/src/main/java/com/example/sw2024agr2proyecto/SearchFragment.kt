package com.example.sw2024agr2proyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialog

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // Configura el SearchView, RecyclerView para los resultados y el botón para abrir el filtro
        val filterButton = view.findViewById<Button>(R.id.btnOpenFilters)
        filterButton.setOnClickListener {
            showFilterBottomSheet()
        }
        return view
    }

    private fun showFilterBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_filters)
        bottomSheetDialog.show()
    }
}
