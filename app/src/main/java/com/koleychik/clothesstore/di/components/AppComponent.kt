package com.koleychik.clothesstore.di.components

import com.koleychik.clothesstore.ui.screens.activities.MainActivity
import com.koleychik.clothesstore.di.modules.*
import com.koleychik.clothesstore.ui.screens.NavDrawerFragment
import com.koleychik.clothesstore.ui.screens.ProductFragment
import com.koleychik.clothesstore.ui.screens.SearchFragment
import com.koleychik.clothesstore.ui.screens.SearchResultFragment
import com.koleychik.clothesstore.ui.screens.login.SignInFragment
import com.koleychik.clothesstore.ui.screens.login.forgotPassword.EmailFragment
import com.koleychik.clothesstore.ui.screens.login.signUp.EmailAndNameFragment
import com.koleychik.clothesstore.ui.screens.login.signUp.SignUpFragment
import com.koleychik.clothesstore.ui.screens.navDrawer.BasketFragment
import com.koleychik.clothesstore.ui.screens.navDrawer.HomeFragment
import com.koleychik.clothesstore.ui.screens.settings.AccountFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        NetworkRepositoryModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)

    //    fun inject(productAdapter: ProductAdapter)
    fun inject(productFragment: ProductFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(searchResultFragment: SearchResultFragment)
    fun inject(navDrawerFragment: NavDrawerFragment)
    fun inject(basketFragment: BasketFragment)
    fun inject(signInFragment: SignInFragment)
    fun inject(nameAndNameFragment: EmailAndNameFragment)
    fun inject(signUpFragment: SignUpFragment)
    fun inject(emailFragment: EmailFragment)
    fun inject(accountFragment: AccountFragment)
}