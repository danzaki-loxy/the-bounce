package com.v1n3tech.bounce

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.v1n3tech.bounce.fragments.ExploreFragment
import com.v1n3tech.bounce.fragments.HomeFragment
import com.v1n3tech.bounce.fragments.LibraryFragment
import com.v1n3tech.bounce.fragments.WalletFragment
import me.ibrahimsn.lib.SmoothBottomBar

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val exploreFragment = ExploreFragment()
    private val libraryFragment = LibraryFragment()
    private val walletFragment = WalletFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bottomBar = findViewById<SmoothBottomBar>(R.id.bottomBar)
        replaceFragment(homeFragment)

        bottomBar.onItemSelected = {
            when (it) {
                0 -> replaceFragment(homeFragment)
                1 -> replaceFragment(exploreFragment)
                2 -> replaceFragment(libraryFragment)
                3 -> replaceFragment(walletFragment)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            replace(R.id.frame, fragment)
            commit()
        }
    }
}