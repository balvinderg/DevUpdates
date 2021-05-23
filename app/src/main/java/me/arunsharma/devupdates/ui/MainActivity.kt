package me.arunsharma.devupdates.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dev.core.extensions.addFragment
import me.arunsharma.devupdates.BackStackManager
import me.arunsharma.devupdates.R
import me.arunsharma.devupdates.app.DevUpdatesApp
import me.arunsharma.devupdates.databinding.ActivityMainBinding
import me.arunsharma.devupdates.di.activity.MainComponent
import me.arunsharma.devupdates.navigator.MainNavigator
import me.arunsharma.devupdates.ui.fragments.HomeFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityListener {

    @Inject
    lateinit var mainNavigator: MainNavigator

    val mainComponent: MainComponent by lazy {
        (applicationContext as DevUpdatesApp)
            .appComponent.mainComponent().create()
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mainComponent.inject(this)

        if (savedInstanceState == null) {
            addFragment(HomeFragment.newInstance(), R.id.fragment_container, false)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount < 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun getNavigator(): MainNavigator {
        return mainNavigator
    }
}
