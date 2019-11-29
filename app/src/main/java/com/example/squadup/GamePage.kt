package com.example.squadup
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class GamePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page)
        val teamIntent: Intent = getIntent()
        val team1 = teamIntent.getStringExtra("userTeam")
        val team2 = teamIntent.getStringExtra("otherTeam")
        val username = teamIntent.getStringExtra("username")
        var userTeamButton = findViewById<Button>(R.id.userTeamWinsButton)
        userTeamButton.text = team1
        var otherTeamButton = findViewById<Button>(R.id.otherTeamWinsButton)
        otherTeamButton.text = team2
        userTeamButton.setOnClickListener{
            updateWinnerRecords(username,team1,team2)
            updateLoserRecords(username,team1,team2)
        }
        otherTeamButton.setOnClickListener{
            updateWinnerRecords(username,team2,team1)
            updateLoserRecords(username,team2,team1)
        }

    }

    /**
     * @pre None
     * @param username: A string containing the active username
     * @post Returns you to the profile page activity
     */
    fun goToProfile(username: String){
        val intent = Intent(this, profileActivity::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
    }
    /**
     * @pre Two existing teams in a game
     * @param Username: a string of the active username
     * @param winningTeam: the name of the team who won
     * @param loserTeam: the name of the team who lost
     * @post Updates the winners record
     */
    fun updateWinnerRecords(username: String, winningTeam: String, loserTeam: String) {
        val request = object : StringRequest(
            Request.Method.POST,
            "https://people.eecs.ku.edu/~h961c228/updateWinnersAfterGame.php",
            object : Response.Listener<String> {
                override fun onResponse(response: String) {
                    //Toast.makeText(this@GamePage, response, Toast.LENGTH_LONG).show()
                    goToProfile(username)

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
                params["winningTeam"] = winningTeam
                params["loserTeam"] = loserTeam
                return params
            }
        }


        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)

    }
    /**
     * @pre two existing teams in a game
     * @param Username: a string of the active username
     * @param winningTeam: the name of the team who won
     * @param loserTeam: the name of the team who lost
     * @post updates the losers record
     */
    fun updateLoserRecords(username: String, winningTeam: String, loserTeam: String) {
        val request = object : StringRequest(
            Request.Method.POST,
            "https://people.eecs.ku.edu/~h961c228/updateLosersAfterGame.php",
            object : Response.Listener<String> {
                override fun onResponse(response: String) {
                    Toast.makeText(this@GamePage, response, Toast.LENGTH_LONG).show()
                    goToProfile(username)

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
                params["winningTeam"] = winningTeam
                params["loserTeam"] = loserTeam
                return params
            }
        }


        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    }


    }
