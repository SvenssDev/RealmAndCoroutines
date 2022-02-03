package com.svenss.realmcoroutines.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.svenss.realmcoroutines.model.ProfileRealm
import com.svenss.realmcoroutines.model.ProfileWithoutRealm
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by miguelleon on 25,enero,2022
 */
class ProfileShowViewModel: ViewModel() {

    private lateinit var realm: Realm

    /** live data **/
    val profileData: MutableLiveData<ProfileWithoutRealm> by lazy { MutableLiveData<ProfileWithoutRealm>() }

    init {
        getUserInformation()
    }

    private fun getUserInformation() {
        val profile = getInfo()
        profileData.postValue(profile)
    }

    private fun getInfo(): ProfileWithoutRealm{
        val profile = ProfileWithoutRealm()
        CoroutineScope(Dispatchers.IO).launch {
            realm = Realm.getDefaultInstance()
            realm.apply {
                executeTransactionAwait(Dispatchers.IO){ transaction ->
                    val query = transaction.where(ProfileRealm::class.java).findAll()
                    query.map {
                        profile.name = it.name
                        profile.lastname = it.lastname
                    }
                }
            }
        }
        return profile
    }
}