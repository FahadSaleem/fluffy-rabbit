package com.example.fahadsaleem.scproject


import android.content.Intent
import com.loopj.android.http.*;
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.AsyncHttpClient
import android.R.id.edit
import android.content.SharedPreferences
import android.preference.PreferenceManager





class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginButton = findViewById<Button>(R.id.activity_login_button_login)
        val clientUsername = findViewById<TextView>(R.id.activity_login_username)
        val clientPassword = findViewById<TextView>(R.id.activity_login_password)

        loginButton.setOnClickListener {
            val client = AsyncHttpClient()
            client.get("https://sixbee-db7e7.firebaseio.com/users.json", object : JsonHttpResponseHandler() {

                override fun onStart() {
                    // called before request is started
                }

                override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
                    for (i in 0 until response.names().length()) {

                        val userJSONObject = response.getJSONObject(response.names().getString(i))
                        val username = userJSONObject.getString("username")
                        val password = userJSONObject.getString("password")

                        if (username==clientUsername.text.toString().trim()&& password==clientPassword.text.toString().trim()){
                           // Toast.makeText(this@LoginActivity,"Validated", Toast.LENGTH_LONG).show()
                            val preferences = PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
                            val editor = preferences.edit()
                            editor.putString("userNumber",Integer.toString(i+1))
                            editor.apply()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))

                        }
                        else {
                            Toast.makeText(this@LoginActivity,"Not Validated", Toast.LENGTH_LONG).show()
                        }


                    }

                }
                override fun onSuccess(statusCode: Int, headers: Array<Header>, timeline: JSONArray) {
                   // Toast.makeText(this@LoginActivity,"was called in timeline, get this = " + timeline.get(0).toString(),Toast.LENGTH_LONG).show()
                    // called when response HTTP status is "200 OK"
                }





            })
        }


    }
}