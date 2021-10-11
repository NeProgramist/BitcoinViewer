package com.example.bitcoinviewer.routing

import androidx.annotation.AnimRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.bitcoinviewer.R
import androidx.fragment.app.commit

enum class AppStage(val weight: Int) {
    Cancel(0), Transactions(1), Refill(1), AddTransaction(2)
}

operator fun AppStage.compareTo(other: AppStage?) : Int {
    return this.weight - (other?.weight ?: 0)
}

data class AnimationSet(
    @AnimRes val animEnter: Int = R.anim.slide_from_right,
    @AnimRes val animExit: Int = R.anim.slide_to_left,
    @AnimRes val popEnter: Int = R.anim.slide_from_left,
    @AnimRes val popExit: Int = R.anim.slide_to_right,
)

fun getAnimationSet(cur: AppStage?, prev: AppStage?) = when {
    prev == null -> {
        AnimationSet(
            animEnter = R.anim.no_anim,
            animExit = R.anim.slide_to_left,
            popEnter = R.anim.no_anim,
            popExit = R.anim.slide_to_right
        )
    }
    prev <= cur -> {
        AnimationSet(
            animEnter = R.anim.slide_from_right,
            animExit = R.anim.slide_to_left,
            popEnter = R.anim.slide_from_left,
            popExit = R.anim.slide_to_right
        )
    }
    else -> {
        AnimationSet(
            animEnter = R.anim.slide_from_left,
            animExit = R.anim.slide_to_right,
            popEnter = R.anim.slide_from_right,
            popExit = R.anim.slide_to_left
        )
    }
}

fun FragmentManager.changeFragment(
    fragment: Fragment,
    container: Int,
    backStack: Boolean = false,
    animationSet: AnimationSet,
) {
    commit {
        val (animEnter, animExit, popEnter, popExit) = animationSet
        setCustomAnimations(animEnter, animExit, popEnter, popExit)
        replace(container, fragment)
        if (backStack) addToBackStack(fragment::class.simpleName)
    }
}
