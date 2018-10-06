/*
package com.example.harishkumar.smartshop.networksync

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v4.content.ContextCompat
import android.view.View
import com.geniusforapp.fancydialog.FancyAlertDialog

class CheckInternetConnection(internal var ctx: Context) {

    private val isInternetConnected: Boolean
        get() {
            val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnectedOrConnecting

        }

    fun checkConnection() {

        if (!isInternetConnected) {

            val alert = FancyAlertDialog.Builder(ctx)
                    .setBackgroundColor(R.color.accent)
                    .setimageResource(R.drawable.internetconnection)
                    .setTextTitle("No Internet")
                    .setTextSubTitle("Cannot connect to a servers")
                    .setBody(R.string.noconnection)
                    .setPositiveButtonText("Connect Now")
                    .setPositiveColor(R.color.colorPrimaryDark)
                    .setOnPositiveClicked(object : FancyAlertDialog.OnPositiveClicked {
                      override fun OnClick(view: View, dialog: Dialog) {

                            if (isInternetConnected) {
                                dialog.dismiss()

                            } else {

                                val dialogIntent = Intent(android.provider.Settings.ACTION_SETTINGS)
                                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                ctx.startActivity(dialogIntent)
                            }
                        }
                    })
                    .setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
                    .setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
                    .setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
                    .setCancelable(false)
                    .build()
            alert.show()
        }
    }
}
*/
