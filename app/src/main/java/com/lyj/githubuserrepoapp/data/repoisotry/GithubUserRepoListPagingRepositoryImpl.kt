package com.lyj.githubuserrepoapp.data.repoisotry

import androidx.paging.PagingData
import com.lyj.githubuserrepoapp.domain.model.GithubModel
import io.reactivex.rxjava3.core.Flowable


class GithubUserRepoListPagingRepositoryImpl(
    private val repository : GithubApiRepositoryImpl
){
    fun getGithubUserRepoRepositoryRxSource(ownerName: String): Flowable<PagingData<GithubModel>> = TODO()
}