package com.starsolns.retrofitdemo.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starsolns.retrofitdemo.api.RetrofitInstance
import com.starsolns.retrofitdemo.models.Post
import kotlinx.coroutines.launch

class EditViewModel: ViewModel() {

    private val _post: MutableLiveData<Post?> = MutableLiveData()
        val post: LiveData<Post?>
    get() = _post

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
        val isLoading: LiveData<Boolean>
    get()= _isLoading


    fun updatePost(postId: Int, post: Post){
        viewModelScope.launch {
            _isLoading.value = true
            _post.value = null
            val postUpdate = RetrofitInstance.api.updatePost(postId, post)
            _post.value = postUpdate
            _isLoading.value = false
        }
    }

}