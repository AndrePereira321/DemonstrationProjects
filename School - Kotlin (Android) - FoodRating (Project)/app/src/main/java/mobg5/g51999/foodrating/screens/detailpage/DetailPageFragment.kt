package mobg5.g51999.foodrating.screens.detailpage

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.firebase.storage.ktx.component1
import com.google.firebase.storage.ktx.component2
import mobg5.g51999.foodrating.MainActivity
import mobg5.g51999.foodrating.R
import mobg5.g51999.foodrating.business.UserFeedback
import mobg5.g51999.foodrating.business.api.RecordsProperty
import mobg5.g51999.foodrating.databinding.FragmentDetailPageBinding
import mobg5.g51999.foodrating.screens.detailpage.imageslider.ImageSliderAdapter
import mobg5.g51999.foodrating.screens.detailpage.recyclerview.FeedbackAdapter
import mobg5.g51999.foodrating.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.sql.Timestamp


/**
 * Displays the details about a place (info, feedbacks).
 * Allows the user to place a feedback.
 */
class DetailPageFragment : Fragment()
{

    companion object
    {
        private const val CAMERA_INTENT_REQUEST: Int = 12
        private const val GALLERY_INTENT_REQUEST: Int = 21
    }

    private val args: DetailPageFragmentArgs by navArgs()
    private val viewModel: DetailPageViewModel by viewModel {
        parametersOf(args.connectedUser, args.record)
    }

    private lateinit var binding: FragmentDetailPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View
    {
        val activity = requireActivity() as MainActivity

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_page, container, false)

        //Creates the adapter for the list of the feedbacks.
        binding.detailFeedbackRecyclerView.adapter = FeedbackAdapter(requireContext())

        viewModel.feedbacks.observe(viewLifecycleOwner, feedbackObserver)
        viewModel.record.observe(viewLifecycleOwner, recordObserver)
        binding.detailBtSubmitFeedback.setOnClickListener { addFeedbackListener() }
        binding.detailBtVisit.setOnClickListener { btVisitListener() }
        binding.detailBtUploadImg.setOnClickListener { startImagePickerIntentListener() }

        this.hideEmptyElements()
        this.setImageSliderContent()

        //Dyn sets the title of the top action bar.
        activity.supportActionBar?.title = viewModel.record.value?.fieldsProperty?.nom


