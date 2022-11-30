package com.example.settleup

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View.inflate
import android.view.inputmethod.InputBinding
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

  private lateinit var textView: TextView
  private lateinit var client: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
        textView = findViewById(R.id.button3)
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        client = GoogleSignIn.getClient(this , options)
        textView.setOnClickListener {
            val intent = client.signInIntent
            startActivityForResult(intent,10001)
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==10001)
        {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken,null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful)
                    {
                            val i = Intent(this,MainActivity2::class.java)
                        startActivity(i)
                    }
                    else
                    {
                        Toast.makeText(this, task.exception?.message,Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }

    override fun onStart() {
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser != null)
        {
            val i = Intent(this,MainActivity2::class.java)
            startActivity(i)
        }
    }

}