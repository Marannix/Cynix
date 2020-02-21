package com.example.cynix.activity

import android.content.Intent
import android.os.Bundle
import com.example.cynix.R
import com.example.cynix.character.Character
import com.example.cynix.fragment.CharacterFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initCharacterFragment()
    }

    private fun initCharacterFragment() {
        supportFragmentManager.createFragment(
            fragmentId = R.id.fragmentContainer,
            factory = { CharacterFragment() },
            initializer = {
                setItemClickListener(object : CharacterFragment.OnFragmentInteractionListener {
                    override fun onFragmentInteraction(character: Character) {
                        val intent = Intent(context, CharacterDetailsActivity().javaClass)
                        startActivity(intent)
                    }
                })
            })
    }

}