package com.pavel.newsapp.presentation.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pavel.newsapp.R
import com.pavel.newsapp.databinding.FragmentBookmarksBinding
import com.pavel.newsapp.model.News
import com.pavel.newsapp.presentation.bookmarks.recyclerView.BookmarksAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookmarksFragment : Fragment() {

    private var binding: FragmentBookmarksBinding? = null
    private val viewModel: BookmarksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarksBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservable()
    }

    private fun setObservable() {
        viewModel.run {
            getNewsList()
            newsData.observe(viewLifecycleOwner) {
                setList(it)
            }
        }
    }

    private fun setList(list: List<News>?) {
        val bundle = Bundle()
        binding?.saveRecyclerView?.run {
            if (adapter == null) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = BookmarksAdapter { news ->
                    bundle.putParcelable("news", news)
                    findNavController().navigate(
                        R.id.action_bookmarksFragment_to_viewNewsFragment,
                        bundle
                    )
                }
            }
            (adapter as? BookmarksAdapter)?.submitList(list)
        }
    }
}