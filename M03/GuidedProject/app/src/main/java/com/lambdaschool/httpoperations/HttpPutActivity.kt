package com.lambdaschool.httpoperations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HttpPutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_get)
        title = "Put Request: Update existing Employee Steve"

        // TODO: Create the API object
        updateEmployee()
    }

    private fun updateEmployee(){
        // TODO: Write the call to update an employee
    }

}
