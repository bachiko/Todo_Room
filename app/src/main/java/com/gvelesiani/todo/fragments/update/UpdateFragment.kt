package com.gvelesiani.todo.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gvelesiani.todo.R
import com.gvelesiani.todo.data.Todo
import com.gvelesiani.todo.data.TodoViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mTodoViewModel: TodoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        view.etUpdateTodoName.setText(args.currentTodo.todoName)

        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)

        view.btnUpdateTodo.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val todoName = etUpdateTodoName.text.toString()

        //checking
        val updatedTodo = Todo(args.currentTodo.id, todoName)
        // update current todoM
        mTodoViewModel.updateTodo(updatedTodo)
        // navigate back
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteTodo()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTodo(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            mTodoViewModel.deleteTodo(args.currentTodo)
            // todo simple toast
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Delete ${args.currentTodo.todoName}?")
        builder.setMessage("Are you sure you want to delete?")
        builder.create().show()
    }

}