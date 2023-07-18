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

        holder.view.article
        holder.view.detailListener = this
//        var articleTitle = holder.view.findViewById<TextView>(R.id.txtArticleTitle)
//        var articleAuthor = holder.view.findViewById<TextView>(R.id.txtArticleAuthor)
//        var articlePublishDate = holder.view.findViewById<TextView>(R.id.txtArticlePublishDate)
//
//        //image view
//        val url_img = articles[position].url_img
//        var articleImageView = holder.view.findViewById<ImageView>(R.id.imgArticle)
//        var articleProgressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarArticleList)
//
//
//        //button detail
//        var btnDetail = holder.view.findViewById<Button>(R.id.btnArticleDetail)
//        btnDetail.setOnClickListener{
//            val action = ArticleListFragmentDirections.actionArticleDetail(articles[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        //picasso image
//        Picasso.get()
//            .load(url_img)
//            .resize(200,200)
//            .centerCrop()
//            .into(articleImageView, object : Callback{
//                override fun onSuccess() {
//                    // Image loaded successfully
//                    articleProgressBar?.visibility = View.GONE
//                    Log.d("Success Load", "IMG Successfully Loaded")
//
//                }
//
//                override fun onError(e: Exception?) {
//                    // Handle error
//                    articleProgressBar?.visibility = View.GONE
//                    Log.d("Error Load", e.toString())
//                }
//            })
//
//        //assign value into each element
//        articleTitle.text = articles[position].title
//        articleAuthor.text = articles[position].author
//        articlePublishDate.text = articles[position].publish_date

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