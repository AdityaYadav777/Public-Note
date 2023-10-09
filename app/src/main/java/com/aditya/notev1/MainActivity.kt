package com.aditya.notev1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.notev1.Adapter.Adapter
import com.aditya.notev1.Model.Data
import com.aditya.notev1.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    var list= ArrayList<Data>()
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = Firebase.database.reference

        binding.recView.isInEditMode
var adi:String=intent.getStringExtra("DATA").toString()




        binding.editIcon.setOnClickListener {

           val i = Intent(this,MainActivity2::class.java).putExtra("MODE","CREATE")

            startActivity(i)

        }
        binding.recView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        val rv_Adapter=Adapter(list,this)
        binding.recView.adapter=rv_Adapter
        val dbrf=Firebase.database.getReference("Notes")

        dbrf.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for(dataSnap in snapshot.children) {
                    val data=dataSnap.getValue(Data::class.java)
                    list.add(data!!)
                    rv_Adapter.notifyDataSetChanged()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })








    }
}