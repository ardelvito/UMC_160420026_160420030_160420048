package com.example.healthcaretesting.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.healthcaretesting.R
import com.example.healthcaretesting.viewmodel.ArticleDetailViewModel


class ArticleDetailFragment : Fragment() {
    private lateinit var viewModel: ArticleDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            val id = ArticleDetailFragmentArgs.fromBundle(requireArguments()).articleId
            viewModel = ViewModelProvider(this)[ArticleDetailViewModel::class.java]
        }
    }

    fun observeDetailModel(){
        viewModel.articleLD.observe(viewLifecycleOwner, Observer {

        })
    }
}