        return binding.root
    }

    /**
     * Sets the data in the view binding.
     */
    private val recordObserver = Observer<RecordsProperty> { binding.record = it }

    /**
     * Refresh the recycler view items. (usually only called once).
     */
    private val feedbackObserver = Observer<MutableList<UserFeedback>> { feedbacks ->
        val adapter = binding.detailFeedbackRecyclerView.adapter as FeedbackAdapter
        adapter.data = feedbacks
    }

    /**
     * Open the Google Maps App with the location of the record.
     * BUG - The marker dont have the recordName.
     */
    @SuppressLint("QueryPermissionsNeeded")
    private fun btVisitListener()
    {
        val coordinates = viewModel.record.value!!.coordinates
        if (coordinates != null)
        {
            val recordName = viewModel.record.value!!.fieldsProperty.nom
            val uriString = "geo:0,0?q=<${coordinates[1]}>,<${coordinates[0]}>($recordName)"
            val gmmIntentUri = Uri.parse(uriString)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(requireActivity().packageManager)?.let {
                startActivity(mapIntent)
            }
        } else
        {
            val errorMsg = "Cet endroit ne fournis pas de cordonnées. (il est peut être mobile)"
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Verifies the user input and inserts a new feedback. Also refresh to display the feedback.
     */
    private fun addFeedbackListener()
    {
        val rating = binding.detailSmileyRating.selectedSmiley.rating
        if (rating == -1 || binding.detailFeedbackInput.text.isEmpty()) //No valid input.
        {
            showToast(requireContext(), "Vous avez pas donnée d'évaluation!")
            return
        }
        val feedback = binding.detailFeedbackInput.text.toString()
        val userEmail = viewModel.connectedUser.value!!.email
        val recordId = viewModel.record.value!!.recordid
        val currentTime = Timestamp(System.currentTimeMillis())
        val userFeedback = UserFeedback(userEmail, recordId, rating, feedback, currentTime)

        viewModel.addFeedback(userFeedback)

        this.refreshRecyclerViewAfterAddFeedback()
    }

    /**
     * Refresh the display of the recyclerview when a feedback has been added.
     */
    private fun refreshRecyclerViewAfterAddFeedback()
    {
        //Direct refresh the recycler view and clears the input.
        val itemPos = binding.detailFeedbackRecyclerView.adapter!!.itemCount - 1
        binding.detailFeedbackRecyclerView.adapter!!.notifyItemInserted(itemPos)
        binding.detailFeedbackRecyclerView.smoothScrollToPosition(itemPos)
        binding.detailFeedbackInput.text.clear()

        //Removes the textView of no feedbacks.
        if (binding.detailFeedbackRecyclerView.visibility == View.GONE)
        {
            binding.detailFeedbackRecyclerView.visibility = View.VISIBLE
            binding.detailNoFeedbackLabel.visibility = View.GONE
        }
    }

    /**
     * Remove the TextViews of fields that came empty from API.
     */
    private fun hideEmptyElements()
    {
        val record = viewModel.record.value!!.fieldsProperty
        if (record.adresse == null)
        {
            binding.detailRecordAdress.visibility = View.GONE
        }
        if (record.siteFr == null)
        {
            binding.detailRecordSiteFr.visibility = View.GONE
        }
        if (record.siteNl == null)
        {
            binding.detailRecordSiteNl.visibility = View.GONE
        }
        if (viewModel.feedbacks.value!!.isEmpty())
        {
            binding.detailFeedbackRecyclerView.visibility = View.GONE
        } else
        {
            binding.detailNoFeedbackLabel.visibility = View.GONE
        }
    }

    /**
     * Displays the Image Slider with the already uploaded images.
     */
    private fun setImageSliderContent()
    {
        viewModel.getRecordImagesReferences(viewModel.record.value!!.recordid)
            .addOnSuccessListener { (items, _) ->
                if (items.isNotEmpty())
                {
                    val slideAdapter = ImageSliderAdapter(requireContext())
                    items.forEach { item ->
                        item.downloadUrl.addOnSuccessListener {
                            slideAdapter.addImageUrl(it.toString())
                        }
                    }
                    binding.detailImageSliderViewPager.adapter = slideAdapter
                    binding.detailImageSlideContainer.visibility = View.VISIBLE
                }
            }
    }

    /**
     * Displays an AlertDialog to the user choose the source of the image to upload (Gallery/Camera).
     */
    private fun startImagePickerIntentListener()
    {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val options = arrayOf("Nouvelle Photo", "Gallerie", "Anuller")
        builder.setTitle("Choisissez une image!")
        builder.setItems(options) { dialog, index ->
            when (options[index])
            {
                options[0] -> startCameraIntent()
                options[1] -> startGalleryIntent()
                options[2] -> dialog.dismiss()
            }
        }
        builder.show()
    }

    private fun startCameraIntent()
    {
        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePicture, CAMERA_INTENT_REQUEST)
    }

    private fun startGalleryIntent()
    {
        val pickPhoto = Intent(Intent.ACTION_PICK)
        pickPhoto.type = "image/*"
        startActivityForResult(pickPhoto, GALLERY_INTENT_REQUEST)
    }

    /**
     * Receives the image from the launched intents.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_CANCELED)
        {
            val userImg = retrieveBitMapImage(requestCode, resultCode, data)
            if (userImg != null)
            {
                binding.detailProgressBar.visibility = View.VISIBLE
                binding.detailProgressBar.progress = 0
                viewModel.addUserImage(userImg)
                    .addOnSuccessListener {
                        showToast(requireContext(), "Image envoyée avec succées!")
                        binding.detailProgressBar.visibility = View.GONE

                    }
                    .addOnFailureListener {
                        showToast(
                            requireContext(),
                            "Un problème est survenu en envoyant votre image vers le serveur!"
                        )
                        binding.detailProgressBar.visibility = View.GONE
                    }
                    .addOnProgressListener {
                        val progress = (100.0 * it.bytesTransferred / it.totalByteCount).toInt()
                        binding.detailProgressBar.progress = progress
                    }
            }
        }
    }

    /**
     * Retrieve a BitMap image from the launched intents.
     */
    private fun retrieveBitMapImage(requestCode: Int, resultCode: Int, data: Intent?): Bitmap?
    {
        when (requestCode)
        {
            CAMERA_INTENT_REQUEST -> if (resultCode == Activity.RESULT_OK && data != null)
            {
                return data.extras?.get("data") as Bitmap
            }
            GALLERY_INTENT_REQUEST -> if (resultCode == Activity.RESULT_OK && data != null)
            {
                val selectedImage: Uri? = data.data
                if (selectedImage != null)
                {
                    val contentResolver = requireContext().contentResolver
                    val imgStream = contentResolver.openInputStream(selectedImage)
                    return BitmapFactory.decodeStream(imgStream, null, null)
                }
            }
        }
        return null
    }



}