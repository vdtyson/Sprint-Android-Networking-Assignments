package com.lambdaschool.httpoperations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HttpGetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_get)

        // TODO: Create the api
        val type = intent.getStringExtra("get")
        if (type == "simple") {
            title = "GET - Simple Request"
            getEmployees()
        } else if (type == "path") {
            title = "GET - Path Parameter: EmployeeId - 1"
            getEmployees("1")
        }
        else{
            title = "GET - Query Parameter: Age - 55"
            getEmployeesForAge("55")
        }
    }

    private fun getEmployees(){
        // TODO: Write the call for getting all employees
    }

    private fun getEmployees(employeeId: String){
        // TODO: Write the call to get an employee by id
    }

    private fun getEmployeesForAge(age: String){
        // TODO: Write the call to get an employee by age
    }

}
