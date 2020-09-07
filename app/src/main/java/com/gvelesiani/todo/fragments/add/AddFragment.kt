package com.gvelesiani.todo.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gvelesiani.todo.R
import com.gvelesiani.todo.data.Todo
import com.gvelesiani.todo.data.TodoViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mTodoViewModel: TodoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        view.btnAddTodo.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase(){
        val todoName = etTodoName.text.toString()

        if(inputCheck(todoName)){
            // create object
            val todo = Todo(0, todoName)

            // add data to database
            mTodoViewModel.addTodo(todo)

            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(todoName : String): Boolean{
        return !(TextUtils.isEmpty(todoName))
    }
 }