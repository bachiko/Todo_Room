package com.gvelesiani.todo.fragments.list

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Paint
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gvelesiani.todo.R
import com.gvelesiani.todo.data.Todo
import com.gvelesiani.todo.data.TodoViewModel
import com.gvelesiani.todo.fragments.update.UpdateFragmentArgs
import kotlinx.android.synthetic.main.todo_custom_layout.view.*

class ListAdapter(private val context: Context): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    //private var mp = MediaPlayer()
    private var todoList = emptyList<Todo>()
    //private lateinit var mTodoViewModel: TodoViewModel

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_custom_layout,
                parent,
                false
            )
        )
    }

//    fun removeItem(viewHolder: RecyclerView.ViewHolder) {
//        todoList.removeAt(viewHolder.adapterPosition)
//        notifyItemRemoved(viewHolder.adapterPosition)
//    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = todoList[position]
        holder.itemView.tvTodoName.text = currentItem.todoName

        holder.itemView.todo_layout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

//        holder.itemView.todo_layout.checkboxSuccess.setOnCheckedChangeListener {_, b ->
//            if(b){
//                holder.itemView.tvTodoName.paintFlags = holder.itemView.tvTodoName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//
//                mp = MediaPlayer.create(context, R.raw.success)
//                mp.setOnPreparedListener{
//                    mp.start()
//                }
//
//                mp.setOnCompletionListener {
//                    mp.release()
//                }
//
//            } else {
//                holder.itemView.tvTodoName.paintFlags = 0
//            }
//        }

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setData(todo: List<Todo>){
        this.todoList = todo
        notifyDataSetChanged()
    }
}
