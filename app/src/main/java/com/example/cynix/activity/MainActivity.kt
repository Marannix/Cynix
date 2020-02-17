package com.example.cynix.activity

import android.os.Bundle
import com.example.cynix.R
import com.example.cynix.fragment.CharacterFragment

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCharacterFragmnet()
    }


    private fun initCharacterFragmnet() {
        val fragment = CharacterFragment.newInstance()
        supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, fragment).addToBackStack(fragment.javaClass.name)
        }
    }
}