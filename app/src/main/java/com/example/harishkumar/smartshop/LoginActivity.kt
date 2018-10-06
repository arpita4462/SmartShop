package com.example.harishkumar.smartshop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.activity_loginph.*
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.res.ResourcesCompat
import android.widget.Toast
import com.atrio.smartdeal.CheckPermission
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity()/*, CountryCodePicker.OnCountryChangeListener, View.OnClickListener*/ {
    var isd_code: String? = null
    var phn_no: String? = null
    var user: FirebaseUser? = null
    lateinit var mAuth: FirebaseAuth
    private var email: String? = null
    private var pass:String? = null
    private var sessionmobile:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
        user = mAuth.currentUser


        val typeface = ResourcesCompat.getFont(this, R.font.blacklist)
        appname_tv.setTypeface(typeface)

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (CheckPermission().ischeckandrequestPermission(this@LoginActivity)) {
            }
        }
        Log.i("user11",user.toString())

        Log.e("Login CheckPoint", "LoginActivity started")
        //check Internet Connection
//        CheckInternetConnection(this).checkConnection()

        register_now.setOnClickListener {
            startActivity(Intent(this@LoginActivity, Register::class.java))
            finish()
        }

        forgot_pass.setOnClickListener { startActivity(Intent(this@LoginActivity, ForgotPassword::class.java)) }


        login_button.setOnClickListener(View.OnClickListener {
            email = email_et.getText().toString()
            pass = password_et.getText().toString()


            if (validateUsername(email!!) && validatePassword(pass!!)) { //Username and Password Validation

                val loginSuccess = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(loginSuccess)
                finish()

            }
        })

/*   if (user != null) {
     startActivity(Intent(this, MainActivity::class.java))
     finish()
 } else {
     isd_code = sp_country.glectedCountryCodeWithPlus()
     sp_country.setOnCountryChangeListener(this)
     btn_next.setOnClickListener(this
 }
)*/

}

/*
override fun onClick(v: View?) {
 if (validatePhoneNumber()) {
     phn_no = isd_code + et_phone.getText().toString().trim()
     intent = Intent(this, Verify_Otp_Activity::class.java)
     intent.putExtra("phn_number", phn_no)
     startActivity(intent)
     finish()
 }
}


override fun onCountrySelected() {
 isd_code = sp_country.getSelectedCountryCodeWithPlus()
//        Toast.makeText(this, "" + sp_country.getSelectedCountryCodeWithPlus(), Toast.LENGTH_SHORT).show();
}

private fun validatePhoneNumber(): Boolean {
 if (!TextUtils.isEmpty(et_phone.getText().toString())) {
     return true
 }
 et_phone.setError("Invalid phone number.")
 return false
}*/

    private fun validatePassword(pass: String): Boolean {


        if (pass.length < 4 || pass.length > 20) {
            password_et.setError("Password Must consist of 4 to 20 characters")
            return false
        }
        return true
    }

    private fun validateUsername(email: String): Boolean {

        if (email.length < 4 || email.length > 30) {
            email_et.setError("Email Must consist of 4 to 30 characters")
            return false
        } else if (!email.matches("^[A-za-z0-9.@]+".toRegex())) {
            email_et.setError("Only . and @ characters allowed")
            return false
        } else if (!email.contains("@") || !email.contains(".")) {
            email_et.setError("Email must contain @ and .")
            return false
        }
        return true
    }

override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
 super.onRequestPermissionsResult(requestCode, permissions, grantResults)

 when(requestCode){
     CheckPermission().PERMISSION_CODE->{
         if (grantResults.last()>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
         }
     }
 }
}
}
