package mobg5.g51999.foodrating


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import mobg5.g51999.foodrating.databinding.ActivityMainBinding
import mobg5.g51999.foodrating.databinding.MenuHeaderBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    //A binding for the navigation drawer header.
    private lateinit var _navDrawerHeaderBinding: MenuHeaderBinding
    val navDrawerHeaderBinding
        get() = _navDrawerHeaderBinding

    private lateinit var navController: NavController

    val rootDrawerLayout : DrawerLayout
        get() = binding.rootDrawerLayout

    val navView : NavigationView
        get() = binding.navView

    /**
     * Prepares the creation of the main activity.
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = this.findNavController(R.id.nav_host_fragment)
        _navDrawerHeaderBinding = MenuHeaderBinding.bind(binding.navView.getHeaderView(0))

        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.rootDrawerLayout)
    }

    /**
     * Allows to display the navigation drawer.
     */
    override fun onSupportNavigateUp(): Boolean
    {
        return NavigationUI.navigateUp(navController, binding.rootDrawerLayout)
    }

}