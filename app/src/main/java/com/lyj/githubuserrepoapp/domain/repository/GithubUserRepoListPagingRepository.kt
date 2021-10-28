package com.lyj.githubuserrepoapp.domain.repository

import androidx.paging.PagingData
import com.lyj.githubuserrepoapp.domain.model.GithubModel
import com.lyj.githubuserrepoapp.domain.model.GithubRepositoryModel
import io.reactivex.rxjava3.core.Flowable

interface GithubUserRepoListPagingRepository{
    fun getGithubUserRepoRepositoryRxSource(ownerName: String): Flowable<PagingData<GithubModel>>
}