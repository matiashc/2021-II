package co.edu.ufps.a2021_ii.vistas

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import co.edu.ufps.a2021_ii.fragmentos.LibroFragment
import co.edu.ufps.a2021_ii.fragmentos.MisLibrosFragment
import co.edu.ufps.a2021_ii.fragmentos.PerfilFragment

class MyAdapter(
    var context: Context,
    fm: FragmentManager,
val totalTabs: Int): FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                LibroFragment()
            }
            1 -> {
                MisLibrosFragment()
            }
            2 -> {
                PerfilFragment()
            }
            else -> getItem(position)
        }

    }

}