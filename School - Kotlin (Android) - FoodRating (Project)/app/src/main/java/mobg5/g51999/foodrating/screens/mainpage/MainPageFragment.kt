package mobg5.g51999.foodrating.screens.mainpage

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.PermissionChecker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import mobg5.g51999.foodrating.R
import mobg5.g51999.foodrating.business.User
import mobg5.g51999.foodrating.databinding.FragmentMainPageBinding
import java.sql.Timestamp

/**
 * The main page allows the user connects to the app.
 */
class MainPageFragment : Fragment()
{

    companion object
    {
        private const val REQUEST_CODE_SIGN_IN: Int = 1
        private const val DOMAIN_ERROR: Int = 12500

        //Auth Providers
        private val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        //Permissions used by the app.
        private val neededPermissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET
        )
    }

    private lateinit var binding: FragmentMainPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View
    {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_page, container, false)

        this.setupPermissions()

        binding.btMainConnect.setOnClickListener { launchSignInActivity() }

        return binding.root
    }

    /**
     * Launches the Firebase AuthUI with the given auth providers.
     */
    private fun launchSignInActivity()
    {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.logo_app)
                .setTheme(R.style.AppTheme)
                .build(),
            REQUEST_CODE_SIGN_IN
        )
    }

    /**
     * Retrieves the info about the connected user and navigates to lobby if there is no error.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_IN)
        {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK)
            {
                try
                {
                    val user = provideConnectedUser()
                    val action =
                        MainPageFragmentDirections.actionMainPageFragmentToLobbyFragment(user)
                    findNavController().navigate(action)
                } catch (e: Exception)
                {
                    handleError(response)
                }
            } else
            {
                handleError(response)
            }
        }
    }

    /**
     * Parses the firebase user structure to the app structure.
     */
    private fun provideConnectedUser(): User
    {
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        if (user != null)
        {
            return User(
                user.email!!,
                user.displayName!!,
                Timestamp(user.metadata!!.creationTimestamp),
                Timestamp(user.metadata!!.lastSignInTimestamp)
            )
        } else
        {
            throw IllegalStateException("No connected user!")
        }
    }

    private fun handleError(response: IdpResponse?)
    {
        Log.e("MainPageSignIn", "Response:\n $response")
        if (response != null && response.error != null)
        {
            Log.e("MainPageSignIn", "Error: ${response.error}")
            Log.e("MainPageSignIn", "ErrorMessage: ${response.error!!.message}")
            if (response.error!!.message == DOMAIN_ERROR.toString())
            {
                displayError("Error: You must sign-in with an Google HE2B Account!")
                return
            }
        }
        displayError("Error: You didn't connected in!")
    }

    private fun displayError(text: String)
    {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    /**
     * Ask the user to accept the app permissions.
     */
    private fun setupPermissions()
    {
        val revokedPermissions = mutableListOf<String>()
        neededPermissions.forEach {
            val permission = PermissionChecker.checkSelfPermission(requireContext(), it)
            if (permission != PackageManager.PERMISSION_GRANTED)
            {
                revokedPermissions.add(it)
            }
        }
        if (revokedPermissions.isNotEmpty())
        {
            requestPermissions(revokedPermissions.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    )
    {
        var allGranted = true
        grantResults.forEach {
            allGranted = allGranted && it == PackageManager.PERMISSION_GRANTED
        }
        if (!allGranted)
        {
            Toast.makeText(
                requireContext(),
                "Vous n'avez pas acceptée toutes les permissions!\n"
                        + "Certaines fonctionalitées peuvent ne pas fonctionner!",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}