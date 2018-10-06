/*
package com.example.harishkumar.smartshop.networksync

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import retrofit2.Response

import java.util.HashMap


class ChangePhotoRequest(email: String, image: String, listener: Response.Listener<String>) : StringRequest(Method.POST, REGISTER_URL, listener, null) {
    private val parameters: MutableMap<String, String>
    protected val params: Map<String, String>
        @Throws(AuthFailureError::class)
        get() = parameters

    init {
        parameters = HashMap()

        parameters["email"] = email
        parameters["image"] = image

    }

    companion object {

        private val REGISTER_URL = "http://magic-print.000webhostapp.com/updatepic.php"
    }
}
*/
