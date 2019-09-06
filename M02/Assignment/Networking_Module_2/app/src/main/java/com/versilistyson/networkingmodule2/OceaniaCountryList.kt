package com.versilistyson.networkingmodule2


import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

enum class Regions(val region: String) {
     Oceania("oceania"),Europe("europe"),Asia("asia"),Americas("americas"),Africa("africa")
}

data class OceaniaCountryList(var countryList: List<CountryData>)