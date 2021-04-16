package mobg5.g51999.foodrating.screens.detailpage

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.UploadTask
import mobg5.g51999.foodrating.business.User
import mobg5.g51999.foodrating.business.UserFeedback
import mobg5.g51999.foodrating.business.api.RecordsProperty
import mobg5.g51999.foodrating.repository.Repository

/**
 * Contains the data about the record.
 */
class DetailPageViewModel(
    _connectedUser: User, _record: RecordsProperty, private val repository: Repository
) : ViewModel()
{

    val connectedUser: LiveData<User> = MutableLiveData(_connectedUser)

    val record: LiveData<RecordsProperty> = MutableLiveData(_record)

    /**
     * List of feedbacks of all users.
     */
    private val _feedbacks: LiveData<MutableList<UserFeedback>>
    val feedbacks: LiveData<MutableList<UserFeedback>>
        get() = _feedbacks

    init
    {
        _feedbacks = MutableLiveData(repository.getRecordFeedBacks(record.value!!))
    }

    fun addFeedback(userFeedback: UserFeedback)
    {
        repository.insertFeedback(userFeedback)
        _feedbacks.value!!.add(userFeedback)
    }

    fun addUserImage(imgBitMap: Bitmap): UploadTask
    {
        return repository.insertUserImage(
            imgBitMap, record.value!!.recordid, connectedUser.value!!.email
        )
    }

    /**
     * Retrieve the firebase references of a list of a images urls from the given record.
     */
    fun getRecordImagesReferences(recordId: String): Task<ListResult>
    {
        return repository.getRecordImagesReferences(recordId)
    }


}