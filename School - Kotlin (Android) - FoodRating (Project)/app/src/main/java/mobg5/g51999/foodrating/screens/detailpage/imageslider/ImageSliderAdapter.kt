package mobg5.g51999.foodrating.screens.detailpage.imageslider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import mobg5.g51999.foodrating.R


/**
 * Adapter for the DetailPage PagerView displaying an Image Slider.
 */
class ImageSliderAdapter(private val context: Context) : PagerAdapter()
{

    /**
     * URLs of the images.
     */
    private val imagesURLs: MutableList<String> = mutableListOf()

    override fun getCount(): Int = imagesURLs.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view === `object` as LinearLayout


    /**
     * Displays the current image in the slider.
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any
    {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View = inflater.inflate(R.layout.item_image_slider, container, false)
        val imageView = itemView.findViewById<View>(R.id.image_slider_image_view) as ImageView

        //Downloads current image and displays it
        Glide.with(context).load(imagesURLs[position]).centerCrop().into(imageView)

        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any)
    {
        container.removeView(`object` as LinearLayout)
    }

    /**
     * Adds an URL of an image to the slider.
     */
    fun addImageUrl(imgUrl: String)
    {
        this.imagesURLs.add(imgUrl)
        this.notifyDataSetChanged()

    }
}
