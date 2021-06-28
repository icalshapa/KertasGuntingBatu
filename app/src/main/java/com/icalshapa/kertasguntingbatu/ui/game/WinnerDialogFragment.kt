package com.icalshapa.kertasguntingbatu.ui.game

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.icalshapa.kertasguntingbatu.R
import com.icalshapa.kertasguntingbatu.data.preference.UserPreference
import com.icalshapa.kertasguntingbatu.databinding.FragmentWinnerDialogBinding
import com.icalshapa.kertasguntingbatu.enum.Weapon


class WinnerDialogFragment(private var winner: Int, private var chosenWeaponOne: Int, private var chosenWeaponTwo: Int) :
    DialogFragment() {

    private lateinit var binding: FragmentWinnerDialogBinding
    private lateinit var userPreference: UserPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWinnerDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        context?.let {
            userPreference = UserPreference(it)
            binding.tvDialogNameOne.text = userPreference.userName
            binding.tvDialogNameTwo.text = userPreference.userNameTwo
            when(Weapon.fromInt(chosenWeaponOne)){
                Weapon.SCISSOR -> binding.ivDialogWeaponOne.setImageDrawable(ContextCompat.getDrawable(it,R.drawable.ic_weapon_scissor))
                Weapon.PAPER -> binding.ivDialogWeaponOne.setImageDrawable(ContextCompat.getDrawable(it,R.drawable.ic_weapon_paper))
                Weapon.ROCK -> binding.ivDialogWeaponOne.setImageDrawable(ContextCompat.getDrawable(it,R.drawable.ic_weapon_rock))
                Weapon.NONE -> binding.ivDialogWeaponOne.setImageDrawable(ContextCompat.getDrawable(it,R.drawable.ic_dialog_vs))
            }
            when(Weapon.fromInt(chosenWeaponTwo)){
                Weapon.SCISSOR -> binding.ivDialogWeaponTwo.setImageDrawable(ContextCompat.getDrawable(it,R.drawable.ic_weapon_scissor))
                Weapon.PAPER -> binding.ivDialogWeaponTwo.setImageDrawable(ContextCompat.getDrawable(it,R.drawable.ic_weapon_paper))
                Weapon.ROCK -> binding.ivDialogWeaponTwo.setImageDrawable(ContextCompat.getDrawable(it,R.drawable.ic_weapon_rock))
                Weapon.NONE -> binding.ivDialogWeaponTwo.setImageDrawable(ContextCompat.getDrawable(it,R.drawable.ic_dialog_vs))
            }
            when(winner){
                2 -> {
                    binding.tvDialogNameWinner.text = userPreference.userName
                    binding.tvDialogWin.text = getText(R.string.tv_result_win)
                }
                1 -> binding.tvDialogWin.text = getText(R.string.tv_result_draw)
                0 -> {
                    binding.tvDialogNameWinner.text = userPreference.userNameTwo
                    binding.tvDialogWin.text = getText(R.string.tv_result_win)
                }
            }
        }
        binding.btnDialogNext.setOnClickListener {
            dismiss()
        }
        binding.btnDialogQuit.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}