package mobg5.g51999.foodrating.screens.lobby

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mobg5.g51999.foodrating.business.User
import mobg5.g51999.foodrating.business.api.FoodServiceAPIResponse
import mobg5.g51999.foodrating.repository.Repository

/**
 * Manages the data used by the Lobby Fragment.
 */
class LobbyViewModel(_connectedUserIn: User, private val repository: Repository) : ViewModel()
{

    /**
     * Connected user.
     */
    val connectedUser: MutableLiveData<User> = MutableLiveData(_connectedUserIn)

    /**
     * API Data response.
     */
    private val _foodData: MutableLiveData<FoodServiceAPIResponse> = MutableLiveData()
    val foodData: LiveData<FoodServiceAPIResponse>
        get() = _foodData


    init {
        retrieveApiInfo(null)
    }

    /**
     * Retrieves the API data.
     * If not arguments is given, retrieves all data, if not the arguments filters the data by category.
     */
    fun retrieveApiInfo(category: String?)
    {
        viewModelScope.launch {
            try
            {
                _foodData.value = if (category != null && category != "All")
                {
                    repository.getRecordsByCategory(category)
                } else
                {
                    repository.getAllRecords()
                }
            } catch (e: Exception)
            {
                Log.i("APIServec", "Error - ${e.message}")
            }
        }
    }


}