package com.icalshapa.kertasguntingbatu.ui.intro

import android.graphics.Color
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.icalshapa.kertasguntingbatu.R

class IntroActivity : AppIntro2() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        isWizardMode = true
        // Make sure you don't call setContentView!

        // Call addSlide passing your Fragments.
        // You can use AppIntroFragment to use a pre-built fragment
        addSlide(
            AppIntroFragment.newInstance(
                description = getString(R.string.text_intro_friend),
                imageDrawable = R.drawable.ic_vs_human,
                descriptionColor = Color.BLACK,
                backgroundColor = ContextCompat.getColor(this,R.color.orange)
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                description = getString(R.string.text_intro_computer),
                imageDrawable = R.drawable.ic_vs_robot,
                descriptionColor = Color.BLACK,
                backgroundColor = ContextCompat.getColor(this,R.color.yellow)
            )
        )
        addSlide(NameFormFragment())

    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        // Decide what to do when the user clicks on "Done"
        if(currentFragment is NameFormFragment) {
            currentFragment.navigateToMenuGame()
        }
    }
}