package com.example.fahadsaleem.scproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.entity.StringEntity
import android.preference.PreferenceManager
import android.content.SharedPreferences
import android.graphics.Typeface
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.CardView
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView


class AttendanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)
        //val button = findViewById<Button>(R.id.button)
        //button.setOnClickListener {

        /*  val params = RequestParams()
          val jdata1 = JSONObject()
          try {
              jdata1.put("courseName", "Software Construction")
              jdata1.put("courseAttendance", "84.2")
              jdata1.put("courseGrade","72")
              jdata1.put("courseCreditHours","3+1")
              jdata1.put("presents","28")
              jdata1.put("absents","7")
          } catch (ex: Exception) {
              // json exception
          }

          val entity =  StringEntity(jdata1.toString());
          val url = "https://sixbee-db7e7.firebaseio.com/users/user3/courses/"

          val client = AsyncHttpClient()

          client.put(this@MainActivity, url+"course1.json",entity,"application/json", object: JsonHttpResponseHandler(){

              override fun onStart() {
                  // called before request is started
              }

              override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
                  Toast.makeText(this@MainActivity,"OnSuccessCalled with JSONObject",Toast.LENGTH_LONG).show()


              }
              override fun onSuccess(statusCode: Int, headers: Array<Header>, timeline: JSONArray) {
                  Toast.makeText(this@MainActivity,"OnSuccessCalled with JSONArray",Toast.LENGTH_LONG).show()
                  // called when response HTTP status is "200 OK"
              }






          })
*/

        val rootLinearLayout = findViewById<LinearLayout>(R.id.rootLayout)

        val floatingButton = findViewById<FloatingActionButton>(R.id.course_add_button)

        floatingButton.setOnClickListener {
            startActivity(Intent(this@AttendanceActivity, CourseActivity::class.java))


        }


        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val userNumber = preferences.getString("userNumber", "")
       // Toast.makeText(this@AttendanceActivity,"was called in main, number= " +userNumber,Toast.LENGTH_LONG).show()

        val client = AsyncHttpClient()
        client.get("https://sixbee-db7e7.firebaseio.com/users/user"+userNumber+".json", object : JsonHttpResponseHandler() {

            override fun onStart() {
                // called before request is started
            }

            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {

                //val userJSONObject = response.getJSONObject(response.names().getString(i))
//                val userName = response.getString("name")
  //              val userClass = response.getString("class")
    //            val userBatch = response.getString("batch")
      //          val userCGPA = response.getString("CGPA")


                val coursesJSONObject = response.getJSONObject("courses")


/*
                val activityMainProfileName =  findViewById<TextView>(R.id.activity_main_profile_name)
                activityMainProfileName.append(userName)

                val activityMainClass = findViewById<TextView>(R.id.activity_main_profile_class)
                activityMainClass.append(userClass)

                val activityMainCGPA = findViewById<TextView>(R.id.activity_main_profile_cgpa)
                activityMainCGPA.append(userCGPA)

                val activityMainBatch = findViewById<TextView>(R.id.activity_main_profile_batch)
                activityMainBatch.append(userBatch)

                val activityMainCourses = findViewById<TextView>(R.id.activity_main_courses_total)
                activityMainCourses.append(coursesJSONObject.names().length().toString())

                val activityMainCreditHours = findViewById<TextView>(R.id.activity_main_courses_credithours)
                activityMainCreditHours.append("10")

                */


                for (i in 0 until coursesJSONObject.names().length()){

                    val cardView = CardView(this@AttendanceActivity)
                    val cardViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    cardViewParams.setMargins(16, 16, 16, 8)
                    cardView.layoutParams=cardViewParams

                    val LL = LinearLayout(this@AttendanceActivity)
                    LL.orientation=LinearLayout.VERTICAL
                    LL.layoutParams = cardViewParams



                    val individualCourseObject = coursesJSONObject.getJSONObject(coursesJSONObject.names().getString(i))

                    val courseName = TextView(this@AttendanceActivity)
                    val courseCreditHours = TextView(this@AttendanceActivity)
                    val labAttendant = TextView(this@AttendanceActivity)
                  //  val coursePresents= TextView(this@AttendanceActivity)
                    val courseID = TextView(this@AttendanceActivity)
                    val consultingDays = TextView(this@AttendanceActivity)


                    val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    params.setMargins(10, 10, 10, 10)


                    courseName.setText(individualCourseObject.getString("courseName"))
                    courseName.setTextSize(TypedValue.COMPLEX_UNIT_SP,22F);
                    courseName.setTypeface(courseName.getTypeface(), Typeface.BOLD_ITALIC)
                    courseName.setTextColor(resources.getColor(R.color.colorPrimary))
                    courseName.setLayoutParams(params)

                    courseCreditHours.setText("Credit Hours: " +individualCourseObject.getString("courseCreditHours"))
                    courseCreditHours.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F);
                    courseCreditHours.setTextColor(resources.getColor(R.color.colorPrimary))
                    courseCreditHours.setLayoutParams(params)

                    labAttendant.setText("Lab Attendant: " +individualCourseObject.getString("labAssistant"))
                    labAttendant.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F);
                    labAttendant.setTextColor(resources.getColor(R.color.colorPrimary))
                    labAttendant.setLayoutParams(params)

                    courseID.setText("Course ID: " +individualCourseObject.getString("courseID"))
                    courseID.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F);
                    courseID.setTextColor(resources.getColor(R.color.colorPrimary))
                    courseID.setLayoutParams(params)

                    consultingDays.setText("Consulting Days: " +individualCourseObject.getString("consultingDays"))
                    consultingDays.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F);
                    consultingDays.setTextColor(resources.getColor(R.color.colorPrimary))
                    consultingDays.setLayoutParams(params)


                   // LL.addView(courseName)
                    LL.addView(labAttendant)
                    LL.addView(courseCreditHours)
                    LL.addView(courseID)
                 //   LL.addView(coursePresents)
                    LL.addView(consultingDays)

                    cardView.addView(LL)
                    rootLinearLayout.addView(courseName)
                    rootLinearLayout.addView(cardView)

                }







            }
            override fun onSuccess(statusCode: Int, headers: Array<Header>, timeline: JSONArray) {
               // Toast.makeText(this@AttendanceActivity,"was called in timeline, get this = " + timeline.get(0).toString(),Toast.LENGTH_LONG).show()
                // called when response HTTP status is "200 OK"
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, throwable: Throwable?, errorResponse: JSONArray?) {
                super.onFailure(statusCode, headers, throwable, errorResponse)
            }





        })

        val button = findViewById<Button>(R.id.main_nav_placeOrder)
        button.setOnClickListener {
            startActivity(Intent(this@AttendanceActivity, MainActivity::class.java))

        }

        val button2 = findViewById<Button>(R.id.main_nav_yourOrders)
        button2.setOnClickListener {
            startActivity(Intent(this@AttendanceActivity, GPAActivity::class.java))

        }




    }

}

//}
