package com.example.flow_demo_mvvm.commentrepository

import com.example.flow_demo_mvvm.model.Comment_Model
import com.example.flow_demo_mvvm.network.ApiService
import com.example.flow_demo_mvvm.network.CommentApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CommentsRepository(private val apiService: ApiService) {

    suspend fun getComment(id:Int): Flow<CommentApiState<Comment_Model>>{
        return flow {

            val comment = apiService.getcomments(id)

            emit(CommentApiState.success(comment))
        }.flowOn(Dispatchers.IO)
    }
}