package com.lyj.githubuserrepoapp.data.repoisotry

import com.lyj.githubuserrepoapp.data.source.api.service.GithubApiService
import com.lyj.githubuserrepoapp.domain.model.GithubModel
import com.lyj.githubuserrepoapp.domain.model.GithubRepositoryModel
import com.lyj.githubuserrepoapp.domain.model.GithubUserModel
import com.lyj.githubuserrepoapp.domain.repository.GithubApiRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubApiRepositoryImpl(
    private val service: GithubApiService
) : GithubApiRepository {
    override fun requestGetUserList(userName: String): Single<GithubUserModel> = service
        .requestSearchUser(userName)
        .subscribeOn(Schedulers.io())
        .map {
            it
        }

    override fun requestGetRepositoryList(
        userName: String,
    ): Single<List<GithubRepositoryModel>> = service.requestRepositoryListByUserName(userName)
        .subscribeOn(Schedulers.io())
        .map {
            it
        }
}