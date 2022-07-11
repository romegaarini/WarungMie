package org.d3if0031.warungmie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import org.d3if0031.warungmie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment)
                .findNavController()

        setupToolbar()
    }

    private fun setupToolbar() {
        val homeFragments = setOf(
            R.id.signInFragment,
            R.id.homeFragment
        )

        val appBarConfiguration = AppBarConfiguration(homeFragments)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val hideToolbarFragments = setOf(
            R.id.homeFragment,
            R.id.loadingFragment
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.visibility =
                if (hideToolbarFragments.contains(destination.id)) View.GONE else View.VISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}