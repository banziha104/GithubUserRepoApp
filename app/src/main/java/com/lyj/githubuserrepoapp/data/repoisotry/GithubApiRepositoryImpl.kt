package com.lyj.githubuserrepoapp.data.repoisotry

import com.lyj.githubuserrepoapp.domain.model.GithubModel
import com.lyj.githubuserrepoapp.domain.model.GithubRepositoryModel
import com.lyj.githubuserrepoapp.domain.model.GithubUserModel
import com.lyj.githubuserrepoapp.domain.repository.GithubApiRepository
import io.reactivex.rxjava3.core.Single

class GithubApiRepositoryImpl : GithubApiRepository {
    override fun requestGetUserList(searchKeyword: String): Single<List<GithubUserModel>> {
        TODO("Not yet implemented")
    }

    override fun requestGetRepositoryList(
        searchKeyword: String,
        page: Int
    ): Single<List<GithubRepositoryModel>> {
        TODO("Not yet implemented")
    }
}