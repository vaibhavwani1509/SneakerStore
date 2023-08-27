package com.otta.sneakership.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.otta.sneakership.R
import com.otta.sneakership.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    /**DataBinding*/
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        /**Nav Controller*/
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.tabCart.setOnClickListener {
            /**Show the Cart*/
            navController.navigate(R.id.action_cart)
        }

        binding.tabHome.setOnClickListener {
            /**Go back to home*/
            val navOptions: NavOptions = NavOptions.Builder()
                .setPopUpTo(navController.graph.startDestinationId, true)
                .build()

            navController.navigate(navController.graph.startDestinationId, null, navOptions)
        }
    }
}