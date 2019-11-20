package com.example.squadup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_start_game.*
import kotlinx.android.synthetic.main.content_main.*

class StartGame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        startGameButton.setOnClickListener{
            var team1 = findViewById<EditText>(R.id.startGameTeam1Name).text.toString()
            var team2 = findViewById<EditText>(R.id.startGameTeam2Name).text.toString()
            checkTeams(team1,team2)
        }
    }
    fun checkTeams(team1: String, team2: String) {
        val request = object : StringRequest(
            Request.Method.POST,
            "https://people.eecs.ku.edu/~h961c228/checkTeams.php",
            object : Response.Listener<String> {
                override fun onResponse(response: String) {
                    Toast.makeText(this@StartGame, response, Toast.LENGTH_LONG).show()
                    if (response == "success") {
                        goToGamePage(team1,team2)
                    }
                }
            },
            Response.ErrorListener() {
                fun onErrorResponse(error: VolleyError) {
                    Log.d("error", error.toString())
                }
            }) {

            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>();
                params["team1"] = team1     //here
                params["team2"] = team2  //here
                return params
            }
        };


        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)

    }
    fun goToGamePage(team1: String, team2: String){
        val intent = Intent(this, GamePage::class.java)
        intent.putExtra("team1",team1)
        intent.putExtra("team2",team2)
        startActivity(intent)
    }

}
