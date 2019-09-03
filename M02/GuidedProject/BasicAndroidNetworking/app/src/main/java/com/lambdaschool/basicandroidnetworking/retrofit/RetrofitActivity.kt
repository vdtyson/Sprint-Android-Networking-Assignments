package com.lambdaschool.basicandroidnetworking.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lambdaschool.basicandroidnetworking.R
import kotlinx.android.synthetic.main.activity_retrofit.*

// TODO: declare this activity handles Retrofit callbacks
class RetrofitActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "RETROFIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        fetchNetworkAPIRetrofitButton.setOnClickListener {

            // TODO: Get advice without logging
        }

        fetchNetworkAPIOkHttpButton.setOnClickListener {
            //TODO: Get advice with logging
        }
    }

    // TODO: Define callback for Retrofit onResponse

    // TODO: Define callback for Retrofit onFailure
}
