package com.example.squadup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_start_game.*


class StartGame : AppCompatActivity() {
    var userTeamList =
        arrayOf<String>("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
    var otherTeamList =
        arrayOf<String>("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        val intent = getIntent()

        val username = intent.getStringExtra("username")
        getUserTeams(username)
        getAllTeams(username)
        startGameButton.setOnClickListener {
            val spin = findViewById<Spinner>(R.id.userTeamSpinner)
            val spin2 = findViewById<Spinner>(R.id.otherTeamSpinner)
            val userTeam = spin.getSelectedItem().toString()
            val otherTeam = spin2.getSelectedItem().toString()
            val intent = Intent(this, GamePage::class.java)
            intent.putExtra("userTeam",userTeam);
            intent.putExtra("otherTeam",otherTeam)
            intent.putExtra("username",username)
            startActivity(intent)
        }
    }

    /**
     * @pre An active user
     * @param username: the active username
     * @post fetches the teams the user is currently on from SQL table
     */
    fun getUserTeams(username: String) {
        val request = object : StringRequest(
            Request.Method.POST,
            "https://people.eecs.ku.edu/~h961c228/getUserTeams.php",
            object : Response.Listener<String> {
                override fun onResponse(response: String) {
                    createArray(response);
                }
            },
            Response.ErrorListener() {
                fun onErrorResponse(error: VolleyError) {
                    Log.d("error", error.toString())
                }
            }) {

            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>();
                params["username"] = username
                return params
            }
        }


        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)

    }


    fun goToGamePage(team1: String, team2: String) {
        val intent = Intent(this, GamePage::class.java)
        intent.putExtra("username", team1)
        intent.putExtra("team2", team2)
        startActivity(intent)
    }


    /**
     * @pre existing teams for the user
     * @param teams: a string of the team name
     * @post creates the spinner array with the names of the teams
     */
    fun createArray(teams: String) {
        var count = 0
        for (index in teams.indices) {
            if (teams[index] == '.') {
                count++
            } else if (teams[index] != '.') {
                userTeamList[count] += teams[index].toString()
            }
        }
        val spin = findViewById<Spinner>(R.id.userTeamSpinner)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, userTeamList)
        spin.adapter = arrayAdapter

    }

    /**
     * @pre an active user
     * @param username: the active username
     * @post fetches all teams the user is on
     */

    fun getAllTeams(username: String) {
        val request = object : StringRequest(
            Request.Method.POST,
            "https://people.eecs.ku.edu/~h961c228/getAllOtherTeams.php",
            object : Response.Listener<String> {
                override fun onResponse(response: String) {
                   // Toast.makeText(this@StartGame, response, Toast.LENGTH_LONG).show()
                    createAllTeamArray(response);
                }
            },
            Response.ErrorListener() {
                fun onErrorResponse(error: VolleyError) {
                    Log.d("error", error.toString())
                }
            }) {

            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>();
                params["username"] = username
                return params
            }
        }
        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    }

    /**
     * @pre existing teams
     * @param teams: a string of team names
     * @post fills the other teams spinner array
     */
    fun createAllTeamArray(teams: String) {
        var count = 0;
        for (index in teams.indices) {
            if (teams[index] == '.') {
                count++
            } else if (teams[index] != '.') {
                otherTeamList[count] += teams[index].toString()
            }
        }
        val spin = findViewById<Spinner>(R.id.otherTeamSpinner)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, otherTeamList)
        spin.adapter = arrayAdapter

    }
}
