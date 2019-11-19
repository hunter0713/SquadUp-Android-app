package com.example.squadup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.content_main.*

class CreateTeam : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_team)
        val intent = getIntent()
        val username = intent.getStringExtra("username")
        var teamName = findViewById<EditText>(R.id.createTeamName)
        teamCreate(teamName.text.toString(),username);
    }

    fun teamCreate(teamName: String, member1: String){
        val request = object: StringRequest(Request.Method.POST, "https://people.eecs.ku.edu/~h961c228/teamRegisterBackend.php", object : Response.Listener<String> {
            override fun onResponse(response: String) {
                Toast.makeText(this@CreateTeam, response, Toast.LENGTH_LONG).show()
                if(response != "failure"){
                    //display team id(response)
                }
            }
        }, Response.ErrorListener() {
            fun onErrorResponse(error: VolleyError) { //function required for StringResponse
                Log.d("error", error.toString())
            }
        }){

            override fun getParams(): Map<String, String> {
                val params = HashMap<String,String>()
                params["teamName"]= teamName
                params["member1"]= member1
                return params
            }
        }



        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    }
}
