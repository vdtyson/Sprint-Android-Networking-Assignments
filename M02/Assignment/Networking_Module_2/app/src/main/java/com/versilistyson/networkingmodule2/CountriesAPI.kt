package com.versilistyson.networkingmodule2

import retrofit2.Call
import retrofit2.http.GET

interface CountriesAPI {
    var region: Regions
    @GET("oceania")
    fun getCountries(): Call<OceaniaCountryList>
}