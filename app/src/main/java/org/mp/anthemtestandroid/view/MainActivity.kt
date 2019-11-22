package org.mp.anthemtestandroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import org.mp.anthemtestandroid.R
import org.mp.anthemtestandroid.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var newsVM: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsVM = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        supportFragmentManager.beginTransaction().replace(R.id.news_container, ArticlesFragment()).addToBackStack(ArticlesFragment::class.java.simpleName).commit()

    }


    fun getNewsVM(): NewsViewModel {
        return newsVM
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }

}
