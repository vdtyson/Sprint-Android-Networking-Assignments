package com.versilistyson.networkingmodule2

import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), Callback<OceaniaCountryList> {
    override fun onFailure(call: Call<OceaniaCountryList>, t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(this, "The call failed", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<OceaniaCountryList>, response: Response<OceaniaCountryList>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        if (response.isSuccessful) {
            var oceaniaCountryList = response.body()
            countries_tv.text = "${oceaniaCountryList.toString()}"
        } else {
        }
    }

    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetch_countries_bttn.setOnClickListener {
           val retriever = OceaniaCountriesRetriever()
            retriever.getOceaniaCountries().enqueue(this)
        }

    }
}


