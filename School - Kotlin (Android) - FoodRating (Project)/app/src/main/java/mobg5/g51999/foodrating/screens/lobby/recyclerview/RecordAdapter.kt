package mobg5.g51999.foodrating.screens.lobby.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mobg5.g51999.foodrating.R
import mobg5.g51999.foodrating.business.api.RecordsProperty
import mobg5.g51999.foodrating.databinding.RvItemRecordBinding
import mobg5.g51999.foodrating.repository.Repository

/**
 * Adapter to the Recycler View List displaying the data.
 */
class RecordAdapter(

    /**
     * Hook function called when a element is clicked.
     */
    private val recordJob: (record: RecordsProperty) -> Unit,
    private val repository: Repository,
    private val userEmail: String

) : ListAdapter<RecordsProperty, RecordAdapter.ViewHolder>(RecordPropertyDiffCallback())
{

    /**
     * Class that allows connection with the view layout.
     */
    class ViewHolder(val binding: RvItemRecordBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * Class that overrides hooks methods used by the diff algorithm.
     */
    class RecordPropertyDiffCallback : DiffUtil.ItemCallback<RecordsProperty>()
    {
        override fun areItemsTheSame(oldItem: RecordsProperty, newItem: RecordsProperty) =
            oldItem.recordid == newItem.recordid

        override fun areContentsTheSame(oldItem: RecordsProperty, newItem: RecordsProperty) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder
    {
        // Create a new view, which defines the UI of the list item
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = RvItemRecordBinding.inflate(layoutInflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int)
    {
        val record = getItem(position)
        viewHolder.binding.rvConstLayMain.setOnClickListener(RecordListener(recordJob, record))
        viewHolder.binding.record = record
        this.displayLastRatingSmiley(record, viewHolder.binding.rvImageStatus)
    }

    /**
     * Displays a Smiley according the last rating of the user for the given record.
     */
    private fun displayLastRatingSmiley(record: RecordsProperty, imageView: ImageView)
    {
        val last = repository.getLastUserFeedbackFromRecord(userEmail, record.recordid)
        if (last != null)
        {
            when (last.rating)
            {
                1 -> this.drawVector(imageView, R.drawable.ic_terrible)
                2 -> this.drawVector(imageView, R.drawable.ic_bad)
                3 -> this.drawVector(imageView, R.drawable.ic_neutral)
                4 -> this.drawVector(imageView, R.drawable.ic_good)
                5 -> this.drawVector(imageView, R.drawable.ic_perfect)
            }
        }
    }

    private fun drawVector(imageView: ImageView, rId: Int)
    {
        imageView.setImageDrawable(
            ResourcesCompat.getDrawable(imageView.context.resources, rId, null)
        )
    }

}

