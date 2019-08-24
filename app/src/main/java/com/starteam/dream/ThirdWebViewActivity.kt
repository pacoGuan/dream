package com.starteam.dream

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.webkit.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_third_web.*

class ThirdWebViewActivity : AppCompatActivity() {


    companion object {
        const val EXTRA_THIRD_URL = "extra_third_url"

        fun startActivity(context: Context, url: String?) {
            if (url.isNullOrEmpty()) {
                return
            }
            val i = Intent(context, ThirdWebViewActivity::class.java)
            i.putExtra(EXTRA_THIRD_URL, url)
            context.startActivity(i)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_web)

        mIvBack.setOnClickListener { view ->
            onBackPressed()
        }

        mWebViewThird.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        mWebViewThird.settings.javaScriptEnabled = true
        mWebViewThird.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING

        mWebViewThird.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let {
                    if (it.startsWith("http") || it.startsWith("https")) {
                        mWebViewThird.loadUrl(url)
                    }
                }
                return true
            }

        }

        val url = intent.getStringExtra(EXTRA_THIRD_URL)
        if (TextUtils.isEmpty(url)) {
            finish()
        } else {
            mWebViewThird.loadUrl(url)
        }
    }

    override fun onBackPressed() {
        if (mWebViewThird.canGoBack()) {
            mWebViewThird.goBack()
        } else {
            super.onBackPressed()
        }
    }
}