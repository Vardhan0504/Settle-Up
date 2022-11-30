package com.example.settleup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {
    private lateinit var btn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.existingandcreategroup)
          btn = findViewById(R.id.button)
          btn.setOnClickListener{
              FirebaseAuth.getInstance().signOut()
              val i = Intent(this,MainActivity::class.java)
              startActivity(i)
          }

      }
}
