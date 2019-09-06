package com.versilistyson.networking_module_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        get_picker_button.setOnClickListener {
            val type = intent.getStringExtra("get")
            if (type == "simple") {
                title = "GET - Simple Request"
            } else if (type == "path") {
                title = "GET - Path Parameter: EmployeeId - 1"
                getEmployees("1")
            }
            else{
                title = "GET - Query Parameter: Age - 55"
                getEmployeesForAge("55")
            }
        }
        }
    }
private fun getEmployees(){
   getEmployees()
}

private fun getEmployees(employeeId: String){
    // TODO: Write the call to get an employee by id
}

private fun getEmployeesForAge(age: String){
    // TODO: Write the call to get an employee by age
}

