package com.icalshapa.kertasguntingbatu.ui.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.icalshapa.kertasguntingbatu.R
import com.icalshapa.kertasguntingbatu.data.preference.UserPreference
import com.icalshapa.kertasguntingbatu.databinding.ActivityMenuBinding
import com.icalshapa.kertasguntingbatu.ui.game.GameActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showGreetings()
        setClickListeners()
    }

    private fun showGreetings() {
        val snackBar = Snackbar.make(
            binding.root,
            String.format(getString(R.string.text_menu_snackbar, UserPreference(this).userName)),
            Snackbar.LENGTH_INDEFINITE
        )
        snackBar.setAction(getString(R.string.text_menu_snackbar_dismiss)) {
            snackBar.dismiss()
        }
        snackBar.show()
    }

    private fun setClickListeners() {
        binding.ivMenuVsHuman.setOnClickListener {
            GameActivity.startThisActivity(this, GameActivity.VS_HUMAN)
        }
        binding.ivMenuVsRobot.setOnClickListener {
            GameActivity.startThisActivity(this, GameActivity.VS_ROBOT)
        }
    }
}