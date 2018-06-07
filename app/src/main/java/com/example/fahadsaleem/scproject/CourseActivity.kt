package com.example.fahadsaleem.scproject

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONArray
import org.json.JSONObject

class CourseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)

        val courseName  = findViewById<EditText>(R.id.courseName)
        val creditHours  = findViewById<EditText>(R.id.creditHours)
        val courseID  = findViewById<EditText>(R.id.courseID)
        val consultingHours  = findViewById<EditText>(R.id.consultingHours)
        val labAssistant  = findViewById<EditText>(R.id.labAssistant)

        val buttonAdd = findViewById<FloatingActionButton>(R.id.course_add_button)

        buttonAdd.setOnClickListener {
            if (!courseName.text.isEmpty() && !creditHours.text.isEmpty() && !courseID.text.isEmpty() && !consultingHours.text.isEmpty()
            && !labAssistant.text.isEmpty()){
                val jdata1 = JSONObject()
                try {
                    jdata1.put("consultingDays", consultingHours.text)
                    jdata1.put("courseCreditHours", creditHours.text)
                    jdata1.put("courseID",courseID.text)
                    jdata1.put("courseName",courseName.text)
                    jdata1.put("labAssistant",labAssistant.text)
                } catch (ex: Exception) {
                    // json exception
                }

                val entity =  StringEntity(jdata1.toString());
                val url = "https://sixbee-db7e7.firebaseio.com/users/"
                val preferences = PreferenceManager.getDefaultSharedPreferences(this)
                val userNumber = preferences.getString("userNumber", "")

                val client = AsyncHttpClient()

                client.get(url+"user" + userNumber +"/courses.json", object : JsonHttpResponseHandler() {

                    override fun onStart() {
                        // called before request is started
                    }


                    override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
                        val editor = preferences.edit()
                        editor.putString("id",Integer.toString(response.names().length()+1))
                        Toast.makeText(this@CourseActivity,Integer.toString(response.names().length()+1),Toast.LENGTH_SHORT).show()
                        editor.apply()

                    }
                })



                client.put(this@CourseActivity, url + "user" + userNumber +"/courses/course" + preferences.getString("id","") + ".json",entity,"application/json", object: JsonHttpResponseHandler(){

                    override fun onStart() {
                        // called before request is started
                    }

                     override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
                       Toast.makeText(this@CourseActivity,"Course Added Succesfully",Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@CourseActivity, AttendanceActivity::class.java))


                    }







                })
            }
        }
    }
}