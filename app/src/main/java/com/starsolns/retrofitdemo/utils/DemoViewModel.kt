package com.starsolns.retrofitdemo.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starsolns.retrofitdemo.api.RetrofitInstance
import com.starsolns.retrofitdemo.models.Post
import kotlinx.coroutines.launch

private val TAG = "DemoViewModel"
class DemoViewModel: ViewModel() {

    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
        val posts : LiveData<List<Post>>
    get() = _posts

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData(false)
        val isLoading : LiveData<Boolean>
    get() = _isLoading

    fun getPosts(){
        viewModelScope.launch {
           try {
               _isLoading.value = true
               val fetchedPosts = RetrofitInstance.api.getPosts()
               _posts.value = fetchedPosts
               _isLoading.value = false
           }catch (e: Exception){
               Log.i(TAG, "getPosts: Error occurred")
           }
        }
    }


}