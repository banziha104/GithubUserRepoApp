package com.lyj.githubuserrepoapp.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lyj.githubuserrepoapp.databinding.ActivityMainBinding
import com.lyj.githubuserrepoapp.presentation.adapter.GithubAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val userName = intent.getStringExtra("userName") ?: "jakewharton"
        viewModel
            .useCase
            .execute(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                binding
                    .mainReclerview
                    .apply {
                        adapter = GithubAdapter(it)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                    }
            }, {
                it.printStackTrace()
            })
    }
}