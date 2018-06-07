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
import android.opengl.Visibility
import android.support.design.widget.FloatingActionButton
import android.view.View


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


        val floatingButton = findViewById<FloatingActionButton>(R.id.course_add_button)

        floatingButton.visibility = View.GONE
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

                val departmentName = response.getString("department")
                val campus = response.getString("campus")
                val salary = response.getString("salary")
                val designation = response.getString("designation")
                val residence = response.getString("residence")





               // val coursesJSONObject = response.getJSONObject("courses")


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
                    val department = TextView(this@GPAActivity)
                    val campusName = TextView(this@GPAActivity)
                    val residenceTV = TextView(this@GPAActivity)
                    val salaryAmount = TextView(this@GPAActivity)
                    val designationName= TextView(this@GPAActivity)
                    //val courseAttendance = TextView(this@AttendanceActivity)
                    //val courseAbsents = TextView(this@AttendanceActivity)


                    val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    params.setMargins(10, 10, 10, 10)


                    department.text = "Department: " + departmentName
                    department.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F);
                    department.setTextColor(resources.getColor(R.color.colorPrimary))
                    department.setLayoutParams(params)

                    campusName.text = "Campus: " + campus
                    campusName.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F);
                    campusName.setTextColor(resources.getColor(R.color.colorPrimary))
                    campusName.setLayoutParams(params)

                    residenceTV.text = "Residence: " + residence
                    residenceTV.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F)
                    residenceTV.setTextColor(resources.getColor(R.color.colorPrimary))
                    residenceTV.setLayoutParams(params)

                    salaryAmount.text ="Salary: " + salary
                    salaryAmount.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F);
                    salaryAmount.setTextColor(resources.getColor(R.color.colorPrimary))
                    salaryAmount.setLayoutParams(params)

                    designationName.text ="Designation: " + designation
                    designationName.setTextSize(TypedValue.COMPLEX_UNIT_SP,17F);
                    designationName.setTextColor(resources.getColor(R.color.colorPrimary))
                    designationName.setLayoutParams(params)

                    heading.setText("Profile and Department Info")
                    heading.setTextSize(TypedValue.COMPLEX_UNIT_SP,22F);
                    heading.setTypeface(department.getTypeface(), Typeface.BOLD_ITALIC)
                    heading.setTextColor(resources.getColor(R.color.colorPrimary))
                    heading.setLayoutParams(params)
/*
                    salary.setText("Click here for viewing each courses's grades")
                    salary.setTextSize(TypedValue.COMPLEX_UNIT_SP,14F);
                    salary.setTypeface(department.getTypeface(), Typeface.BOLD)
                    salary.setTextColor(resources.getColor(R.color.colorPrimary))
                    salary.setLayoutParams(params)
                    salary.setPaintFlags(salary.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)

                salary.setOnClickListener {
                    startActivity(Intent(this@GPAActivity, AttendanceActivity::class.java))

                }

*/
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
                    LL.addView(department)
                    LL.addView(campusName)
                    LL.addView(salaryAmount)
                    LL.addView(designationName)
                LL.addView(residenceTV)


                    cardView.addView(LL)
                    rootLinearLayout.addView(heading)
                    rootLinearLayout.addView(cardView)
             //   rootLinearLayout.addView(salary)








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
