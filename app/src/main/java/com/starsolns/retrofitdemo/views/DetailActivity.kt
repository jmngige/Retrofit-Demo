package com.starsolns.retrofitdemo.views

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.starsolns.retrofitdemo.R
import com.starsolns.retrofitdemo.databinding.ActivityDetailBinding
import com.starsolns.retrofitdemo.utils.DetailViewModel

private const val TAG = "DetailActivity"
const val EXTRA_POST="Extra_post"
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i(TAG, "onCreate: Error")
        val postId = intent.getIntExtra(STRING_EXTRA_ID, -1)

        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        viewModel.isLoading.observe(this, Observer { isloading->
            Log.i(TAG, "onCreate: Error")
            binding.progressBar.visibility = if (isloading) View.VISIBLE else View.GONE
        })

        viewModel.post.observe(this, Observer {post->
            Log.i(TAG, "onCreate: Error")
            binding.detailTitle.text = post.title
            binding.detailBody.text = post.body
        })

        viewModel.user.observe(this, Observer { user->
            binding.detailFullName.text = "Name: ${user.name}"
            binding.detailUsername.text = "Username: ${user.username}"
            binding.detailUserEmail.text = "Email: ${user.email}"
            binding.detailUserWebsite.text = "Webiste: ${user.website}"
            binding.detailUserPhone.text = "Phone: ${user.phone}"
            Log.i(TAG, "onCreate: Error")
        })

        viewModel.getPostDetails(postId)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.edit_post -> {
               viewModel.post.observe(this, Observer { post->
                   val intent = Intent(this, EditActivity::class.java)
                   intent.putExtra(EXTRA_POST, post)
                   startActivity(intent)
               })
            }
        }
        return super.onOptionsItemSelected(item)
    }
}