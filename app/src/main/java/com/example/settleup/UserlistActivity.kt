package com.example.settleup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.FirebaseDatabaseKtxRegistrar
import com.google.firebase.ktx.Firebase

class UserlistActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView : RecyclerView
    private lateinit var userArrayList : ArrayList<User>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userlist)

        userRecyclerView = findViewById(R.id.userlist)
        userRecyclerView.LayoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf<User>()
        getUserData()


    }

    private fun getUserData(){

        dbref = FirebaseDatabase.getInstance().getReference(userArrayList)
    }

}