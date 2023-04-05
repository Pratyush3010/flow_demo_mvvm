package com.example.flow_demo_mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flow_demo_mvvm.commentrepository.CommentsRepository
import com.example.flow_demo_mvvm.model.Comment_Model
import com.example.flow_demo_mvvm.network.ApiService
import com.example.flow_demo_mvvm.network.CommentApiState
import com.example.flow_demo_mvvm.network.Status
import com.example.flow_demo_mvvm.utils.AppConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CommentsViewModel : ViewModel() {
    private val repository = CommentsRepository(AppConfig.ApiService())

    val commentstate = MutableStateFlow(
        CommentApiState(Status.LOADING,Comment_Model(),"")
    )

    init {
        getNewComment(1)
    }

 fun getNewComment(id: Int) {

        commentstate.value = CommentApiState.loading()

        viewModelScope.launch {
            repository.getComment(id)
                .catch {
                    commentstate.value = CommentApiState.error(it.message.toString())
                }
                .collect{
                    commentstate.value = CommentApiState.success(it.data)
                }
        }
    }
}