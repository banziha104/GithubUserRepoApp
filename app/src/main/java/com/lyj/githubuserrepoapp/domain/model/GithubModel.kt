package com.lyj.githubuserrepoapp.domain.model

sealed interface GithubModel

interface GithubRepositoryModel : GithubModel{
    val repoName : String?
    val description : String?
    val starCount : Int?
}

interface GithubUserModel : GithubModel {
    val imageUrl : String?
    val name : String?
}