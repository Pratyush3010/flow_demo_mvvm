package com.example.flow_demo_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.flow_demo_mvvm.databinding.ActivityMainBinding
import com.example.flow_demo_mvvm.network.Status
import com.example.flow_demo_mvvm.viewmodel.CommentsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CommentsViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CommentsViewModel::class.java]

        binding.btnSerach.setOnClickListener {
            if (binding.searchEditView.text.isNullOrEmpty()) {
                Toast.makeText(this, "Query can' be empty", Toast.LENGTH_SHORT).show()
            }
            else{
               viewModel.getNewComment(binding.searchEditView.text.toString().toInt())
            }
        }

        lifecycleScope.launch {

            viewModel.commentstate.collect{

                when (it.status){

                    Status.LOADING ->{
                        binding.progressBar.isVisible = true
                    }
                     Status.SUCCESS ->{
                         binding.progressBar.isVisible = false

                         it.data?.let {comment ->
                              binding.commentIdTextview.text = comment.id.toString()
                             binding.nameTextview.text = comment.name
                             binding.emailTextview.text= comment.email
                             binding.commentTextview.text=  comment.comment
                         }
                     }
                    else -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(this@MainActivity, "${it.message}",Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }
}