package com.example.healthcaretesting.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.healthcaretesting.R
import com.example.healthcaretesting.databinding.FragmentArticleDetailBinding
import com.example.healthcaretesting.viewmodel.ArticleDetailViewModel


class ArticleDetailFragment : Fragment(){
    private lateinit var viewModel: ArticleDetailViewModel
    private lateinit var dataBinding: FragmentArticleDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       dataBinding = DataBindingUtil.inflate(
           inflater,
           R.layout.fragment_article_detail,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            val id = ArticleDetailFragmentArgs.fromBundle(requireArguments()).articleId
            viewModel = ViewModelProvider(this)[ArticleDetailViewModel::class.java]
            viewModel.detail(id)
            observeDetailModel()
        }
    }

    fun observeDetailModel(){
        viewModel.articleLD.observe(viewLifecycleOwner) {
            dataBinding.article = it
        }
    }
}