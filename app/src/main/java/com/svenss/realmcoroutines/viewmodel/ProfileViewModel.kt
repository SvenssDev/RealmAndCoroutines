package com.svenss.realmcoroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.svenss.realmcoroutines.model.ProfileRealm
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by miguelleon on 25,enero,2022
 */
class ProfileViewModel: ViewModel() {

    private lateinit var realm: Realm

    /** live data **/
    val stateSaved: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun saveData(name: String, lastName: String){
        if (name.isNotEmpty() && lastName.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                realm = Realm.getDefaultInstance()
                realm.apply {
                    executeTransactionAwait { transaction ->
                        val model = ProfileRealm(name, lastName)
                        transaction.insertOrUpdate(model)
                        setStateSaved(true)
                    }
                }
            }
        }else{
            setStateSaved(false)
        }
    }

    private fun setStateSaved(state: Boolean){
        stateSaved.postValue(state)
    }
}