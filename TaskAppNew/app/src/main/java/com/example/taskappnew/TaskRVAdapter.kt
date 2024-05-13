package com.example.taskappnew

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskRVAdapter(
    val context: Context,
    val taskClickInterface: TaskClickInterface,
    val taskClickDeleteInterface: TaskClickDeleteInterface
):RecyclerView.Adapter<TaskRVAdapter.ViewHolder>() {

    private val allTasks = ArrayList<Task>()
    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val taskTV = itemView.findViewById<TextView>(R.id.idTVTaskTitle)
        val timeTV = itemView.findViewById<TextView>(R.id.idTVTimeStamp)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_rv_item,parent,false);
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskTV.setText(allTasks.get(position).taskTitle)
        holder.timeTV.setText("Last updated: " + allTasks.get(position).timeStamp)

        holder.deleteIV.setOnClickListener {
            taskClickDeleteInterface.onDeleteIconClick(allTasks.get(position))
        }

        holder.itemView.setOnClickListener {
            taskClickInterface.onTaskClick(allTasks.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allTasks.size
    }

    fun updateList(newList: List<Task>){
        allTasks.clear()
        allTasks.addAll(newList)
        notifyDataSetChanged()
    }
}

interface TaskClickDeleteInterface{
    fun onDeleteIconClick(task: Task)
}

interface TaskClickInterface {
    fun onTaskClick(task: Task)
}