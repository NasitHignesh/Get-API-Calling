package com.example.getmethod.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.getmethod.R
import com.example.getmethod.model.CommentsDataItem

class CommentAdapter(private val context: Activity, private val commentsArrayList: List<CommentsDataItem>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postId: TextView = itemView.findViewById(R.id.postId)
        var commentId: TextView = itemView.findViewById(R.id.commentId)
        var userName: TextView = itemView.findViewById(R.id.userName)
        var userEmail: TextView = itemView.findViewById(R.id.userEmail)
        var commentBody: TextView = itemView.findViewById(R.id.commentBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.comments_item_view, parent, false)
        return CommentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return commentsArrayList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val currItem = commentsArrayList[position]

        holder.postId.text = "Post Id : " + currItem.postId
        holder.commentId.text = "Comment Id : " + currItem.id
        holder.userName.text = "User Name: " + currItem.name
        holder.userEmail.text = "User Email : " + currItem.email
        holder.commentBody.text = "Body : " + currItem.body
    }

}