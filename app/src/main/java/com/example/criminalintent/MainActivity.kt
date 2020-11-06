package com.example.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var currentFragment=supportFragmentManager.findFragmentById(R.id.fragment_container)


        if(currentFragment ==null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, CrimeListFragment.newInstance())
                .commit()
        }



    }
}