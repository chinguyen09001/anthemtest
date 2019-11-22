package org.mp.anthemtestandroid.view


import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.Settings.Global.putString
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import kotlinx.android.synthetic.main.fragment_article_detail.view.*

import org.mp.anthemtestandroid.R
import org.mp.anthemtestandroid.tools.withArgs

/**
 * A simple [Fragment] subclass.
 */
class ArticleDetailFragment : Fragment() {

    private lateinit var mContext: Context

    companion object{
        private const val URL_KEY = "URL_KEY"

        fun newInstance(url: String) = ArticleDetailFragment().withArgs{
            putString(URL_KEY, url)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
    }

    //onAttach
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onResume() {
        super.onResume()

        createWebView(view)

        //Handles progress bar
        view?.article_webview?.webViewClient = object : WebViewClient(){

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                view?.article_progress?.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                view?.article_progress?.visibility = View.GONE
            }
        }

        view?.article_webview?.webChromeClient = object  : WebChromeClient(){

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                view?.article_progress?.progress = newProgress
                if(newProgress == 100){
                    view?.article_progress?.visibility = View.GONE
                }
            }

        }

        view?.article_webview?.loadUrl(arguments?.getString(URL_KEY))


    }

    //WebView created
    fun createWebView(view: View?){
        view?.article_webview?.settings?.javaScriptEnabled = true
        view?.article_webview?.settings?.domStorageEnabled = true
        view?.article_webview?.settings?.useWideViewPort = true
        view?.article_webview?.settings?.setSupportZoom(true)
        view?.article_webview?.settings?.cacheMode = WebSettings.LOAD_NO_CACHE

    }


}
