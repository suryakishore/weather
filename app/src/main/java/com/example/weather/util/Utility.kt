package com.example.weather.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

/**
 * This class is used to show the utility methods which are companion objects used by through out  the app
 */
class Utility {

    companion object {

        fun isNetworkConnected(activity: Activity): Boolean {
            val cm =
                activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (cm != null) {
                cm.activeNetworkInfo != null
            } else false
        }
    }


}