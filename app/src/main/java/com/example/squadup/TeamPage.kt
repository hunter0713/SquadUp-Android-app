package com.example.squadup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_team_page.*
import kotlinx.android.synthetic.main.content_main.*

class TeamPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_page)
        val teamIntent = Intent(this@TeamPage, GamePage::class.java)
        val member1 = editText.getText().toString()
        val member2 = editText5.getText().toString()
        val member3 = editText5.getText().toString()
        val member4 = editText7.getText().toString()
        val member5 = editText8.getText().toString()
        teamIntent.putExtra("Member1", member1)
        teamIntent.putExtra("Member2", member2)
        teamIntent.putExtra("Member3", member3)
        teamIntent.putExtra("Member4", member4)
        teamIntent.putExtra("Member5", member5)
    }
    }

