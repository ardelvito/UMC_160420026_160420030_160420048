package com.example.healthcaretesting.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcaretesting.R
import com.example.healthcaretesting.databinding.ArticleListItemBinding
import com.example.healthcaretesting.model.Article

class ArticleListAdapter(private val articles: ArrayList<Article>):
    RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>(),ArticleListFragmentInterface {

    class ArticleViewHolder(val view: ArticleListItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =DataBindingUtil.inflate<ArticleListItemBinding>(inflater,R.layout.article_list_item, parent, false)
//        val view = inflater.inflate(R.layout.article_list_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.view.article= articles[position]
        holder.view.detailListener = this
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun updateArticleList(newArticle: ArrayList<Article>){
        articles.clear()
        articles.addAll(newArticle)
        notifyDataSetChanged()
    }

    override fun onDetailClick(view: View) {
        val id = view.tag.toString().toInt()
        val action = ArticleListFragmentDirections.actionArticleDetail(id)
        Navigation.findNavController(view).navigate(action)
    }


}