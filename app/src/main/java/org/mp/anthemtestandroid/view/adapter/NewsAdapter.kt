package org.mp.anthemtestandroid.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_unit.view.*
import org.mp.anthemtestandroid.R
import org.mp.anthemtestandroid.databinding.ArticleUnitBinding
import org.mp.anthemtestandroid.viewmodel.NewsViewModel

class NewsAdapter(private val viewModel: NewsViewModel, val clickListener: ItemClickListener): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.article_unit, parent, false))
    }

    override fun getItemCount(): Int {
        return try {
            return viewModel.newsLiveData.value?.articles?.size!!
        } catch (e: KotlinNullPointerException) {           //Just in case
            0
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.itemLayoutBinding.viewModel = viewModel
        holder.itemLayoutBinding.position = position

        //Load image
        Picasso.with(holder.itemView.context)
            .load(viewModel.getArticle(position)?.urlToImage).into(holder.itemView.article_img)

        //Article click
        holder.itemView.setOnClickListener{
            clickListener.onItemClicked(position)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class NewsViewHolder(val itemLayoutBinding: ArticleUnitBinding):RecyclerView.ViewHolder(itemLayoutBinding.root)

    interface ItemClickListener {
        fun onItemClicked(position: Int)
    }

}