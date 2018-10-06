package com.example.harishkumar.smartshop

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
/*
import com.android.volley.NetworkError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.ServerError
import com.android.volley.TimeoutError
import com.android.volley.VolleyError
import com.android.volley.toolbox.Volley
import com.beingdev.magicprint.networksync.CheckInternetConnection
import com.beingdev.magicprint.networksync.PasswordRequest
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail
import com.kaopiz.kprogresshud.KProgressHUD

import org.json.JSONException
import org.json.JSONObject

import es.dmoral.toasty.Toasty*/
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPassword : AppCompatActivity() {

    private var forgotpassemail: EditText? = null
    private var getEmail: String? = null
    private var sendotp: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        //check Internet Connection
//        CheckInternetConnection(this).checkConnection()

        val typeface = ResourcesCompat.getFont(this, R.font.blacklist)
        appname_tv.typeface = typeface

        forgotpassemail = findViewById(R.id.forgotpassemail)
        sendotp = findViewById(R.id.sendotp)

        sendotp!!.setOnClickListener {
            getEmail = forgotpassemail!!.text.toString()

        }

    }


    override fun onStop() {
        super.onStop()

    }

    override fun onResume() {
        super.onResume()
//        CheckInternetConnection(this).checkConnection()
    }

    companion object {
        val TAG = "MyTag"
    }
}
