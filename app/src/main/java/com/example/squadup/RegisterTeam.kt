package com.example.squadup

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
import kotlinx.android.synthetic.main.activity_register_page.*
import kotlinx.android.synthetic.main.activity_register_team.*

class RegisterTeam : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_team)
        val intent = getIntent()
        val username = intent.getStringExtra("username")
        registerTeamButton.setOnClickListener{
            val teamName = findViewById<EditText>(R.id.registerTeamName).text.toString()
            registerTeam(teamName,username)
        }
    }
    fun registerTeam(teamName: String, member1: String){
        val request = object: StringRequest(Request.Method.POST, "https://people.eecs.ku.edu/~h961c228/teamRegisterBackend.php", object : Response.Listener<String> {
            override fun onResponse(response: String) {
                Toast.makeText(this@RegisterTeam, response, Toast.LENGTH_LONG).show()
                if(response == "Failed"){
                    //display team id to user to give to other users.
                }
            }
        }, Response.ErrorListener() {
            fun onErrorResponse(error: VolleyError) { //function required for StringResponse
                Log.d("error", error.toString())
            }
        }){

            override fun getParams(): Map<String, String> {
                val params = HashMap<String,String>()
                params["member1"]= member1
                params["teamName"]= teamName
                return params
            }
        }



        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    }
}
