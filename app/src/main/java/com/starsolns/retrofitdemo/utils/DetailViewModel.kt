package com.starsolns.retrofitdemo.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starsolns.retrofitdemo.api.RetrofitInstance
import com.starsolns.retrofitdemo.models.Post
import com.starsolns.retrofitdemo.models.User
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {

    private val _post :  MutableLiveData<Post> = MutableLiveData()
        val post : LiveData<Post>
    get() = _post

    private val _user: MutableLiveData<User> = MutableLiveData()
        val user : LiveData<User>
    get() = _user

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
        val isLoading: LiveData<Boolean>
    get() = _isLoading

    fun getPostDetails(postId: Int){
        viewModelScope.launch {
            _isLoading.value = true
            val fetchedPost = RetrofitInstance.api.getPost(postId)
            val fetchedUser = RetrofitInstance.api.getUser(fetchedPost.userId)
            _post.value = fetchedPost
            _user.value = fetchedUser
            _isLoading.value = false
        }
    }
}