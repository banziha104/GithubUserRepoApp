package com.lyj.githubuserrepoapp.domain.repository

import com.lyj.githubuserrepoapp.domain.model.GithubRepositoryModel
import com.lyj.githubuserrepoapp.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface GithubApiRepository {
    fun requestGetUserList(searchKeyword: String) : Single<GithubUserModel>

    fun requestGetRepositoryList(userName: String) : Single<List<GithubRepositoryModel>>
}