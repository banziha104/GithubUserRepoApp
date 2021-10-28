package com.lyj.githubuserrepoapp.data.source.api.service

import com.google.gson.annotations.SerializedName
import com.lyj.githubuserrepoapp.data.source.api.entity.repository.RepositoryListResponse
import com.lyj.githubuserrepoapp.data.source.api.entity.user.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService{
    companion object {
        const val DEFAULT_PAGE = 1
        const val DEFAULT_PER_PAGE = 30
    }

    @GET("users/{userName}")
    fun requestSearchUser(
        @Query("userName") userName: String,
        @Query("page") page : Int = 1,
        @Query("per_page") perPage : Int = DEFAULT_PER_PAGE,
        @Query("order") order: Order = Order.ASC,
        @Query("sort") sort: UserSort = UserSort.BEST_MATCH,
    ) : Single<UserResponse>

    @GET("users/{userName}/repos")
    fun requestRepositoryListByUserName(
        @Path("userName") userName: String,
        @Query("type") type: Type = Type.OWNER,
        @Query("sort") sort: RepoSort = RepoSort.FULL_NAME,
        @Query("direction") direction: Direction = Direction.ASC,
        @Query("per_page") perPage: Int = DEFAULT_PER_PAGE,
        @Query("page") page: Int = DEFAULT_PAGE
    ): Single<List<RepositoryListResponse.RepositoryResponseItem>>



    enum class Type {
        @SerializedName("all")
        ALL,

        @SerializedName("owner")
        OWNER,

        @SerializedName("member")
        MEMBER
    }

    enum class RepoSort {

        @SerializedName("created")
        CREATED,

        @SerializedName("updated")
        UPDATED,

        @SerializedName("pushed")
        PUSHED,

        @SerializedName("full_name")
        FULL_NAME,
    }


    enum class UserSort{
        @SerializedName("best_match")
        BEST_MATCH,
        @SerializedName("followers")
        FLOLOWERS,
        @SerializedName("repositories")
        REPOSITORIES
    }

    enum class Order{
        @SerializedName("ASC")
        ASC,
        @SerializedName("DESC")
        DESC
    }


    enum class Direction {

        @SerializedName("asc")
        ASC,

        @SerializedName("desc")
        DESC,
    }

}