package com.example.buskers

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPostAdapter(
    var postList: ArrayList<Post>,
    val inflater: LayoutInflater,
    val glide: RequestManager,
    val activity: Activity
) : RecyclerView.Adapter<MyPostAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postOwner: TextView
        val postImage: ImageView
        val postContent: TextView

        init {
            postOwner = itemView.findViewById(R.id.post_owner)
            postImage = itemView.findViewById(R.id.post_img)
            postContent = itemView.findViewById(R.id.post_content)

            itemView.findViewById<TextView>(R.id.delete).setOnClickListener {
                (activity.application as MasterApplication).service.deletePost(
                    postList.get(adapterPosition).id!!
                ).enqueue(object : Callback<Post> {

                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        if (response.isSuccessful) {
                            Toast.makeText(activity, "삭제되었습니다.", Toast.LENGTH_LONG).show()
                            activity.startActivity(Intent(activity, MyListActivity::class.java))
                        } else {
                            Toast.makeText(activity, "삭제 오류", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        Toast.makeText(activity, "서버 오류", Toast.LENGTH_LONG).show()
                    }
                })
            }

            itemView.findViewById<TextView>(R.id.update).setOnClickListener {
                val intent = Intent(activity, UpdateActivity::class.java)
                intent.putExtra("pk", postList.get(adapterPosition).id)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.myitem_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postOwner.setText(postList.get(position).owner)
        holder.postContent.setText(postList.get(position).content)
        glide.load(postList.get(position).image).into(holder.postImage)
    }
}