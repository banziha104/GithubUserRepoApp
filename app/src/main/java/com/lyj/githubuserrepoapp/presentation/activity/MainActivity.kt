package com.lyj.githubuserrepoapp.presentation.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lyj.githubuserrepoapp.databinding.ActivityMainBinding
import com.lyj.githubuserrepoapp.presentation.adapter.GithubAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//adb -s emulator-5554 shell am start -W  -a android.intent.action.VIEW -d testapp://repos/jakewharton com.lyj.githubuserrepoapp
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    companion object{
        const val DEFAULT_URI = "testapp://repos/jakewharton"
        const val REPOS_HOST = "repos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val data = intent.data
        val path = data?.path
        val host = data?.host

        if (path != null && REPOS_HOST == host) {
            viewModel
                .useCase
                .execute(path.drop(1))
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
        }else{
            Toast.makeText(this,"잘못된 링크입니다",Toast.LENGTH_LONG).show()
        }
    }
}