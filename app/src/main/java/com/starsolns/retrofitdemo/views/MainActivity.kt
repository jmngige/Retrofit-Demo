package com.starsolns.retrofitdemo.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.starsolns.retrofitdemo.databinding.ActivityMainBinding
import com.starsolns.retrofitdemo.models.Post
import com.starsolns.retrofitdemo.utils.DemoViewModel

const val STRING_EXTRA_ID = "post_id"
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: DemoViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DemoAdapter
    private var demoList = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i(TAG, "onCreate: Error")
        viewModel = ViewModelProvider(this)[DemoViewModel::class.java]
        viewModel.posts.observe(this, Observer { posts->
            demoList.addAll(posts)
            adapter.notifyDataSetChanged()
        })

        viewModel.isLoading.observe(this, Observer { isLoading->
            binding.progress.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        adapter = DemoAdapter(this, demoList, object: DemoAdapter.ItemClickListener{
            override fun onItemClick(post: Post) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(STRING_EXTRA_ID, post.id)
                startActivity(intent)
                overridePendingTransition(0,0)
            }

        })
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter

        viewModel.getPosts()

    }
}