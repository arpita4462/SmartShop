/*
package com.example.harishkumar.smartshop.networksync

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

import java.util.HashMap


class PasswordRequest(email: String, listener: Response.Listener<String>, errorListener: Response.ErrorListener) : StringRequest(Method.POST, LOGIN_URL, listener, errorListener) {
    private val parameters: MutableMap<String, String>

    protected val params: Map<String, String>
        @Throws(AuthFailureError::class)
        get() = parameters

    init {
        parameters = HashMap()
        parameters["email"] = email
    }

    companion object {

        private val LOGIN_URL = "http://magic-print.000webhostapp.com/forgotpass.php"
    }

}
*/
