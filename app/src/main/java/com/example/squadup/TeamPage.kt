package com.example.squadup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TeamPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_page)
        setSupportActionBar(findViewById(R.id.my_toolbar))
    }
}
