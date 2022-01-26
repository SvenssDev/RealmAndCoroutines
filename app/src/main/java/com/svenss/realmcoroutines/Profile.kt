package com.svenss.realmcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.svenss.realmcoroutines.databinding.ActivityProfileBinding
import com.svenss.realmcoroutines.viewmodel.ProfileViewModel

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        initViews()
    }


    private fun initViews(){
        viewModel.stateSaved.observe(this, onSaveUpdated())
        binding.btnSave.setOnClickListener {
            saveData()
        }
    }

    private fun saveData(){
        with(binding){
            val name = etName.text.toString()
            val lastname = etLastname.text.toString()

            viewModel.saveData(name, lastname)
        }
    }

    private fun onSaveUpdated() = Observer<Boolean>{ state ->
        state?.let {
            if (state){
                Toast.makeText(this, "Profile saved", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

}