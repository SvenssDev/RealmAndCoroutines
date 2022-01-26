package com.svenss.realmcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.svenss.realmcoroutines.databinding.ActivityProfileShowBinding
import com.svenss.realmcoroutines.model.ProfileWithoutRealm
import com.svenss.realmcoroutines.viewmodel.ProfileShowViewModel

class ProfileShow : AppCompatActivity() {

    private lateinit var binding: ActivityProfileShowBinding
    private lateinit var viewModel: ProfileShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ProfileShowViewModel::class.java)

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.profileData.observe(this, onProfileUpdated())
    }

    private fun onProfileUpdated() = Observer<ProfileWithoutRealm>{ profile ->
        profile?.let {
            with(binding){
                tvName.text = profile.name
                tvLastname.text = profile.lastname
            }
        }
    }
}