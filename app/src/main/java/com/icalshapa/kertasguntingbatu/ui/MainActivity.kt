package com.icalshapa.kertasguntingbatu.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.icalshapa.kertasguntingbatu.R
import com.icalshapa.kertasguntingbatu.databinding.ActivityMainBinding
import com.icalshapa.kertasguntingbatu.enum.Player
import com.icalshapa.kertasguntingbatu.enum.Weapon
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var weaponHuman: Int = -1
    private var weaponRobot: Int = -1
    private var isGameFinished: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setClickEvent()
        setInitialState()
    }

    private fun setInitialState() {
        showSelection(weaponHuman, Player.HUMAN)
        showSelection(weaponRobot, Player.ROBOT)
        binding.tvResult.text = "VS"
        binding.ivRestart.visibility = View.INVISIBLE
    }

    private fun setClickEvent() {
        binding.ivWeaponHumanScissor.setOnClickListener {
            if (!isGameFinished) {
                weaponHuman = 2
                startGame()
            }
        }
        binding.ivWeaponHumanPaper.setOnClickListener {
            if (!isGameFinished) {
                weaponHuman = 1
                startGame()
            }
        }
        binding.ivWeaponHumanRock.setOnClickListener {
            if (!isGameFinished) {
                weaponHuman = 0
                startGame()
            }
        }
        binding.ivRestart.setOnClickListener {
            if (isGameFinished) {
                isGameFinished = false
                weaponHuman = -1
                weaponRobot = -1
                setInitialState()
            }
        }
    }

    private fun startGame() {
        weaponRobot = Random.nextInt(0, 3)
        showSelection(weaponHuman, Player.HUMAN)
        showSelection(weaponRobot, Player.ROBOT)
        if ((weaponHuman + 1) % 3 == weaponRobot) {
            binding.tvResult.text = "LOSE!!"
        } else {
            if (weaponHuman == weaponRobot) {
                binding.tvResult.text = "DRAW!!"
            } else {
                binding.tvResult.text = "WIN!!"
            }
        }
        isGameFinished = true
        binding.ivRestart.visibility = View.VISIBLE
    }

    private fun showSelection(weapon: Int, player: Player) {
        val ivScissor: ImageView?
        val ivPaper: ImageView?
        val ivRock: ImageView?
        if (player == Player.HUMAN) {
            ivScissor = binding.ivWeaponHumanScissor
            ivPaper = binding.ivWeaponHumanPaper
            ivRock = binding.ivWeaponHumanRock
        } else {
            ivScissor = binding.ivWeaponRobotScissor
            ivPaper = binding.ivWeaponRobotPaper
            ivRock = binding.ivWeaponRobotRock
        }
        when (Weapon.fromInt(weapon)) {
            Weapon.SCISSOR -> {
                ivScissor.setBackgroundResource(R.drawable.bg_weapon)
            }
            Weapon.PAPER -> {
                ivPaper.setBackgroundResource(R.drawable.bg_weapon)
            }
            Weapon.ROCK -> {
                ivRock.setBackgroundResource(R.drawable.bg_weapon)
            }
            Weapon.NONE -> {
                ivScissor.setBackgroundResource(0)
                ivPaper.setBackgroundResource(0)
                ivRock.setBackgroundResource(0)
            }
        }
    }
}