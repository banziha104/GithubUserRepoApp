package com.lyj.githubuserrepoapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lyj.githubuserrepoapp.R
import com.lyj.githubuserrepoapp.databinding.ItemRepoBinding
import com.lyj.githubuserrepoapp.databinding.ItemUserBinding
import com.lyj.githubuserrepoapp.domain.model.GithubModel
import com.lyj.githubuserrepoapp.domain.model.GithubRepositoryModel
import com.lyj.githubuserrepoapp.domain.model.GithubUserModel


class GithubAdapter(
    private val items : List<GithubModel>
) : RecyclerView.Adapter<GithubViewHolder<GithubModel>>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubViewHolder<GithubModel> {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            GithubViewHolder.USER_VIEW_TYPE -> GithubViewHolder.UserViewHolder(ItemUserBinding.inflate(inflater,parent,false))
            else -> GithubViewHolder.RepoViewHolder(ItemRepoBinding.inflate(inflater,parent,false))
        } as GithubViewHolder<GithubModel>
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GithubViewHolder<GithubModel>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int =
        when(position){
            0 -> GithubViewHolder.USER_VIEW_TYPE
            else -> GithubViewHolder.REPO_VIEW_TYPE
        }
}


sealed class GithubViewHolder<in ITEM_TYPE : GithubModel>(view : View) : RecyclerView.ViewHolder(view){

    companion object ViewType{
        const val USER_VIEW_TYPE = 0
        const val REPO_VIEW_TYPE = 1
    }

    abstract fun bind(item : ITEM_TYPE?)

    class UserViewHolder(private val binding: ItemUserBinding) : GithubViewHolder<GithubUserModel>(binding.root){
        override fun bind(item: GithubUserModel?) {

            binding.itemUserTxtName.text = item?.userName ?: "유저명 없음"

            Glide
                .with(itemView)
                .load(item?.imageUrl)
                .into(
                    binding
                        .itemUserImgAvatar
                )
        }
    }
    class RepoViewHolder(private val binding: ItemRepoBinding) : GithubViewHolder<GithubRepositoryModel>(binding.root){
        override fun bind(item: GithubRepositoryModel?) {
            binding.itemRepoTxtName.text = item?.repoName ?: "레파지토리 이름 없음"
            binding.itemRepoTxtDescription.text = item?.description ?: "레파지토리 설명 없음"
            binding.itemRepoTxtStar.text = (item?.starCount ?: 0).toString()
        }
    }
}