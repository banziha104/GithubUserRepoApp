package com.lyj.githubuserrepoapp.presentation.activity


import androidx.lifecycle.ViewModel
import com.lyj.githubuserrepoapp.domain.usecase.RequestGetGithubUserRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val useCase: RequestGetGithubUserRepoUseCase
) : ViewModel()
