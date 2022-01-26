package com.svenss.realmcoroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.svenss.realmcoroutines.databinding.ActivityMainBinding
import io.realm.Realm

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRealm()
        initViews()
    }

    private fun initRealm(){
        Realm.init(this)
    }

    private fun initViews(){
        with(binding){
            btnCreate.setOnClickListener(this@MainActivity)
            btnShow.setOnClickListener(this@MainActivity)
        }
    }

    private fun initNewActivity(code: Int){
        val activity = if (code == 0) Profile::class.java else ProfileShow::class.java
        Intent(this, activity).apply {
            startActivity(this)
        }
    }

    override fun onClick(p0: View?) {
        with(binding){
            when(p0?.id){
                btnCreate.id -> initNewActivity(0)
                btnShow.id -> initNewActivity(1)
            }
        }
    }
}