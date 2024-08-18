    package com.example.sw2024agr2proyecto

    import android.os.Bundle
    import androidx.fragment.app.Fragment
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import com.example.sw2024agr2proyecto.fragments.MyRecipesFragment
    import com.example.sw2024agr2proyecto.fragments.SavedRecipesFragment
    import com.google.android.material.tabs.TabLayout

    class ProfileFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_profile, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Configura la lógica de las pestañas
            val tab_layout = view.findViewById<TabLayout>(R.id.tab_layout)
            tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> replaceFragment(MyRecipesFragment()) // Fragmento para Mis Recetas
                        1 -> replaceFragment(SavedRecipesFragment()) // Fragmento para Recetas Guardadas
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })

            // Iniciar con el fragmento "Mis Recetas"
            replaceFragment(MyRecipesFragment())
        }

        private fun replaceFragment(fragment: Fragment) {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }