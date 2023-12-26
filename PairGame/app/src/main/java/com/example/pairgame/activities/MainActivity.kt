package com.example.pairgame.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pairgame.databinding.ActivityMainBinding
import com.example.pairgame.fragments.MenuFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(binding.fragmentHolder.id, MenuFragment.newInstance("0")).commit()
    }
}