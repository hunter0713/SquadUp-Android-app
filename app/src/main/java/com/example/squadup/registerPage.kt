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
import kotlinx.android.synthetic.main.activity_register_page.*

class registerPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
        registerButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.registerUsername)
            val password = findViewById<EditText>(R.id.registerPagePass1)
            val passwordVerify = findViewById<EditText>(R.id.registerPagePass2)
            if (password.text.toString() == passwordVerify.text.toString()) {
                registerUser(username.text.toString(), password.text.toString())
            } else {
                Toast.makeText(this, "Passwords Don't Match :(", Toast.LENGTH_LONG).show()
            }
        }
    }
fun goToProfile(){
    val intent = Intent(this, profileActivity::class.java)
    intent.putExtra("username",findViewById<EditText>(R.id.registerUsername).text.toString())
    startActivity(intent)
}
    fun registerUser(username: String, password: String) {
        val request = object : StringRequest(
            Request.Method.POST,
            "https://people.eecs.ku.edu/~h961c228/registerBackend.php",
            object : Response.Listener<String> {
                override fun onResponse(response: String) {
                    Toast.makeText(this@registerPage, response, Toast.LENGTH_LONG).show()
                    if (response == "success") {
                        goToProfile()
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
                params["username"] = username     //here
                params["password"] = password  //here
                return params
            }
        }


        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)

    }
}
