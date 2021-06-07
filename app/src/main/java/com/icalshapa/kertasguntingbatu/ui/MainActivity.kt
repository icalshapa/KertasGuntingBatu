package com.icalshapa.kertasguntingbatu.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.icalshapa.kertasguntingbatu.R
import com.icalshapa.kertasguntingbatu.databinding.ActivityMainBinding
import com.icalshapa.kertasguntingbatu.enum.Player
import com.icalshapa.kertasguntingbatu.enum.Weapon
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    private lateinit var binding: ActivityMainBinding

    private var weaponHuman: Int = -1
    private var weaponRobot: Int = -1
    private var isGameFinished: Boolean = false

    private var winCount: Int = 0
    private var loseCount: Int = 0
    private var scoreColor: Int = 0

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
        binding.tvResult.text = getString(R.string.tv_result_vs)
        binding.ivRestart.visibility = View.INVISIBLE
        showScore()
        setBackgroundTheme(scoreColor)
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
                Log.d(TAG,"Game Restarted")
                setInitialState()
            }
        }
    }

    private fun startGame() {
        weaponRobot = Random.nextInt(0, 3)
        showSelection(weaponHuman, Player.HUMAN)
        Log.d(TAG, "weaponHuman: $weaponHuman")
        showSelection(weaponRobot, Player.ROBOT)
        Log.d(TAG,"weaponRobot: $weaponRobot")
        if ((weaponHuman + 1) % 3 == weaponRobot) {
            binding.tvResult.text = getString(R.string.tv_result_lose)
            loseCount++
            Log.d(TAG,"loseCount: $loseCount")
        } else {
            if (weaponHuman == weaponRobot) {
                binding.tvResult.text = getString(R.string.tv_result_draw)
                Log.d(TAG,"Draw")
            } else {
                binding.tvResult.text = getString(R.string.tv_result_win)
                winCount++
                scoreColor++
                Log.d(TAG,"winCount: $winCount, scoreColor: $scoreColor")
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
                ivScissor.setBackgroundResource(R.drawable.bg_weapon_ii)
            }
            Weapon.PAPER -> {
                ivPaper.setBackgroundResource(R.drawable.bg_weapon_ii)
            }
            Weapon.ROCK -> {
                ivRock.setBackgroundResource(R.drawable.bg_weapon_ii)
            }
            Weapon.NONE -> {
                ivScissor.setBackgroundResource(0)
                ivPaper.setBackgroundResource(0)
                ivRock.setBackgroundResource(0)
            }
        }
    }
    private fun setBackgroundTheme(score: Int){
        when(score){
            0 -> {
                binding.ivBackground.setBackgroundColor(ContextCompat.getColor(this,R.color.red))
            }
            5 -> {
                binding.ivBackground.setBackgroundColor(ContextCompat.getColor(this,R.color.orange))
                Log.d(TAG,"Color Changed")
            }
            10 -> {
                binding.ivBackground.setBackgroundColor(ContextCompat.getColor(this,R.color.yellow))
                Log.d(TAG,"Color Changed")
            }
            15 -> {
                binding.ivBackground.setBackgroundColor(ContextCompat.getColor(this,R.color.green))
                Log.d(TAG,"Color Changed")
            }
            20 -> {
                binding.ivBackground.setBackgroundColor(ContextCompat.getColor(this,R.color.blue))
                Log.d(TAG,"Color Changed")
            }
            25 -> {
                binding.ivBackground.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_200))
                Log.d(TAG,"Color Changed")
            }
            30 -> {
                binding.ivBackground.setBackgroundColor(ContextCompat.getColor(this,R.color.red))
                Log.d(TAG,"Color Changed")
                scoreColor = 0
            }
        }
    }
    private fun showScore(){
        binding.tvScoreHuman.text = "$winCount"
        binding.tvScoreRobot.text = "$loseCount"
    }
}