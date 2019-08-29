# Android Threads Assignment

## Introduction

This app will have you create a large number of prime numbers using AsyncTask. Generating a large set of primes is used often in cryptography --  it's computationally expensive and takes a long time. Your mission will be to generate these prime numbers without blocking the UI thread.

## Instructions

### Part 1 - Activity
1. Create a new Android project with a blank Activity
2. Edit the main Activity layout to include:
	a. a centered `TextView` for displaying our prime numbers
	b. a centered `ProgressBar` for indicating progress to the user while we're generating prime numbers on a background Thread:
    ```Xml
    <ProgressBar
            android:id="@+id/progressBar"
            android:visibility="invisible"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"/>
    ```
3. Add a helper method to your main Activity for generating prime numbers:
	```Kotlin
	fun primes(): Sequence<Long> {
			var i = 0L
			return sequence {
					generateSequence { i++ }
							.filter { n -> n > 1 && ((2 until n).none { i -> n % i == 0L }) }
							.forEach { yield(it) }
			}
	}
	```
### Part 2 - App Setup and Initial Test Without Threading
Our interaction for generating prime numbers will be naive and simple in order to illustrate the power of Threading.
1. Write a method in your Activity for toggling your `ProgressBar` from `INVISIBLE` to `VISIBLE` and vice-versa. We'll use this for indicating progress to the user while we are running a background Thread.
2. When your Activity loads, hide the `TextView` from part 2.a above, and user your method from step 1 to display your progressBar.
3. Now try to generate 15,000 + prime numbers and display them in your `TextView`. What happens? Does the app feel unresponsive or even crash?
    ```Kotlin
    val primeNumbers = primes().take(16000).joinToString(", ")
    txt_primes.text = "Primes: $primeNumbers"
    ```

### Part 3 - Multi-Threading with AsyncTask
Now, you've seen how long this process takes and what happens when you run it on the main thread, let's run it on a background thread.
1. Create a new inner class (class inside of another class, your main activity in this case) which extends `AsyncTask`
	a. When extending `AsyncTask`, we pass data types for the type of parameter it accepts, how it reports progress, and how the result is passed:
    ```Kotlin
    class MyAsyncTask : AsyncTask<Unit, Int, String>() {
    ...
    }
    ```
2. Navigate to your "Generate" menu (right-click, alt-insert...)
3. Insert Override methods for onPreExecute, onPostExecute, onCancelled, doInBackground. This will generate methods with the proper signatures for the types you gave in the class signature

Now we'll update our prime number generation to work in a background Thread instead.
4. In `onPreExecute`, hide your `TextView` and display the `ProgressBar` with your helper method
5. In `doInBackground`, perform the time-consuming portion of your task on a background Thread, and you won't be able to access any views in the UI from it. Move your code for prime number generation here, and return the appended string of prime numbers. This will be passed into the `onPostExecute` method.
6. In `onPostExecute`, perform tasks you would do after the thread completes (hide the progress bar and update the text in the field)
	a. This method will accept a parameter which matches what you put in the class signature and returned in `doInBackground`. This value will be what you returned in the `doInBackground` method.
7. At the end of your listener, add a line to construct a new instance of this class, call the execute method on it, passing in the data you need to work on (the string from the textview). Build and test your app. Play with the UI and see that it is now responsive.
9. In `onCancelled` will allow you to release any resources before this `AsyncTask` is canceled (close a file, close a database, etc.)
	a. because this class is an inner class of an `Activity`, that activity can't end until this thread has completed so we'll need to implement a cancel feature in our background thread.
	b. Override the `onStop` method in your Main Activity. Call `cancel` on your `AsyncTask` instance in this method.
	c. set breakpoints or log statements in your `Activity.onStop` and `AsyncTask` `onCancelled`methods and test the app to make sure the thread is canceled.

### Part 4 - Optional Stretch-Goal
Now, you've seen how AsyncTask works, try to wire up a Thread manually instead. Do some research on Thread, Looper and Handler and try to mimic the behavior of the AsyncTask.
