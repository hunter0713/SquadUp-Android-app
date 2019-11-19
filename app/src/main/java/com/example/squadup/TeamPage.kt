package com.example.squadup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_team_page.*

class TeamPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_page)
        val teamIntent = Intent(this@TeamPage, GamePage::class.java)
        val member1 = player1.text.toString()
        val member2 = player3.getText().toString()
        val member3 = player3.getText().toString()
        val member4 = player4.getText().toString()
        val member5 = player5.getText().toString()
        teamIntent.putExtra("Member1", member1)
        teamIntent.putExtra("Member2", member2)
        teamIntent.putExtra("Member3", member3)
        teamIntent.putExtra("Member4", member4)
        teamIntent.putExtra("Member5", member5)
        //go to game activity if button is clicked
        val startGamebtn = findViewById(R.id.startButton) as Button
        startGamebtn.setOnClickListener{
            startActivity(teamIntent)
        }
    }
    }

