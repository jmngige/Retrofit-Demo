package com.starsolns.retrofitdemo.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.starsolns.retrofitdemo.R
import com.starsolns.retrofitdemo.models.Post

class DemoAdapter(
    private val ctx: Context,
    private val demoList: List<Post>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<DemoAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(post: Post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(demoList[position])
    }

    override fun getItemCount(): Int {
        return demoList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title)

        fun bind(post: Post) {
            title.text = post.title
            itemView.setOnClickListener {
                itemClickListener.onItemClick(post)
            }
        }
    }

}