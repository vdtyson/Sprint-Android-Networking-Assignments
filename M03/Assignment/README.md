
# Http Operations
Coding Assignment: Spring 4, Module 3 
Create an Android app that uses mockable.io as a backend and implements the following operations
GET, POST, PUT, DELETE to the list of employees of a company

Using: Kotlin, Retrofit, Okhttp, GSON

HTTP API: https://mockable.io

### STEP 1 - Create Android Project
A) Using the Android Studio IDE, create a New -> Project

B) Start with an Empty Activity called MainActivity

C) Define your own package name

D) Other settings:

Minimum API: 22

Language: Kotlin

True: Use androidx.* artifacts

E) Click Finish to create the project

### STEP 2 - Setup Initial UI 

A) In activity_main.xml add four buttons each for GET, POST, PUT and DELETE

B) Each of these buttons should take the user to a new Activity, so create four new activities for each of the operations, e.g., GETACTIVITY, POSTACTIVITY, PUTACTIVITY and DELETEACTIVITY. Additionally, create another activity called GETPICKERACTIVITY with three buttons in the layout for making 
1) A Simple GET call 
2) GET call with a path parameter
3) GET call with a query parameter

For the layout of these activities, just use a standard constraintlayout with a NestedScrollview, for scrolling across the content and a textview for setting the result. 

    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="8dp"
        tools:context=".HttpGetActivity">

    <ProgressBar
            android:id="@+id/progressBar"
            style="?android:attr/progressBarStyleLarge"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"
    />

    <androidx.core.widget.NestedScrollView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_marginTop="24dp"
            app:layout_constraintTop_toBottomOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"
    >

        <TextView
                android:id="@+id/result"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textColor="#000"
        />

    </androidx.core.widget.NestedScrollView>

    </androidx.constraintlayout.widget.ConstraintLayout>

C) Make sure to add a function to check if network is connected before taking the user to any of the above activities and handle the case of network unavailability by showing a Snackbar message to the user. 

### STEP 3 - Add Dependencies & Permissions

A) In AndroidManifest.xml add 
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
        <uses-permission android:name="android.permission.INTERNET"/>

B) In app/build.gradle, add dependencies for: 

    ext {
    gsonVersion = '2.8.5'
    retrofitVersion = '2.6.1'
    materialVersion = '1.0.0'
    }

     // Gson
     implementation "com.google.code.gson:gson:$gsonVersion"

    // Retrofit2
    implementation "com.squareup.retrofit2:retrofit:$retrofitVersion"
    implementation "com.squareup.retrofit2:converter-gson:$retrofitVersion"

    // OkHttp
    implementation "com.squareup.okhttp3:okhttp:4.1.0"
    
    implementation "com.squareup.okhttp3:logging-interceptor:4.1.0"

    implementation "com.google.android.material:material:$materialVersion" 
### STEP 4 - Create Employee Model

A) Create a data class under a package named model and call it Employee. It will have four fields.
 name (String), id (Int), age(Int), title(String)

Note: Make sure to specify these fields as vals and pay attention to the casing. They should be named exactly the way the tutorial spells them for GSON to parse them correctly.

### STEP 5 - Create JsonPlaceHolderAPI interface
A) Create an interface that has all the endpoints that you will be hitting for responses

    Add the endpoints here with the correct functions and parameters and return types

B) Create a Factory class that will serve as a Helper class to retrieve the retrofit object. 

    class Factory {

        companion object {

            private const val BASE_URL = "https://demo8143297.mockable.io"

            fun create(): JsonPlaceHolderApi {

                val logger = HttpLoggingInterceptor()
                logger.level = HttpLoggingInterceptor.Level.BASIC
                logger.level = HttpLoggingInterceptor.Level.BODY

                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .retryOnConnectionFailure(false)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build()

                val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                return retrofit.create(JsonPlaceHolderApi::class.java)
            }
        }
    }

 Note the key lines here. 1) The BASE_URL, this will depend on the backend you're using.
 2) Http logging (you will see the results in logcat) 3) okhttp timeout settings and finally 4) the retrofit builder which takes in the okhttpclient, the base url and the GSON converter factory that converts json from the backend into JAVA/Kotlin objects for us.

### STEP 6 - Making the Network Calls

A) Get an instance to the JsonPlaceHolderApi in your HTTPGetActivity and create a lateinit variable for it. Make the following network calls. 

1) Make a simple GET call to get all employees when users click on the first button in the GETPickerActivity. 

2) Make a GET call with employee id "2" as path parameter when users click on the second button in the GETPickerActivity

3) Make a GET call with employee age - 45 as query parameter when users click on the third button in the GetPickerActivity 

4) Back in the MainActivity when users click on the Post button, make a POST call to add a new employee with details age = 30, id = 7, name = David, title = intern

5) In MainActivity when users click on PUT button, make a PUT call to update existing employee Steve's title, with details, age = 25, id = 1, name = "Steve" and title = "Principal Egnineer"

6) In MainActivity when users click on DELETE button, make a DELETE call to delete existing employee Steve, id = 1 

7) Handle all failures as outlined in the BASIC Networking Module. 
