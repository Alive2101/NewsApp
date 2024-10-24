package com.pavel.newsapp.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.pavel.newsapp.R
import com.pavel.newsapp.controller.NetworkController
import com.pavel.newsapp.databinding.FragmentNewsBinding
import com.pavel.newsapp.model.News
import com.pavel.newsapp.presentation.news.recyclerView.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var binding: FragmentNewsBinding? = null
    private val viewModel: NewsViewModel by viewModels()

    @Inject
    lateinit var networkController: NetworkController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObservable()
    }

    private fun initView() {
        binding?.run {
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val category = tab?.text.toString()
                    viewModel.setCategory(category)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun setObservable() {
        viewModel.run {
            binding?.run {
                categoryListLiveData.observe(viewLifecycleOwner) {
                    for (category in newsCategories) {
                        tabLayout.addTab(tabLayout.newTab().setText(category))
                    }
                }
                newsData.observe(viewLifecycleOwner) {
                    setList(it)
                }
                categoryLiveData.observe(viewLifecycleOwner) {
                    getNews(it)

                }
                isNetworkConnected.observe(viewLifecycleOwner) {
                    if (it) {
                        binding?.newsRecyclerView?.visibility = View.VISIBLE
                        binding?.noInetTextView?.visibility = View.GONE
                        getNews(newsCategories[0])
                    } else {
                        binding?.newsRecyclerView?.visibility = View.GONE
                        binding?.noInetTextView?.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setList(list: List<News>?) {
        val bundle = Bundle()
        binding?.newsRecyclerView?.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = NewsAdapter { news ->
                    bundle.putParcelable("news", news)
                    findNavController().navigate(
                        R.id.action_newsFragment_to_viewNewsFragment,
                        bundle
                    )
                }
            }
            (adapter as? NewsAdapter)?.submitList(list)
        }
    }
}