package com.dinosys.sportbook.extensions

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.dinosys.sportbook.R
import com.dinosys.sportbook.features.mytournament.MyTournamentFragment
import com.dinosys.sportbook.features.mytournament.detail.MyTournamentSpecificFragment
import com.dinosys.sportbook.features.mytournament.invitation.InvitationFragment
import com.dinosys.sportbook.features.mytournament.opponent.OpponentFragment
import com.dinosys.sportbook.features.mytournament.results.ResultFragment
import com.dinosys.sportbook.features.mytournament.timetable.TimeTableFragment
import com.dinosys.sportbook.features.mytournament.venue.VenueFragment
import com.dinosys.sportbook.features.signin.ForgotFragment
import com.dinosys.sportbook.features.signin.SignInFragment
import com.dinosys.sportbook.features.signup.SignUpFragment
import com.dinosys.sportbook.features.tournament.ProfileFragment
import com.dinosys.sportbook.features.tournament.StatisticFragment
import com.dinosys.sportbook.features.tournament.TournamentFragment
import com.dinosys.sportbook.features.tournament.overview.TournamentOverviewFragment
import com.dinosys.sportbook.features.tournament.signup.TournamentSignUpFragment
import com.dinosys.sportbook.utils.LogUtil


fun FragmentManager.openScreenByTag(tag:String, containerId: Int = R.id.ll_main_container, needFindInStack: Boolean = false, bundle: Bundle? = null) {
    val fragment = createFragmentByTAG(tag)
    fragment?.arguments = bundle
    when(fragment) {
        null -> LogUtil.e("FragmentManager", "Can not create fragment by tag ${tag}")
        else -> attachFragment(containerId, fragment, tag, needFindInStack)
    }
}


private fun FragmentManager.createFragmentByTAG(tag: String) : Fragment? =
    when(tag) {
        SignInFragment.TAG -> SignInFragment()
        SignUpFragment.TAG -> SignUpFragment()
        StatisticFragment.TAG -> StatisticFragment()
        ProfileFragment.TAG -> ProfileFragment()
        TournamentFragment.TAG -> TournamentFragment()
        ForgotFragment.TAG -> ForgotFragment()
        TournamentOverviewFragment.TAG -> TournamentOverviewFragment()
        TournamentSignUpFragment.TAG -> TournamentSignUpFragment()
        MyTournamentFragment.TAG -> MyTournamentFragment()
        MyTournamentSpecificFragment.TAG -> MyTournamentSpecificFragment()
        VenueFragment.TAG -> VenueFragment()
        OpponentFragment.TAG -> OpponentFragment()
        InvitationFragment.TAG -> InvitationFragment()
        TimeTableFragment.TAG -> TimeTableFragment()
        ResultFragment.TAG -> ResultFragment()
        else -> null
    }

private fun FragmentManager.addNewFragment(containerId: Int, fragment: Fragment, tag: String) {
    val transaction = this.beginTransaction()
    transaction.add(containerId, fragment, tag)
            .addToBackStack(tag)
            .commit()
}

fun FragmentManager.remove(fragment: Fragment) {
    val transaction = this.beginTransaction()
    transaction.remove(fragment)
            .commit()
}

fun FragmentManager.popBackStack(numberOfFragment: Int) {
    if (numberOfFragment <= 0) {
        LogUtil.e("FragmentManager", "[removeTopFragment] numbers <= 0")
        return
    }

    if (numberOfFragment > fragments.size) {
        LogUtil.e("FragmentManager", "[removeTopFragment] numbers > curentSizeBackStack")
        return
    }

    val transaction = this.beginTransaction()

    val fromPos = fragments.size - numberOfFragment
    for (currentPos in fromPos until fragments.size) {
        transaction.remove(fragments[currentPos])
        popBackStack()
    }

    transaction.commit()
}

private fun FragmentManager.attachFragment(containerId: Int,
                                           fragment: Fragment,
                                           tag: String,
                                           needFindInStack: Boolean) {
    when (needFindInStack) {
        false -> {
            this.addNewFragment(containerId, fragment, tag)
        }
        else -> {
            val previousFragment = this.findFragmentByTag(tag)
            if (previousFragment == null) {
                this.addNewFragment(containerId, fragment, tag)
            } else {
                this.beginTransaction()
                        .replace(containerId, previousFragment, tag)
                        .addToBackStack(tag)
                        .commit()
            }
        }
    }
}
