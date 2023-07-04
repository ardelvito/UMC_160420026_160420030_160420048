package com.example.healthcaretesting.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcaretesting.R
import com.example.healthcaretesting.model.Article
import com.example.healthcaretesting.viewmodel.ArticleListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ArticleListFragment : Fragment() {

    //Article View Model
    private lateinit var viewModel: ArticleListViewModel
    private val adapter = ArticleListAdapter(arrayListOf(), { item -> viewModel.clearTask(item)})


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ArticleListViewModel::class.java)

        viewModel.refresh()
        val recViewArticle = view.findViewById<RecyclerView>(R.id.recViewArticle)
        recViewArticle.layoutManager = LinearLayoutManager(context)
        recViewArticle.adapter = adapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.articleLiveData.observe(viewLifecycleOwner, Observer{
            adapter.updateArticleList(it as ArrayList<Article> /* = java.util.ArrayList<com.example.todoapp.model.Model.Todo> */)
            val txtEmpty = view?.findViewById<TextView>(R.id.txtArticleListEmpty)

            if(it.isEmpty()){
                txtEmpty?.visibility = View.VISIBLE
            }
            else{
                txtEmpty?.visibility =View.GONE
            }
        })
    }

}