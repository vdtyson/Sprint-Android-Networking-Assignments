package com.versilistyson.networking_module_3

import android.icu.text.CaseMap
import com.versilistyson.networking_module_3.Employee
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceHolderApi {
    @GET("vemployees")
    fun getEmployees(): Call<List<Employee>>

    @GET("vemployees/{id}")
    fun getEmployees(@Path("id") employeeId: String)
}
