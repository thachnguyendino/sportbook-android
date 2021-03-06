package com.dinosys.sportbook.di.authentication

import com.dinosys.sportbook.di.NetworkModule
import com.dinosys.sportbook.features.signin.ForgotFragment
import com.dinosys.sportbook.features.signin.SignInFragment
import com.dinosys.sportbook.features.signup.SignUpFragment
import com.dinosys.sportbook.services.MyFirebaseInstanceIDService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AuthenticationModule::class,
        NetworkModule::class)
)
interface AuthenticationComponent {
        fun inject(singInfragment: SignInFragment)
        fun inject(forgotFragment: ForgotFragment)
        fun inject(singUpfragment: SignUpFragment)
        fun inject(myFirebaseInstanceIDService: MyFirebaseInstanceIDService)
}