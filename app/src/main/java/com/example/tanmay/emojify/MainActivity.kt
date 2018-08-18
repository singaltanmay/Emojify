package com.example.tanmay.emojify

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private companion object {
        val REQUEST_IMAGE_CAPTURE = 1289;
        val REQUEST_STORAGE_PERMISSION = 28497;

        val FILE_PROVIDER_AUTHORITY = "com.example.android.fileprovider"

    }

    var mEmojifyButton: Button? = null
    var mSaveFab: FloatingActionButton? = null
    var mClearFab: FloatingActionButton? = null
    var mShareFab: FloatingActionButton? = null
    var mTitleBox: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mEmojifyButton = action_initiate_emojification
        mSaveFab = action_save_image
        mClearFab = action_clear_image
        mShareFab = action_share_image
        mTitleBox = title_text

    }

    // Get's executed when mEmojifyButton is pressed
    fun emojifyMe(view: View) {

        // Check for external storage writing permissions
        if (ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // If permission not granted then request it
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_STORAGE_PERMISSION);

        } else {

            // Launch the camera if permission exists
            launchCamera();

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){
            REQUEST_STORAGE_PERMISSION -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // If you get permission, launch camera
                    launchCamera();
                }else Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun launchCamera(){
        Toast.makeText(this, "Camera Launched", Toast.LENGTH_SHORT).show()
    }
}