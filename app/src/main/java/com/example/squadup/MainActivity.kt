package com.example.squadup
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
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
            var username = findViewById<EditText>(R.id.editText2)
            var password = findViewById<EditText>(R.id.editText3)
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



    fun sayHello(view: View)
    {
        val helloToast = Toast.makeText(this, "Hello SquadUp User!", Toast.LENGTH_LONG)
        helloToast.show()
    }

    fun goToProfile(username: String)
    {
        val intent = Intent(this, profileActivity::class.java)
        intent.putExtra("username",username);
        startActivity(intent)
    }
    //LOGIN FUNCTION FOR DATABASE STUFF
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


