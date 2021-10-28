package com.lyj.githubuserrepoapp.domain.usecase

import com.lyj.githubuserrepoapp.domain.model.GithubModel
import com.lyj.githubuserrepoapp.domain.repository.GithubApiRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestGetGithubUserRepoUseCase @Inject constructor(
    private val repository: GithubApiRepository
) {
    fun execute(userName : String): Single<List<GithubModel>> = Single.zip(
        repository.requestGetUserList(userName),
        repository.requestGetRepositoryList(userName)
    ){ user, repos ->
        mutableListOf<GithubModel>(user).apply {
            addAll(repos)
        }
    }
        .subscribeOn(Schedulers.io())
        .map { it }
}