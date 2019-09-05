package com.brian.prioritize_2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskListAdapter internal constructor(context: Context): RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var tasks = emptyList<Task>()

    inner class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TaskViewHolder{
        val itemView = inflater.inflate(R.layout.recylerview_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = tasks[position]
        holder.wordItemView.text = current.task
    }

    internal fun setTasks(tasks: List<Task>){
        this.tasks = tasks
        notifyDataSetChanged()
    }

    override fun getItemCount() = tasks.size
}
