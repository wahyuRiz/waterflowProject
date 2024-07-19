package com.example.waterflow

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.waterflow.fragments.HomeFragment
import com.example.waterflow.fragments.NotificationFragment
import com.example.waterflow.fragments.SettingFragment
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<CurvedBottomNavigation>(R.id.bottomNavigation)
        bottomNavigation.add(
            CurvedBottomNavigation.Model(1,"Notification",R.drawable.baseline_notifications_24)
        )
        bottomNavigation.add(
            CurvedBottomNavigation.Model(2,"Home",R.drawable.baseline_home_24)
        )
        bottomNavigation.add(
            CurvedBottomNavigation.Model(3,"Setting",R.drawable.baseline_settings_24)
        )

        bottomNavigation.setOnClickMenuListener{
            when(it.id){
                1 -> {
                    replaceFragment(NotificationFragment())
                }
                2 -> {
                    replaceFragment(HomeFragment())
                }
                3 -> {
                    replaceFragment(SettingFragment())
                }

            }
        }

        replaceFragment(HomeFragment())
        bottomNavigation.show(2)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer,fragment)
            .commit()
    }
}