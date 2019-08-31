# Basic Android Networking

#### CODING ASSIGNMENT: *Sprint 4, Module 2*

> Create an Android app that retrieves a list of all countries in the world found in the Oceania region.
> 
> 
> Using: Kotlin, Retrofit, OkHttp, GSON
> 
> HTTP API: [https://restcountries.eu](https://restcountries.eu/#api-endpoints-all)


## STEP 1 - Create Android Project

A) Using the Android Studio IDE, create a New -> Project

B) Start with an Empty Activity called `MainActivity`

C) Define your own package name

D) Other settings:

Minimum API: `22`

Language: `Kotlin`

True: `Use androidx.* artifacts`

E) Click `Finish` to create the project


## STEP 2 - Setup Initial UI

A) In `actvity_main.xml` add a Button to press when fetching the list of countries (called `fetchCountriesButton`)

B) In `actvity_main.xml` add a TextView to hold the results of the API call (called `countriesTextView`)

C) *EXTRA*: Use a ListView (or similar) to show these countries in a list format, instead of a TextView


## STEP 3 - Add Dependencies

A) In `AndroidManifest.xml` add a permission to check for Internet connectivity (see if network is available)

Name: `android.permission.ACCESS_NETWORK_STATE`

B) In `AndroidManifest.xml` add a permission to allow for Internet connections (to call an endpoint, etc.)

Name: `android.permission.INTERNET`

C) In `app/build.gradle`, add dependencies for:

GSON: `com.google.code.gson:gson:2.8.5`

Retrofit: `com.squareup.retrofit2:retrofit:2.5.0`, `com.squareup.retrofit2:converter-gson:2.5.0`

OkHttp: `com.squareup.okhttp3:okhttp:3.14.2`, `com.squareup.okhttp3:logging-interceptor:3.14.2`

D) Feel free to add other dependencies if desired, such as Material Design (`com.google.android.material:material:1.0.0`), etc

**NOTE**: the dependency **versions** may change at any time. The Android Studio IDE will highlight a dependency to indicate that a newer version may be available.


## STEP 4 - Create Countries Model

A) Familiarize yourself with the JSON results of the `restcountries.eu` endpoint.

In a browser, navigate to: [https://restcountries.eu/#api-endpoints-all](https://restcountries.eu/#api-endpoints-all)

**TIP**: To make JSON results easier to read, add a browser extension that automatically formats JSON results. For example, in Chrome, you can search for a "JSON Formatter" extension.

B) Find the endpoint that lists countries by REGION - specifically the **Ocieania** region.

C) Create a new **model** class to handle the results.

You can start with a kotlin data class that holds the highest-level results - a list of countries:

```
data class OceaniaCountryList {
	val country: List<OceaniaCountry> // define "OceaniaCountry" class soon...
}
```

Now, in the same file create another data class to hold the contents of each country result:

```
data class OceaniaCountry (
    val alpha2code: String,
    val capital: String,
    val subregion: String,
    // other fields here
)
```

Be sure to add the field that represents the name of the country!

If the JSON results of an API has extra fields that aren't defined in your model, they are simply ignored.


## STEP 5 - Create OceaniaCountriesAPI interface

A) Create an interface that contains the API URL and returns the results as the OceaniaCountryList model object:

```
interface CountriesAPI {

    @GET("<the last portion of your API URL>")
    fun getCountries(): Call<OceaniaCountryList>
    
}
```

NOTE: This function can be made more generic by passing in the "oceania" value as a paramter, so that this call can supprt any region.


## STEP 6 - Create OceaniaCountriesRetriever class

A) Create the Retrofit class that will be called by your Activity:

```
class OceaniaCountriesRetriever {

    fun getOceaniaCountries(): Call<OceaniaCountryList> {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val countriesAPI = retrofit.create(CountriesAPI::class.java)

        return countriesAPI.getCountries()
    }

}
```

**NOTE**: When parsing JSON using GSON, the `.setLenient()` method is a way to "relax" the parser. Wihtout this method, the default parsing is strict, and an error would occur if the returned JSON violated one of the JSON structure rules.


## STEP 7 - Add Handlers and 	Callbacks

A) Modify `MainActivity.kt` to implement a Retrofit **Callback**. This allows the Activity to override success and failure methods.

```
class MainActivity : AppCompatActivity(), Callback<OceaniaCountryList> {
```

B) Modify `MainActivity.kt` to handle the Button's onClick event using Kotlin Synthetics

```
import kotlinx.android.synthetic.main.activity_main.*
```

ie. If your button's ID is `fetchCountriesButton`, then within the `onCreate` method of your Activity, you can use:

```
fetchCountriesButton.setOnClickListener {
    OceaniaCountriesRetriever().getCountries().enqueue(this)
}
```
C) This is an asynchronous call, and so the results do not return immediately.

D) Create a Success handler to receive a successful result from the API call:

```
override fun onResponse(call: Call<OceaniaCountryList>, response: Response<OceaniaCountryList>) {
    if (response.isSuccessful) {
        val oceaniaCountryList = response.body()
        countriesTextView.text = "" // use "oceaniaCountryList" to populate this TextView
    } else {
        val response = "response not successful; ${response.errorBody().toString()}"
        Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
    }
}
```

E) Create a Faliure handler to handle an HTTP failure:

```
 override fun onFailure(call: Call<OceaniaCountryList>, t: Throwable) {
    t.printStackTrace()
    val response = "faliure; ${t.printStackTrace()}"
    Toast.makeText(this@RetrofitActivity, response, Toast.LENGTH_SHORT).show()
}
```

