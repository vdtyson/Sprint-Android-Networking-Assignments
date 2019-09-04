package com.versilistyson.threadingassignment

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            MyAsyncTask()
        }
    }

    inner class MyAsyncTask : AsyncTask<Unit, Int, String>() {

        override fun onPostExecute(result: String?) {
            showProgressBar(false)
            txt_primes.text = result
            super.onPostExecute(result)

        }

        override fun onCancelled(result: String?) {
            super.onCancelled(result)
        }

        override fun onCancelled() {
            super.onCancelled()
        }

        override fun onPreExecute() {
            showProgressBar(true)
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Unit?): String {
            val primeNumbers = primes().take(16000).joinToString(", ")
            return primeNumbers
        }

    }

    override fun onStop() {
        super.onStop()
        MyAsyncTask().cancel(true)
    }

    fun showProgressBar(isVisible: Boolean) {
        when (isVisible) {
            true -> {
                progress_bar.visibility = View.VISIBLE
                txt_primes.visibility = View.INVISIBLE
            }
            else -> {
                progress_bar.visibility = View.INVISIBLE
                txt_primes.visibility = View.VISIBLE
            }
        }
    }
    fun primes(): Sequence<Long> {
        var i = 0L
        return sequence {
            generateSequence { i++ }
                .filter { n -> n > 1 && ((2 until n).none { i -> n % i == 0L }) }
                .forEach { yield(it) }
        }
    }
}


