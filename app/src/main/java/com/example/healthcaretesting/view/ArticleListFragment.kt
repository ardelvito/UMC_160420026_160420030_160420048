package com.example.healthcaretesting.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcaretesting.R
import com.example.healthcaretesting.model.Article
import com.example.healthcaretesting.viewmodel.ArticleListViewModel


class ArticleListFragment : Fragment() {

    //Article View Model
    private lateinit var viewModel: ArticleListViewModel
    private val articleAdapter = ArticleListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Show bottom nav, drawer and toolbar
        (activity as MainActivity).setComponentsVisibility(true)

        //Change text in toolbar
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = "Article List"


        val sharedPreferences = requireContext().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        val userIdPreferences = requireContext().getSharedPreferences("UserId", Context.MODE_PRIVATE)
        val userId = userIdPreferences.getInt("userId", 0)

        if (isLoggedIn(sharedPreferences)) {
            // User is already logged in, continue displaying the ArticleListFragment
            Log.d("Status Login", "Anda Sudah Login dengan ID $userId")
            Toast.makeText(this.context, "Anda Sudah Login! dengan ID $userId", Toast.LENGTH_LONG).show()

        } else {
            // User is not logged in, navigate to the LoginFragment
//            findNavController().navigate(R.id.loginFragment)
            val action = ArticleListFragmentDirections.actionItemArticleListLoginFragment()
            Navigation.findNavController(view).navigate(action)
            Log.d("Status Login", "Anda Belum Login")

        }

        viewModel = ViewModelProvider(this)[ArticleListViewModel::class.java]
        viewModel.refresh()
        val recViewArticle = view.findViewById<RecyclerView>(R.id.recViewArticle)
        recViewArticle.layoutManager = LinearLayoutManager(context)
        recViewArticle.adapter = articleAdapter
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.articleLiveData.observe(viewLifecycleOwner) {
            articleAdapter.updateArticleList(it as ArrayList<Article> /* = java.util.ArrayList<com.example.todoapp.model.Model.Todo> */)
            val txtEmpty = view?.findViewById<TextView>(R.id.txtArticleListEmpty)
            if (it.isEmpty()) {
                txtEmpty?.visibility = View.VISIBLE
            } else {
                txtEmpty?.visibility = View.GONE
            }
        }
    }

    private fun isLoggedIn(sharedPreferences: SharedPreferences): Boolean {
        // Retrieve the login state from SharedPreferences
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

}