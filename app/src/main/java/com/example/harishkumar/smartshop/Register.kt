package com.example.harishkumar.smartshop

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
/*import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.beingdev.magicprint.networksync.CheckInternetConnection
import com.beingdev.magicprint.networksync.RegisterRequest
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail
import com.kaopiz.kprogresshud.KProgressHUD
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener*/

import org.json.JSONException
import org.json.JSONObject

import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream



class Register : AppCompatActivity() {

    private var edtname: EditText? = null
    private var edtemail: EditText? = null
    private var edtpass: EditText? = null
    private var edtcnfpass: EditText? = null
    private var edtnumber: EditText? = null
    private var check: String? = null
    private var name: String? = null
    private var email: String? = null
    private var password: String? = null
    private var mobile: String? = null
    private var profile: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //check Internet Connection
//        CheckInternetConnection(this).checkConnection()

        val typeface = ResourcesCompat.getFont(this, R.font.blacklist)
        appname_tvreg.setTypeface(typeface)

        edtname = findViewById(R.id.name)
        edtemail = findViewById(R.id.email)
        edtpass = findViewById(R.id.password)
        edtcnfpass = findViewById(R.id.confirmpassword)
        edtnumber = findViewById(R.id.number)

        edtname!!.addTextChangedListener(nameWatcher)
        edtemail!!.addTextChangedListener(emailWatcher)
        edtpass!!.addTextChangedListener(passWatcher)
        edtcnfpass!!.addTextChangedListener(cnfpassWatcher)
        edtnumber!!.addTextChangedListener(numberWatcher)



        //validate user details and register user

        register.setOnClickListener(View.OnClickListener {
            //TODO AFTER VALDATION
            if (validateName() && validateEmail() && validatePass() && validateCnfPass() && validateNumber()) {

                name = edtname!!.text.toString()
                email = edtemail!!.text.toString()
                password = edtcnfpass!!.text.toString()
                mobile = edtnumber!!.text.toString()

                //Validation Success
            }
        })

        //Take already registered user to login page

        login_now.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@Register, LoginActivity::class.java))
            finish()
        })

        //take user to reset password

        forgot_pass.setOnClickListener {
            startActivity(Intent(this@Register, ForgotPassword::class.java))
            finish()
        }


    }

    //TextWatcher for Name -----------------------------------------------------

    internal var nameWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            //none
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            //none
        }

        override fun afterTextChanged(s: Editable) {

            check = s.toString()

            if (check!!.length < 4 || check!!.length > 20) {
                edtname!!.error = "Name Must consist of 4 to 20 characters"
            }
        }

    }

    //TextWatcher for Email -----------------------------------------------------

    internal var emailWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            //none
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            //none
        }

        override fun afterTextChanged(s: Editable) {

            check = s.toString()

            if (check!!.length < 4 || check!!.length > 40) {
                edtemail!!.error = "Email Must consist of 4 to 20 characters"
            } else if (!check!!.matches("^[A-za-z0-9.@]+".toRegex())) {
                edtemail!!.error = "Only . and @ characters allowed"
            } else if (!check!!.contains("@") || !check!!.contains(".")) {
                edtemail!!.error = "Enter Valid Email"
            }

        }

    }

    //TextWatcher for pass -----------------------------------------------------

    internal var passWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            //none
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            //none
        }

        override fun afterTextChanged(s: Editable) {

            check = s.toString()

            if (check!!.length < 4 || check!!.length > 20) {
                edtpass!!.error = "Password Must consist of 4 to 20 characters"
            } else if (!check!!.matches("^[A-za-z0-9@]+".toRegex())) {
                edtemail!!.error = "Only @ special character allowed"
            }
        }

    }

    //TextWatcher for repeat Password -----------------------------------------------------

    internal var cnfpassWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            //none
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            //none
        }

        override fun afterTextChanged(s: Editable) {

            check = s.toString()

            if (check != edtpass!!.text.toString()) {
                edtcnfpass!!.error = "Both the passwords do not match"
            }
        }

    }


    //TextWatcher for Mobile -----------------------------------------------------

    internal var numberWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            //none
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            //none
        }

        override fun afterTextChanged(s: Editable) {

            check = s.toString()

            if (check!!.length > 10) {
                edtnumber!!.error = "Number cannot be grated than 10 digits"
            } else if (check!!.length < 10) {
                edtnumber!!.error = "Number should be 10 digits"
            }
        }

    }


    private fun validateNumber(): Boolean {

        check = edtnumber!!.text.toString()
        Log.e("inside number", check!!.length.toString() + " ")
        if (check!!.length > 10) {
            return false
        } else if (check!!.length < 10) {
            return false
        }
        return true
    }

    private fun validateCnfPass(): Boolean {

        check = edtcnfpass!!.text.toString()

        return check == edtpass!!.text.toString()
    }

    private fun validatePass(): Boolean {


        check = edtpass!!.text.toString()

        if (check!!.length < 4 || check!!.length > 20) {
            return false
        } else if (!check!!.matches("^[A-za-z0-9@]+".toRegex())) {
            return false
        }
        return true
    }

    private fun validateEmail(): Boolean {

        check = edtemail!!.text.toString()

        if (check!!.length < 4 || check!!.length > 40) {
            return false
        } else if (!check!!.matches("^[A-za-z0-9.@]+".toRegex())) {
            return false
        } else if (!check!!.contains("@") || !check!!.contains(".")) {
            return false
        }

        return true
    }

    private fun validateName(): Boolean {

        check = edtname!!.text.toString()

        return !(check!!.length < 4 || check!!.length > 20)

    }

    override fun onResume() {
        super.onResume()
        //check Internet Connection
//        CheckInternetConnection(this).checkConnection()
    }

    override fun onStop() {
        super.onStop()
    }

    companion object {
        val TAG = "MyTag"
    }
}


