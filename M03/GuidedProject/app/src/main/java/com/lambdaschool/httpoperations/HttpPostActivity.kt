package com.lambdaschool.httpoperations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HttpPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_get)
        title = "Post Request: New Employee David"

        // TODO: create the API object
        addnewEmployee()
    }

    private fun addnewEmployee(){
        // TODO: Write the call to add a new employee
    }
}
