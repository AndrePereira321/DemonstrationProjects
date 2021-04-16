package mobg5.g51999.foodrating.screens.lobby

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.mikepenz.itemanimators.ScaleXAnimator
import mobg5.g51999.foodrating.MainActivity
import mobg5.g51999.foodrating.R
import mobg5.g51999.foodrating.business.User
import mobg5.g51999.foodrating.business.api.FoodServiceAPIResponse
import mobg5.g51999.foodrating.business.api.RecordsProperty
import mobg5.g51999.foodrating.databinding.FragmentLobbyBinding
import mobg5.g51999.foodrating.screens.lobby.recyclerview.RecordAdapter
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * This is the main screen for a connected user.
 * It displays the user profile, and the food places proposed by the app.
 */
class LobbyFragment : Fragment(), AdapterView.OnItemSelectedListener
{

    companion object
    {
        private val categories: Array<String> = arrayOf(
            "All", "Potagers", "Horeca", "Epiceries", "Marchés", "Agriculture Urbaine"
        )
    }

    private val args: LobbyFragmentArgs by navArgs()
    private val viewModel: LobbyViewModel by viewModel { parametersOf(args.connectedUser) }

    private lateinit var binding: FragmentLobbyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lobby, container, false)

        viewModel.connectedUser.value = args.connectedUser

        //Arranges all to display the API data.
        this.setUpAPIData()

        //Sets the lobby as a top-level destination.
        this.refreshNavigation()

        //Sets the observers events for the view model.
        viewModel.connectedUser.observe(viewLifecycleOwner, userObserver)
        viewModel.foodData.observe(viewLifecycleOwner, apiDataObserver)

        return binding.root
    }

    /**
     * Spinner item selected.
     * Defines the ViewModel API data according the filter selected.
     */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
    {
        if (parent != null)
        {
            viewModel.retrieveApiInfo(parent.getItemAtPosition(position) as String)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?)
    {
    }

    /**
     * Observer for the ViewModel connected user.
     */
    private val userObserver = Observer<User> { user ->
        val activity = requireActivity() as MainActivity
        activity.navDrawerHeaderBinding.navDrawerEmail.text = user.email
        activity.navDrawerHeaderBinding.navDrawerName.text = user.displayName
        activity.navDrawerHeaderBinding.navDrawerEmail.visibility = View.VISIBLE
        activity.navDrawerHeaderBinding.navDrawerName.visibility = View.VISIBLE
    }

    /**
     * Observer for the ViewModel API Data.
     **/
    private val apiDataObserver = Observer<FoodServiceAPIResponse> { response ->
        val adapter = binding.lobbyRecycler.adapter as RecordAdapter
        adapter.submitList(response.records)
    }

    /**
     * Hook function for assign onClick to each element in the recycler view.
     */
    private val recordListenerHook: (record: RecordsProperty) -> Unit = {
        val navAction = LobbyFragmentDirections
            .actionLobbyFragmentToDetailPageFragment(viewModel.connectedUser.value!!, it)
        findNavController().navigate(navAction)
    }

    /**
     * Sets this screen as the primary destination, and allows to display
     * the navigation drawer menu.
     */
    private fun refreshNavigation()
    {
        val activity = requireActivity() as MainActivity
        val navController = findNavController()
        navController.graph.startDestination = R.id.lobbyFragment
        activity.navView.menu.getItem(0).isVisible = true
        NavigationUI.setupActionBarWithNavController(
            activity, navController, activity.rootDrawerLayout
        )
    }


    /**
     * Makes a request to the REST API and displays the data.
     */
    private fun setUpAPIData()
    {
        val context = requireContext()
        val sDropDown = R.layout.support_simple_spinner_dropdown_item
        val userEmail = viewModel.connectedUser.value!!.email

        //Sets the Adapter for the Recycler View of the records.
        binding.lobbyRecycler.adapter = RecordAdapter(recordListenerHook, get(), userEmail)
        binding.lobbyRecycler.itemAnimator = ScaleXAnimator()

        //Displays a drop down spinner to filter the recyler view.
        binding.lobbyFilterSpinner.adapter = ArrayAdapter(context, sDropDown, categories)
        binding.lobbyFilterSpinner.onItemSelectedListener = this
    }

}
