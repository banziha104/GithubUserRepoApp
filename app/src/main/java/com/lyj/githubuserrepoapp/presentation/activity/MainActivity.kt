package com.lyj.githubuserrepoapp.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lyj.githubuserrepoapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding  by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel : MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val userName = intent.getStringExtra("userName") ?: "jakewharton"
        viewModel.useCase.execute(userName).subscribe({
            Log.d("lyj",  it.joinToString(","))
        },{
            it.printStackTrace()
        })
    }
}