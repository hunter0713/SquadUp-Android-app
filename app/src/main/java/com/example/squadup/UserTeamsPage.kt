package com.example.squadup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_user_teams_page.*

class UserTeamsPage : AppCompatActivity() {
    var userTeamList =
        arrayOf<String>("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
    var userTeamName = "";
    var teamID = "";
    var teamWins = ""
    var teamLosses = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_teams_page)
        val intent = getIntent()
        val username = intent.getStringExtra("username")
        getUserTeams(username)
        showTeamStats.setOnClickListener{
           var teamName = findViewById<Spinner>(R.id.allUserTeamsSpinner).getSelectedItem().toString()
            getTeamStats(teamName)
        }
    }
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
    fun createArray(teams: String) {
        var count = 0;
        for (index in teams.indices) {
            if (teams[index] == '.') {
                count++
            } else if (teams[index] != '.') {
                userTeamList[count] += teams[index].toString()
            }
        }
        val spin = findViewById<Spinner>(R.id.allUserTeamsSpinner)
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, userTeamList)
        spin.adapter = arrayAdapter

    }
    fun getTeamStats(teamName: String){
        val request = object : StringRequest(
            Request.Method.POST,
            "https://people.eecs.ku.edu/~h961c228/getUserTeamStats.php",
            object : Response.Listener<String> {
                override fun onResponse(response: String) {
                    //Toast.makeText(this@UserTeamsPage, response, Toast.LENGTH_LONG).show()
                    createData(response);
                }
            },
            Response.ErrorListener() {
                fun onErrorResponse(error: VolleyError) {
                    Log.d("error", error.toString())
                }
            }) {

            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>();
                params["teamName"] = teamName
                return params
            }
        }


        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)

    }
    fun createData(data: String){
        var flag = 0;
        userTeamName = ""
        teamID = ""
        teamWins = ""
        teamLosses = ""
        for(index in data.indices){
            if(data[index] == '.'){
                flag = flag + 1
            }
            else if(data[index] != '.' && flag == 0){
                userTeamName += data[index]
            }
            else if(data[index] != '.' && flag == 1){
                teamID += data[index]
            }
            else if(data[index] != '.' && flag == 2){
                teamWins += data[index]
            }
            else if(data[index] != '.' && flag == 3){
                teamLosses += data[index]
            }
        }
        var UserTeamViewName = findViewById<TextView>(R.id.userTeamNameTeamPage)
        UserTeamViewName.text = userTeamName
        var userTeamViewID = findViewById<TextView>(R.id.userTeamID)
        userTeamViewID.text = "Team Code: " + teamID
        var userTeamViewWins = findViewById<TextView>(R.id.userTeamWins)
        userTeamViewWins.text = "Wins: " + teamWins
        var userTeamViewLosses = findViewById<TextView>(R.id.userTeamLosses)
        userTeamViewLosses.text = "Losses: " + teamLosses
    }


}
