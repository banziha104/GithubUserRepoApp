package com.lyj.githubuserrepoapp.domain.usecase

import com.lyj.githubuserrepoapp.domain.repository.GithubApiRepository

class GetUserRepoPagingRepositoryUseCase(
    private val repository: GithubApiRepository
) {

}