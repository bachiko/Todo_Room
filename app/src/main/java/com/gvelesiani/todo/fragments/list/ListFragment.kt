package com.gvelesiani.todo.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gvelesiani.todo.MainActivity
import com.gvelesiani.todo.R
import com.gvelesiani.todo.data.Todo
import com.gvelesiani.todo.data.TodoViewModel
import com.gvelesiani.todo.fragments.update.UpdateFragmentArgs
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mTodoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // RecyclerView
        val adapter = ListAdapter(activity as MainActivity)
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // TodoViewModel
        mTodoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        mTodoViewModel.readAllData.observe(viewLifecycleOwner, Observer { todo ->
            adapter.setData(todo as MutableList<Todo>)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        //val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        //itemTouchHelper.attachToRecyclerView(recyclerView)

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllTodos()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllTodos() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mTodoViewModel.deleteAllTodo()
            // todo simple toast
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete Everything?")
        builder.setMessage("Are you sure you want to delete all?")
        builder.create().show()
    }

    /*private val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
            adapter.removeItem(viewHolder)
            mTodoViewModel.deleteTodo(args.currentTodo)
        }

    }*/


}