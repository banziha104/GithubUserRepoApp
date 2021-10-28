package com.lyj.githubuserrepoapp.data.source.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.lyj.githubuserrepoapp.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

class GithubPagingSource() : RxPagingSource<Int, GithubUserModel>() {
    companion object {
        private const val DEFAULT_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, GithubUserModel>): Int? = DEFAULT_PAGE

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, GithubUserModel>> {
//        val page = params.key ?: DEFAULT_PAGE
        TODO()
    }
}