package mobg5.g51999.foodrating.screens.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mobg5.g51999.foodrating.R

/**
 * Displays a brief description about the App.
 */
class AboutFragment : Fragment()
{

    /**
     * Creates the layout views for the About Fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View?
    {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }
}