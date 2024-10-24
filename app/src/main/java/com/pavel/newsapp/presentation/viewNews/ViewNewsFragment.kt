package com.pavel.newsapp.presentation.viewNews

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pavel.newsapp.R
import com.pavel.newsapp.databinding.FragmentForViewingTheNewsBinding
import com.pavel.newsapp.model.News
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewNewsFragment : Fragment() {

    private var binding: FragmentForViewingTheNewsBinding? = null
    private val viewModel: ViewNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForViewingTheNewsBinding.inflate(inflater)
        return binding?.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val news = arguments?.getParcelable<News>("news")
        initView(news)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled", "SetTextI18n")
    private fun initView(news: News?) {
        binding?.run {
            binding?.run {
                newsWebView.webViewClient = WebViewClient()
                newsWebView.settings.javaScriptEnabled = true
                newsWebView.loadUrl(news?.url.toString())
                authorTextView.text = (if (news?.author?.isEmpty() == true) {
                    getString(R.string.non_author)
                } else {
                    "${getString(R.string.author)}: ${news?.author}"
                }).toString()
                dateTextView.text =
                    "${getString(R.string.date)}: ${viewModel.parseData(news?.publishedAt)}"
                saveButton.setOnClickListener {
                    news?.let { news ->
                        viewModel.saveNews(
                            news
                        )
                    }
                }
            }
        }
    }
}