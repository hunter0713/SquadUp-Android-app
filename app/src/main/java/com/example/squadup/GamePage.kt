package com.example.squadup
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_game_page.*
import org.w3c.dom.Text

class GamePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)
        val teamIntent: Intent = getIntent()
        val team1 = teamIntent.getStringExtra("userTeam")
        val team2 = teamIntent.getStringExtra("otherTeam")
    }

}
