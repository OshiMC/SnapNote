package com.example.snap_note.Adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.snap_note.AddNewTask
import com.example.snap_note.MainActivity
import com.example.snap_note.Model.SnapNoteModel
import com.example.snap_note.R
import com.example.snap_note.Utils.DatabaseHandler

class SnapNoteAdapter(private val db: DatabaseHandler, private val activity: MainActivity) :
    RecyclerView.Adapter<SnapNoteAdapter.ViewHolder>() {

    private var todoList: List<SnapNoteModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        db.openDatabase()
        val item: SnapNoteModel = todoList!![position]
        holder.task.text = item.task
        holder.task.isChecked = item.status != 0
        holder.task.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                db.updateStatus(item.id, 1)
            } else {
                db.updateStatus(item.id, 0)
            }
        }
    }

    override fun getItemCount(): Int {
        return todoList?.size ?: 0
    }

    fun setTasks(todoList: List<SnapNoteModel?>?) {
        this.todoList = todoList as List<SnapNoteModel>?
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        val item: SnapNoteModel = todoList!![position]
        db.deleteTask(item.id)
        todoList = todoList?.toMutableList()?.apply { removeAt(position) }
        notifyItemRemoved(position)
    }

    fun editItem(position: Int) {
        val item: SnapNoteModel = todoList!![position]
        val bundle = Bundle().apply {
            putInt("id", item.id)
            putString("task", item.task)
        }
        val fragment = AddNewTask().apply {
            arguments = bundle
        }
        fragment.show(activity.supportFragmentManager, AddNewTask.TAG)
    }

    fun getContext(): Context {
        TODO("Not yet implemented")
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val task: CheckBox = view.findViewById(R.id.todoCheckBox)
    }
}
