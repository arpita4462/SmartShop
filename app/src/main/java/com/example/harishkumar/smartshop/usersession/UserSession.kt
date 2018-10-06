package com.example.harishkumar.smartshop.usersession

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log

import com.example.harishkumar.smartshop.LoginActivity

import java.util.HashMap


class UserSession(internal var context: Context) {

    internal var pref: SharedPreferences
    internal var editor: SharedPreferences.Editor
    internal var PRIVATE_MODE = 0

    val userDetails: HashMap<String, String>
        get() {
            val user = HashMap<String, String>()
            user[KEY_NAME] = pref.getString(KEY_NAME, null)
            user[KEY_EMAIL] = pref.getString(KEY_EMAIL, null)
            user[KEY_MOBiLE] = pref.getString(KEY_MOBiLE, null)
            user[KEY_PHOTO] = pref.getString(KEY_PHOTO, null)
            return user
        }

    // Get Login State
    val isLoggedIn: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)

    var cartValue: Int
        get() = pref.getInt(KEY_CART, 0)
        set(count) {
            editor.putInt(KEY_CART, count)
            editor.commit()
        }

    var wishlistValue: Int
        get() = pref.getInt(KEY_WISHLIST, 0)
        set(count) {
            editor.putInt(KEY_WISHLIST, count)
            editor.commit()
        }

    var firstTime: Boolean?
        get() = pref.getBoolean(FIRST_TIME, true)
        set(n) {
            editor.putBoolean(FIRST_TIME, n!!)
            editor.commit()
        }

    var isFirstTimeLaunch: Boolean
        get() = pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(isFirstTime) {
            editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
            editor.commit()
        }

    init {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    /**
     * Create login session
     */
    fun createLoginSession(name: String, email: String, mobile: String, photo: String) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_MOBiLE, mobile)
        editor.putString(KEY_PHOTO, photo)
        editor.commit()
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    fun checkLogin() {
        // Check login status
        if (!this.isLoggedIn) {
            val i = Intent(context, LoginActivity::class.java)
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            // Add new Flag to start new Activity
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            // Staring Login Activity
            context.startActivity(i)
        }

    }

    /**
     * Clear session details
     */
    fun logoutUser() {
        editor.putBoolean(IS_LOGIN, false)
        editor.commit()
        val i = Intent(context, LoginActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(i)
    }


    fun increaseCartValue() {
        val `val` = cartValue + 1
        editor.putInt(KEY_CART, `val`)
        editor.commit()
        Log.e("Cart Value PE", "Var value : " + `val` + "Cart Value :" + cartValue + " ")
    }

    fun increaseWishlistValue() {
        val `val` = wishlistValue + 1
        editor.putInt(KEY_WISHLIST, `val`)
        editor.commit()
        Log.e("Cart Value PE", "Var value : " + `val` + "Cart Value :" + cartValue + " ")
    }

    fun decreaseCartValue() {
        val `val` = cartValue - 1
        editor.putInt(KEY_CART, `val`)
        editor.commit()
        Log.e("Cart Value PE", "Var value : " + `val` + "Cart Value :" + cartValue + " ")
    }

    fun decreaseWishlistValue() {
        val `val` = wishlistValue - 1
        editor.putInt(KEY_WISHLIST, `val`)
        editor.commit()
        Log.e("Cart Value PE", "Var value : " + `val` + "Cart Value :" + cartValue + " ")
    }

    companion object {

        // Sharedpref file name
        private val PREF_NAME = "UserSessionPref"

        // First time logic Check
        internal val FIRST_TIME = "firsttime"

        // All Shared Preferences Keys
        private val IS_LOGIN = "IsLoggedIn"

        // User name (make variable public to access from outside)
        val KEY_NAME = "name"

        // Email address (make variable public to access from outside)
        val KEY_EMAIL = "email"

        // Mobile number (make variable public to access from outside)
        val KEY_MOBiLE = "mobile"

        // user avatar (make variable public to access from outside)
        val KEY_PHOTO = "photo"

        // number of items in our cart
        val KEY_CART = "cartvalue"

        // number of items in our wishlist
        val KEY_WISHLIST = "wishlistvalue"

        // check first time app launch
        val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
    }
}