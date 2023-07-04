package com.example.healthcaretesting.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcaretesting.R
import com.example.healthcaretesting.model.Article
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class ArticleListAdapter(val articles: ArrayList<Article>, val adapter: (Article) -> Unit):
    RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.article_list_item, parent, false)

        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        var articleTitle = holder.view.findViewById<TextView>(R.id.txtArticleTitle)
        var articleAuthor = holder.view.findViewById<TextView>(R.id.txtArticleAuthor)
        var articlePublishDate = holder.view.findViewById<TextView>(R.id.txtArticlePublishDate)

        //image view
        val url_img = articles[position].url_img
        var articleImageView = holder.view.findViewById<ImageView>(R.id.imgArticle)
        var articleProgressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarArticleList)


        //button detail
        var btnDetail = holder.view.findViewById<Button>(R.id.btnArticleDetail)
        btnDetail.setOnClickListener{
            val action = ArticleListFragmentDirections.actionArticleDetail(articles[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        //picasso image
        Picasso.get()
            .load(url_img)
            .resize(200,200)
            .centerCrop()
            .into(articleImageView, object : Callback{
                override fun onSuccess() {
                    // Image loaded successfully
                    articleProgressBar?.visibility = View.GONE
                    Log.d("Success Load", "IMG Successfully Loaded")

                }

                override fun onError(e: Exception?) {
                    // Handle error
                    articleProgressBar?.visibility = View.GONE
                    Log.d("Error Load", e.toString())
                }
            })

        //assign value into each element
        articleTitle.text = articles[position].title
        articleAuthor.text = articles[position].author
        articlePublishDate.text = articles[position].publish_date

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun updateArticleList(newArticle: ArrayList<Article>){
        articles.clear()
        articles.addAll(newArticle)
        notifyDataSetChanged()
    }


}