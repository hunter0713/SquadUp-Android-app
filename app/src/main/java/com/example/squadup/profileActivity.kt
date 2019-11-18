package com.example.squadup

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*

class profileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val intent = getIntent()
        val username = intent.getStringExtra("username")
        val title = findViewById<TextView>(R.id.profileTitle)
        title.text = "Welcome, " + username + "!"
        profileGoToTeamPage.setOnClickListener{
            val intent = Intent(this, TeamPage::class.java)
            startActivity(intent)
        }
    }
}
