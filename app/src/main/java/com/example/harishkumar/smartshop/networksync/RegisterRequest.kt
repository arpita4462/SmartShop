/*
package com.example.harishkumar.smartshop.networksync

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

import java.util.HashMap



class RegisterRequest(name: String, password: String, mobile: String, email: String, photo: String, listener: Response.Listener<String>) : StringRequest(Method.POST, REGISTER_URL, listener, null) {
    private val parameters: MutableMap<String, String>
    protected val params: Map<String, String>
        @Throws(AuthFailureError::class)
        get() = parameters

    init {
        parameters = HashMap()
        parameters["name"] = name
        parameters["password"] = password
        parameters["mobile"] = mobile
        parameters["email"] = email
        parameters["image"] = photo

    }

    companion object {

        private val REGISTER_URL = "http://magic-print.000webhostapp.com/register.php"
    }
}
*/
