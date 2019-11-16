package com.example.squadup

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register_page.*

class registerPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
        registerButton.setOnClickListener{
            var username = findViewById<EditText>(R.id.registerUsername)
            var password = findViewById<EditText>(R.id.registerPagePass1)
            var passwordVer = findViewById<EditText>(R.id.registerPagePass2)
            if(password.text.toString() == passwordVer.text.toString()){
                //call function to handle server-side stuff. Password is Verified
            }
            else{
                Toast.makeText(this,"Passwords Don't Match :(",Toast.LENGTH_LONG).show()
            }
        }
    }
}
