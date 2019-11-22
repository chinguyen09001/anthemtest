package org.mp.anthemtestandroid.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_articles.view.*

import org.mp.anthemtestandroid.R
import org.mp.anthemtestandroid.model.News
import org.mp.anthemtestandroid.view.adapter.NewsAdapter
import org.mp.anthemtestandroid.viewmodel.NewsViewModel

class ArticlesFragment : Fragment(), NewsAdapter.ItemClickListener {

    private lateinit var adapter: NewsAdapter
    private lateinit var newsLiveData : LiveData<News>
    private lateinit var    newsViewModel: NewsViewModel
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_articles, container, false)

        newsViewModel = (mContext as MainActivity).getNewsVM()

        getNews()

        newsLiveData.observe(this, Observer<News> {
            adapter = NewsAdapter(newsViewModel, this)
            view.news_list.adapter = adapter
            view.news_list.layoutManager = LinearLayoutManager(mContext)
        })

        return view
    }



    private fun getNews() {
        newsLiveData = newsViewModel.getNews(
            "/v2/top-headlines",
            "us",
            "entertainment",
            "f9278e730f6f4b7ca33db8daa199d2e9",
            (mContext as MainActivity).application)
    }

    //On item click, go to Article Detail
    override fun onItemClicked(position: Int) {
        (mContext as MainActivity).supportFragmentManager
            .beginTransaction().replace(R.id.news_container, ArticleDetailFragment.newInstance(newsViewModel.getArticleWebUrl(position)!!))
            .addToBackStack(ArticlesFragment::class.java.simpleName)
            .commit()
    }


}
