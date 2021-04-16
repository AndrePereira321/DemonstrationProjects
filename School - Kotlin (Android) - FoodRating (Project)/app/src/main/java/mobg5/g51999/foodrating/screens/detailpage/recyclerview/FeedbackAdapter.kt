package mobg5.g51999.foodrating.screens.detailpage.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import mobg5.g51999.foodrating.R
import mobg5.g51999.foodrating.business.UserFeedback
import mobg5.g51999.foodrating.databinding.RvItemFeedbackBinding

/**
 * Basic adapter for the recycler view displaying the user feedbacks.
 */
class FeedbackAdapter(private val context: Context) :
    RecyclerView.Adapter<FeedbackAdapter.ViewHolder>()
{
    /**
     * The list of the feedbacks to be displayed.
     */
    //Will reference the LiveData value of viewmodel by the fragment.
    var data: MutableList<UserFeedback> = mutableListOf()
        set(_data)
        {
            field = _data
            notifyDataSetChanged()
        }

    /**
     * Binding to the view layout.
     */
    class ViewHolder(val binding: RvItemFeedbackBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder
    {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = RvItemFeedbackBinding.inflate(layoutInflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int)
    {
        val feedback = data[position]
        viewHolder.binding.feedback = feedback

        this.alternateColors(viewHolder, position)
        this.displayRatingSmiley(feedback, viewHolder.binding.feedbackImgStatus)
    }

    override fun getItemCount(): Int = data.size


    /**
     * Alternate the background color of each element of the RecyclerView.
     */
    private fun alternateColors(viewHolder: ViewHolder, position: Int)
    {
        if (position % 2 == 1)
        {
            viewHolder.binding.root.setBackgroundColor(
                ContextCompat.getColor(context, R.color.softGray)
            )
        } else
        {
            viewHolder.binding.root.setBackgroundColor(
                ContextCompat.getColor(context, R.color.softYellow)
            )
        }
    }

    /**
     * Displays a Smiley according the rating of the user.
     */
    private fun displayRatingSmiley(feedback: UserFeedback, imageView: ImageView)
    {
        when (feedback.rating)
        {
            1 -> this.drawVector(imageView, R.drawable.ic_terrible)
            2 -> this.drawVector(imageView, R.drawable.ic_bad)
            3 -> this.drawVector(imageView, R.drawable.ic_neutral)
            4 -> this.drawVector(imageView, R.drawable.ic_good)
            5 -> this.drawVector(imageView, R.drawable.ic_perfect)
        }
    }

    /**
     * Draws the given vector id into the image view.
     */
    private fun drawVector(imageView: ImageView, rId: Int)
    {
        imageView.setImageDrawable(
            ResourcesCompat.getDrawable(imageView.context.resources, rId, null)
        )
    }

}
