package com.starsolns.retrofitdemo.views

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.starsolns.retrofitdemo.databinding.ActivityEditBinding
import com.starsolns.retrofitdemo.models.Post
import com.starsolns.retrofitdemo.utils.EditViewModel

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding;
    private lateinit var viewModel: EditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = intent.getSerializableExtra(EXTRA_POST) as Post
        binding.editTitle.setText(post.title)
        binding.editBody.setText(post.body)

        viewModel = ViewModelProvider(this)[EditViewModel::class.java]
        viewModel.post.observe(this, Observer { post->
            if(post != null){
                binding.newTitle.text = post.title
                binding.newBody.text = post.body
            }
        })

        viewModel.isLoading.observe(this, Observer { isloading->
            binding.editProgress.visibility = if (isloading) View.VISIBLE else View.GONE
            if (isloading){
                binding.newTitle.text = null
                binding.newBody.text = null
            }
        })

        binding.btnUpdate.setOnClickListener {
            viewModel.updatePost(post.id, Post(post.id, post.userId, binding.editTitle.text.toString(), binding.editBody.text.toString()))
        }

    }
}