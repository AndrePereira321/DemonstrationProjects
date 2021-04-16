package mobg5.g51999.foodrating.screens.lobby.recyclerview

import android.view.View
import mobg5.g51999.foodrating.business.api.RecordsProperty

/**
 * Listener for the elements of the recycler view.
 */
class RecordListener(
    private val recordListener: (record: RecordsProperty) -> Unit,
    private val recordProperty: RecordsProperty
) : View.OnClickListener
{


    override fun onClick(v: View?)
    {
        recordListener(recordProperty)
    }
}