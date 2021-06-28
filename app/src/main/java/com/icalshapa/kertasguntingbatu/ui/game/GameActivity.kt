package com.icalshapa.kertasguntingbatu.ui.game

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.icalshapa.kertasguntingbatu.R
import com.icalshapa.kertasguntingbatu.data.preference.UserPreference
import com.icalshapa.kertasguntingbatu.databinding.ActivityMainBinding
import com.icalshapa.kertasguntingbatu.enum.Player
import com.icalshapa.kertasguntingbatu.enum.Weapon
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private val TAG = GameActivity::class.java.simpleName

    private lateinit var binding: ActivityMainBinding

    private var weaponPlayerOne: Int = -1
    private var weaponPlayerTwo: Int = -1
    private var isGameFinished: Boolean = false

    private var winStatus: Int = -1
    private var winCountOne: Int = 0
    private var winCountTwo: Int = 0
    private var scoreColor: Int = 0

    private var playMode: Int = VS_ROBOT
    private var playTurn: Player = Player.PLAYER_ONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        getIntentExtras()
        setPlayerNames()
        setClickEvent()
        setInitialState()
    }

    private fun getIntentExtras() {
        playMode = intent.getIntExtra(PLAY_MODE, VS_ROBOT)
    }

    private fun setPlayerNames() {
        binding.tvNameOne.text = UserPreference(this).userName
        binding.tvNameTwo.text = UserPreference(this).userNameTwo
    }

    private fun setInitialState() {
        showSelection(weaponPlayerOne, Player.PLAYER_ONE)
        showSelection(weaponPlayerTwo, Player.PLAYER_TWO)
        binding.tvResult.text = getString(R.string.tv_result_vs)
        binding.ivRestart.visibility = View.INVISIBLE
        binding.tvLock.visibility = View.INVISIBLE
        showScore()
        setBackgroundTheme(scoreColor)
        if (playMode == VS_ROBOT) {
            binding.tvTurn.visibility = View.INVISIBLE
            binding.tvTurnName.visibility = View.INVISIBLE
            binding.tvNameTwo.visibility = View.INVISIBLE
            binding.ivWeaponRobotPaper.visibility = View.VISIBLE
            binding.ivWeaponRobotRock.visibility = View.VISIBLE
            binding.ivWeaponRobotScissor.visibility = View.VISIBLE
            binding.ivPlayerRobot.visibility = View.VISIBLE
        } else {
            binding.tvTurn.visibility = View.VISIBLE
            binding.tvTurnName.visibility = View.VISIBLE
            binding.tvNameTwo.visibility = View.VISIBLE
            binding.ivWeaponRobotPaper.visibility = View.INVISIBLE
            binding.ivWeaponRobotRock.visibility = View.INVISIBLE
            binding.ivWeaponRobotScissor.visibility = View.INVISIBLE
            binding.ivPlayerRobot.visibility = View.INVISIBLE
            playTurn = Player.PLAYER_ONE
            binding.tvTurnName.text = UserPreference(this).userName
        }
    }

    private fun setClickEvent() {
        binding.ivWeaponHumanScissor.setOnClickListener {
            if (!isGameFinished) {
                if (playTurn == Player.PLAYER_ONE) {
                    weaponPlayerOne = 2
                    showSelection(weaponPlayerOne, Player.PLAYER_ONE)
                    Log.d(TAG, "weaponPlayerOne $weaponPlayerOne")
                    if (playMode == VS_ROBOT) {
                        startGame()
                    } else {
                        binding.tvLock.visibility = View.VISIBLE
                    }
                } else {
                    weaponPlayerTwo = 2
                    showSelection(weaponPlayerTwo, Player.PLAYER_ONE)
                    Log.d(TAG, "weaponPlayerTwo $weaponPlayerTwo")
                    binding.tvLock.visibility = View.VISIBLE
                }
            }
        }
        binding.ivWeaponHumanPaper.setOnClickListener {
            if (!isGameFinished) {
                if (playTurn == Player.PLAYER_ONE) {
                    weaponPlayerOne = 1
                    showSelection(weaponPlayerOne, Player.PLAYER_ONE)
                    Log.d(TAG, "weaponPlayerOne $weaponPlayerOne")
                    if (playMode == VS_ROBOT) {
                        startGame()
                    } else {
                        binding.tvLock.visibility = View.VISIBLE
                    }
                } else {
                    weaponPlayerTwo = 1
                    showSelection(weaponPlayerTwo, Player.PLAYER_ONE)
                    Log.d(TAG, "weaponPlayerTwo $weaponPlayerTwo")
                    binding.tvLock.visibility = View.VISIBLE
                }
            }
        }
        binding.ivWeaponHumanRock.setOnClickListener {
            if (!isGameFinished) {
                if (playTurn == Player.PLAYER_ONE) {
                    weaponPlayerOne = 0
                    showSelection(weaponPlayerOne, Player.PLAYER_ONE)
                    Log.d(TAG, "weaponPlayerOne $weaponPlayerOne")
                    if (playMode == VS_ROBOT) {
                        startGame()
                    } else {
                        binding.tvLock.visibility = View.VISIBLE
                    }
                } else {
                    weaponPlayerTwo = 0
                    showSelection(weaponPlayerTwo, Player.PLAYER_ONE)
                    Log.d(TAG, "weaponPlayerTwo $weaponPlayerTwo")
                    binding.tvLock.visibility = View.VISIBLE
                }
            }
        }
        binding.ivRestart.setOnClickListener {
            resetGame()
        }
        binding.tvLock.setOnClickListener {
            if (playTurn == Player.PLAYER_ONE) {
                playTurn = Player.PLAYER_TWO
                showSelection(weaponPlayerTwo, Player.PLAYER_ONE)
                binding.tvLock.visibility = View.INVISIBLE
                binding.tvTurnName.text = UserPreference(this).userNameTwo
                Toast.makeText(this, getString(R.string.text_game_toast_locked), Toast.LENGTH_SHORT)
                    .show()
            } else {
                startGame()
            }
        }
    }

    private fun startGame() {
        if (playMode == VS_ROBOT) {
            weaponPlayerTwo = Random.nextInt(0, 3)
            showSelection(weaponPlayerTwo, Player.PLAYER_TWO)
            Log.d(TAG, "weaponPlayerTwo: $weaponPlayerTwo")
            binding.ivRestart.visibility = View.VISIBLE
        }
        if ((weaponPlayerOne + 1) % 3 == weaponPlayerTwo) {
            winStatus = 0
            winCountTwo++
            Log.d(TAG, "winCountTwo: $winCountTwo")
            winState(winStatus)
        } else {
            if (weaponPlayerOne == weaponPlayerTwo) {
                winStatus = 1
                Log.d(TAG, "Draw")
                winState(winStatus)
            } else {
                winStatus = 2
                winCountOne++
                scoreColor++
                Log.d(TAG, "winCountOne: $winCountOne, scoreColor: $scoreColor")
                winState(winStatus)
            }
        }
        isGameFinished = true
        if (playMode == VS_HUMAN) {
            WinnerDialogFragment(winStatus, weaponPlayerOne, weaponPlayerTwo).show(
                supportFragmentManager,
                null
            )
            resetGame()
        }
    }

    private fun resetGame() {
        if (isGameFinished) {
            isGameFinished = false
            weaponPlayerOne = -1
            weaponPlayerTwo = -1
            Log.d(TAG, "Game Restarted")
            setInitialState()
        }
    }

    private fun winState(status: Int) {
        if (playMode == VS_ROBOT) {
            when (status) {
                0 -> {
                    binding.tvResult.text = getString(R.string.tv_result_lose)
                }
                1 -> {
                    binding.tvResult.text = getString(R.string.tv_result_draw)
                }
                2 -> {
                    binding.tvResult.text = getString(R.string.tv_result_win)
                }
            }
        }
    }

    private fun showSelection(weapon: Int, player: Player) {
        val ivScissor: ImageView?
        val ivPaper: ImageView?
        val ivRock: ImageView?
        if (player == Player.PLAYER_ONE) {
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
                ivPaper.setBackgroundResource(0)
                ivRock.setBackgroundResource(0)
            }
            Weapon.PAPER -> {
                ivScissor.setBackgroundResource(0)
                ivPaper.setBackgroundResource(R.drawable.bg_weapon_ii)
                ivRock.setBackgroundResource(0)
            }
            Weapon.ROCK -> {
                ivScissor.setBackgroundResource(0)
                ivPaper.setBackgroundResource(0)
                ivRock.setBackgroundResource(R.drawable.bg_weapon_ii)
            }
            Weapon.NONE -> {
                ivScissor.setBackgroundResource(0)
                ivPaper.setBackgroundResource(0)
                ivRock.setBackgroundResource(0)
            }
        }
    }

    private fun setBackgroundTheme(score: Int) {
        if (playMode == VS_ROBOT) {
            when (score) {
                0 -> {
                    binding.ivBackground.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.red
                        )
                    )
                }
                5 -> {
                    binding.ivBackground.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.orange
                        )
                    )
                    Log.d(TAG, "Color Changed")
                }
                10 -> {
                    binding.ivBackground.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.yellow
                        )
                    )
                    Log.d(TAG, "Color Changed")
                }
                15 -> {
                    binding.ivBackground.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.green
                        )
                    )
                    Log.d(TAG, "Color Changed")
                }
                20 -> {
                    binding.ivBackground.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.blue
                        )
                    )
                    Log.d(TAG, "Color Changed")
                }
                25 -> {
                    binding.ivBackground.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.purple_200
                        )
                    )
                    Log.d(TAG, "Color Changed")
                }
                30 -> {
                    binding.ivBackground.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.red
                        )
                    )
                    Log.d(TAG, "Color Changed")
                    scoreColor = 0
                }
            }
        } else {
            if (winCountOne == winCountTwo) {
                binding.ivBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.green
                    )
                )
                Log.d(TAG, "Color Changed")
            } else {
                if (winCountOne > winCountTwo) {
                    binding.ivBackground.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.yellow
                        )
                    )
                    Log.d(TAG, "Color Changed")
                } else {
                    binding.ivBackground.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.purple_200
                        )
                    )
                    Log.d(TAG, "Color Changed")
                }
            }
        }
    }

    private fun showScore() {
        binding.tvScoreOne.text = "$winCountOne"
        binding.tvScoreTwo.text = "$winCountTwo"
    }

    companion object {
        const val PLAY_MODE = "Play Mode"
        const val VS_ROBOT = 0
        const val VS_HUMAN = 1
        fun startThisActivity(context: Context, playMode: Int) {
            val intent = Intent(context, GameActivity::class.java)
            intent.putExtra(PLAY_MODE, playMode)
            context.startActivity(intent)
        }
    }
}