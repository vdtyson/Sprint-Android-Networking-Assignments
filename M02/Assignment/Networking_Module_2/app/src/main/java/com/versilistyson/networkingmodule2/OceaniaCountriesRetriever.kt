package com.versilistyson.networkingmodule2

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OceaniaCountriesRetriever {

    companion object {
        private const val Tag = "RETREIVER"
        internal const val BASE_URL = "https://restcountries.eu/rest/v2/region/"
    }
    fun getOceaniaCountries(): Call<OceaniaCountryList> {
        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val countriesAPI = retrofit.create(CountriesAPI::class.java)

        return countriesAPI.getCountries()
    }

}