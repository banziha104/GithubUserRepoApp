package com.lyj.githubuserrepoapp.domain.repository

import com.lyj.githubuserrepoapp.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface GithubApiRepository {
    fun requestGetUserList(searchKeyword: String) : Single<List<GithubUserModel>>

    fun requestGetRepositoryList(searchKeyword: String, page : Int = 1) : Single<List<GithubUserModel>>
}