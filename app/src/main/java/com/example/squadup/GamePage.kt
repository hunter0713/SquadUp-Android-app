package com.example.squadup
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
class GamePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)
        val teamIntent: Intent = getIntent()
        val member1 = teamIntent.getStringExtra("Member1")
        val member2 = teamIntent.getStringExtra("Member2")
        val member3 = teamIntent.getStringExtra("Member3")
        val member4 = teamIntent.getStringExtra("Member4")
        val member5 = teamIntent.getStringExtra("Member5")




    }
}
