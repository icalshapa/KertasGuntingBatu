package com.icalshapa.kertasguntingbatu.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.icalshapa.kertasguntingbatu.R
import com.icalshapa.kertasguntingbatu.data.preference.UserPreference
import com.icalshapa.kertasguntingbatu.databinding.FragmentNameFormBinding
import com.icalshapa.kertasguntingbatu.ui.menu.MenuActivity


class NameFormFragment : Fragment() {
    private lateinit var binding : FragmentNameFormBinding
    private lateinit var userPreference: UserPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNameFormBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefilledCurrentName()
    }

    private fun prefilledCurrentName() {
        context?.let {
            userPreference = UserPreference(it)
            binding.etNamePlayer.setText(userPreference.userName.orEmpty())
            binding.etNamePlayerTwo.setText(userPreference.userNameTwo.orEmpty())
        }
    }

    private fun isFormFilled(): Boolean {
        val name = binding.etNamePlayer.text.toString()
        var isFormValid = true

        if (name.isEmpty()) {
            isFormValid = false
            Toast.makeText(context, getString(R.string.text_name_error_toast), Toast.LENGTH_SHORT).show()
        }
        return isFormValid
    }

    fun navigateToMenuGame(){
        if(isFormFilled()){
            userPreference.userName = binding.etNamePlayer.text.toString()
            userPreference.userNameTwo = binding.etNamePlayerTwo.text.toString()
            context?.startActivity(Intent(context, MenuActivity::class.java))
        }
    }

}