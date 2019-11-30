package com.example.squadup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
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

    /**
     * @pre At least one member
     * @param teamName: The name of the team to be created
     * @param member1: The first member of the team
     * @post creates a team with the provided member
     */
    fun registerTeam(teamName: String, member1: String){
        val username = intent.getStringExtra("username")
        val returnToProfile = Intent(this, profileActivity::class.java)
        returnToProfile.putExtra("username", username) //putting username into profile activity return to prevent crash due to null name
        val request = object: StringRequest(Request.Method.POST, "https://people.eecs.ku.edu/~h961c228/teamRegisterBackend.php", object : Response.Listener<String> {
            override fun onResponse(response: String) {
                Toast.makeText(this@RegisterTeam, "Your Team ID: " + response, Toast.LENGTH_LONG).show()
                startActivity(returnToProfile) //Returns user to profile page after registering team
                if(response == "Failed"){
                    //failure
                }
            }
        }, Response.ErrorListener() {
            fun onErrorResponse(error: VolleyError) { //function required for StringResponse. Never Used
                Log.d("error", error.toString())
            }
        }){
            /**
             * @pre a member and a team name
             * @post adds member and teamName to hash map
             */
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
