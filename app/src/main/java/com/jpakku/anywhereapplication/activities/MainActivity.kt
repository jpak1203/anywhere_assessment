package com.jpakku.anywhereapplication.activities

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.jpakku.anywhereapplication.R
import com.jpakku.anywhereapplication.databinding.ActivityMainBinding
import com.jpakku.anywhereapplication.ui.detail.DetailFragment
import com.jpakku.anywhereapplication.ui.detail.DetailFragment.Companion.DESC
import com.jpakku.anywhereapplication.ui.detail.DetailFragment.Companion.ICON
import com.jpakku.anywhereapplication.ui.detail.DetailFragment.Companion.NAME
import com.jpakku.anywhereapplication.ui.list.ListFragment
import com.jpakku.anywhereapplication.util.AppConfigUtil
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), ListFragment.OnItemSelectedListener {

    @Inject
    lateinit var appConfigUtil: AppConfigUtil

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(appConfigUtil.getClientTheme())
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        if (isTablet(this)) {
            val detailFragment = supportFragmentManager.findFragmentById(R.id.detail_fragment_container)
            detailFragment?.let {
                supportFragmentManager.beginTransaction().hide(it).commit()
            }
        } else {
            if (savedInstanceState == null) {
                navController = findNavController(R.id.fragment_navigation_controller)
            }
            setNavigation()
        }
    }

    private fun setNavigation() {
        val navHostFragment = (supportFragmentManager.findFragmentById(R.id.fragment_navigation_controller) as NavHostFragment)
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.main_navgraph)
        graph.setStartDestination(R.id.list_fragment)
        navHostFragment.navController.graph = graph
    }

    private fun isTablet(context: Context): Boolean {
        return ((context.resources.configuration.screenLayout and
                        Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE)
    }

    override fun onCharacterSelected(name: String, description: String, icon: String) {
        if (isTablet(this)) {
            val detailFragment = supportFragmentManager.findFragmentById(R.id.detail_fragment_container)
            detailFragment?.let {
                supportFragmentManager.beginTransaction().show(it).commit()
            }
            (detailFragment as DetailFragment).updateView(name, description, icon)
        } else {
            findNavController(R.id.fragment_navigation_controller).navigate(R.id.detail_fragment, bundleOf(NAME to name, DESC to description, ICON to icon))
        }
    }
}