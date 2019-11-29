package com.example.squadup
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        loginButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.usernameLogin)
            val password = findViewById<EditText>(R.id.password)
            login(username.text.toString(), password.text.toString())


        }
        clickToRegister.setOnClickListener{
            val intent = Intent(this, registerPage::class.java)
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * @pre None
     * @param username: A string containing the active username
     * @post Returns you to the profile page activity
     */
    fun goToProfile(username: String)
    {
        val intent = Intent(this, profileActivity::class.java)
        intent.putExtra("username",username)
        startActivity(intent)
    }
    //LOGIN FUNCTION FOR DATABASE STUFF
    /**
     * @pre None
     * @param username: the entered username
     * @param password: the entered password
     * @post Checks if the user exists in the SQL database and redirects them if so
     */
    fun login(username: String, password: String){
        val request = object: StringRequest(Request.Method.POST, "https://people.eecs.ku.edu/~h961c228/loginExistingBackend.php", object : Response.Listener<String> {
            override fun onResponse(response: String) {
                Toast.makeText(this@MainActivity, response, Toast.LENGTH_LONG).show()
                if(response == "Success"){
                    goToProfile(username);
                }
            }
        }, Response.ErrorListener() {
            fun onErrorResponse(error: VolleyError) { //function required for StringResponse
                Log.d("error", error.toString())
            }
        }){

            override fun getParams(): Map<String, String> {
                val params = HashMap<String,String>()
                params["username"]= username
                params["password"]= password
                return params
            }
        }



        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(request)
    }
}


