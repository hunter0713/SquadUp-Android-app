package com.example.squadup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_profile.*

class profileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val intent = getIntent()
        val username = intent.getStringExtra("username")
        val title = findViewById<TextView>(R.id.profileTitle)
        title.text = "Welcome, " + username + "!"
        getRecord(username)
        profileGoToTeamPage.setOnClickListener{
            val intent = Intent(this, RegisterTeam::class.java)
            intent.putExtra("username",username)
            startActivity(intent)
        }
        goToTeamPage.setOnClickListener{
            val intent = Intent(this,TeamPage::class.java)
            startActivity(intent)
        }
        profileJoinTeamButton.setOnClickListener{
            val intent = Intent(this,JoinTeam::class.java)
            intent.putExtra("username",username)
            startActivity(intent)
        }
        profileStartGame.setOnClickListener{
            val intent = Intent(this,StartGame::class.java)
            intent.putExtra("username",username)
            startActivity(intent)
        }
    }
    fun getRecord(username: String){
        val request = object: StringRequest(Request.Method.POST, "https://people.eecs.ku.edu/~h961c228/profileGetUserRecord.php", object : Response.Listener<String> {
            override fun onResponse(response: String) {
                //Toast.makeText(this@profileActivity, response, Toast.LENGTH_LONG).show()
                    postRecord(response);
            }
        }, Response.ErrorListener() {
            fun onErrorResponse(error: VolleyError) { //function required for StringResponse
                Log.d("error", error.toString())
            }
        }){

            override fun getParams(): Map<String, String> {
                val params = HashMap<String,String>()
                params["username"]= username
                return params
            }
        }



        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    }
    fun postRecord(record: String){
        val winsRec = findViewById<TextView>(R.id.profileWins)
        val lossRec = findViewById<TextView>(R.id.profileLosses)
        var Winscore = "";
        var LossScore = "";
        var flag =0;
        for(index in record.indices){
            if(record[index] != '.' && flag == 0){
                Winscore += record[index];
            }
            if(record[index] == '.'){
                flag = 1;
            }
            if(record[index] != '.' && flag == 1){
                LossScore += record[index]
            }
        }
        winsRec.text = Winscore
        lossRec.text = LossScore
    }
}
