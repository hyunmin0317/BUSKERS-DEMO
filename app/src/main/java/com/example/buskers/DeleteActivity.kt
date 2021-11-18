package com.example.buskers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pk = intent.getIntExtra("pk", -1)

        (application as MasterApplication).service.deletePost(
            pk
        ).enqueue(object : Callback<Post> {

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@DeleteActivity, "삭제되었습니다.", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@DeleteActivity, MyListActivity::class.java))
                } else {
                    Toast.makeText(this@DeleteActivity, "삭제 오류", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(this@DeleteActivity, "서버 오류", Toast.LENGTH_LONG).show()
            }
        })
    }
}