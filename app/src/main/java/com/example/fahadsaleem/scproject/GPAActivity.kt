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
import android.graphics.Paint
import android.graphics.Typeface
import android.support.v7.widget.CardView
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import org.w3c.dom.Text
import android.graphics.Paint.UNDERLINE_TEXT_FLAG




class GPAActivity : AppCompatActivity() {

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



        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val userNumber = preferences.getString("userNumber", "")
    //    Toast.makeText(this@AttendanceActivity,"was called in main, number= " +userNumber,Toast.LENGTH_LONG).show()

        val client = AsyncHttpClient()
        client.get("https://sixbee-db7e7.firebaseio.com/users/user"+userNumber+".json", object : JsonHttpResponseHandler() {

            override fun onStart() {
                // called before request is started
            }

            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {

                //val userJSONObject = response.getJSONObject(response.names().getString(i))

                val userCGPA = response.getString("CGPA")
                val semesterOne = response.getString("gpaSemester1")
                val semesterTwo = response.getString("gpaSemester2")
                val semesterThree = response.getString("gpaSemester3")





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



                    val cardView = CardView(this@GPAActivity)
                    val cardViewParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    cardViewParams.setMargins(16, 16, 16, 8)
                    cardView.layoutParams=cardViewParams

                    val LL = LinearLayout(this@GPAActivity)
                    LL.orientation=LinearLayout.VERTICAL
                    LL.layoutParams = cardViewParams



              //     val individualCourseObject = coursesJSONObject.getJSONObject(coursesJSONObject.names().getString(i))

                    val heading = TextView(this@GPAActivity)
                    val CGPA = TextView(this@GPAActivity)
                    val one = TextView(this@GPAActivity)
                    val lineUnderneath = TextView(this@GPAActivity)
                    val two = TextView(this@GPAActivity)
                    val three= TextView(this@GPAActivity)
                    //val courseAttendance = TextView(this@AttendanceActivity)
                    //val courseAbsents = TextView(this@AttendanceActivity)


                    val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    params.setMargins(10, 10, 10, 10)


                    CGPA.text = "Current CGPA = " + userCGPA
                    CGPA.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F);
                    CGPA.setTextColor(resources.getColor(R.color.colorPrimary))
                    CGPA.setLayoutParams(params)

                    one.text = "First Semester = " + semesterOne
                    one.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F);
                    one.setTextColor(resources.getColor(R.color.colorPrimary))
                    one.setLayoutParams(params)

                    two.text ="Second Semester = " + semesterTwo
                    two.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F);
                    two.setTextColor(resources.getColor(R.color.colorPrimary))
                    two.setLayoutParams(params)

                    three.text ="Third Semester = " + semesterThree
                    three.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F);
                    three.setTextColor(resources.getColor(R.color.colorPrimary))
                    three.setLayoutParams(params)

                    heading.setText("GPA Information")
                    heading.setTextSize(TypedValue.COMPLEX_UNIT_SP,22F);
                    heading.setTypeface(CGPA.getTypeface(), Typeface.BOLD_ITALIC)
                    heading.setTextColor(resources.getColor(R.color.colorPrimary))
                    heading.setLayoutParams(params)

                    lineUnderneath.setText("Click here for viewing each courses's grades")
                    lineUnderneath.setTextSize(TypedValue.COMPLEX_UNIT_SP,14F);
                    lineUnderneath.setTypeface(CGPA.getTypeface(), Typeface.BOLD)
                    lineUnderneath.setTextColor(resources.getColor(R.color.colorPrimary))
                    lineUnderneath.setLayoutParams(params)
                    lineUnderneath.setPaintFlags(lineUnderneath.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

                lineUnderneath.setOnClickListener {
                    startActivity(Intent(this@GPAActivity, AttendanceActivity::class.java))

                }


/*
                    courseAttendance.setText("Attendance: " +individualCourseObject.getString("courseAttendance") + "%")
                    courseAttendance.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F);
                    courseAttendance.setTextColor(resources.getColor(R.color.colorPrimary))
                    courseAttendance.setLayoutParams(params)

                    courseAbsents.setText("Absents: " +individualCourseObject.getString("absents"))
                    courseAbsents.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F);
                    courseAbsents.setTextColor(resources.getColor(R.color.colorPrimary))
                    courseAbsents.setLayoutParams(params)

*/
                    // LL.addView(courseName)
                    LL.addView(CGPA)
                    LL.addView(one)
                    LL.addView(two)
                    LL.addView(three)


                    cardView.addView(LL)
                    rootLinearLayout.addView(heading)
                    rootLinearLayout.addView(cardView)
                rootLinearLayout.addView(lineUnderneath)








            }
            override fun onSuccess(statusCode: Int, headers: Array<Header>, timeline: JSONArray) {
             //   Toast.makeText(this@AttendanceActivity,"was called in timeline, get this = " + timeline.get(0).toString(),Toast.LENGTH_LONG).show()
                // called when response HTTP status is "200 OK"
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, throwable: Throwable?, errorResponse: JSONArray?) {
                super.onFailure(statusCode, headers, throwable, errorResponse)
            }





        })

        val button = findViewById<Button>(R.id.main_nav_placeOrder)
        button.setOnClickListener {
            startActivity(Intent(this@GPAActivity, MainActivity::class.java))

        }

        val button2 = findViewById<Button>(R.id.main_nav_orderList)
        button2.setOnClickListener {
            startActivity(Intent(this@GPAActivity, AttendanceActivity::class.java))

        }




    }

}

//}
