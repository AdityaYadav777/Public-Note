package com.aditya.notev1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aditya.notev1.Model.Data
import com.aditya.notev1.databinding.ActivityMain2Binding
import com.aditya.notev1.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = Firebase.database.reference




        if (intent.getStringExtra("MODE").equals("CREATE")) {


            binding.button.setOnClickListener {

                val key = database.child("Notes").push().key.toString()
                val message = binding.editTextText.text.toString()

                if (message.isEmpty()) {
                    Toast.makeText(this, "Khali hai", Toast.LENGTH_SHORT).show()
                } else {
                    database.child("Notes").child(key).setValue(Data(message, key))
                        .addOnCompleteListener {

                            if (it.isSuccessful) {
                                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()

                              val i=  Intent(this, MainActivity::class.java)

                                 startActivity(i)
                                 finish()



                            }
                            binding.editTextText.text.clear()

                        }
                }
            }


        }


        else{

            title="UPDATE"
            binding.editTextText.setText(intent.getStringExtra("DATA"))
            binding.button.setOnClickListener {


                val dbrf=Firebase.database.getReference("Notes").child(intent.getStringExtra("KEY")!!)
                val data=Data(binding.editTextText.text.toString(),intent.getStringExtra("KEY")!! )
                dbrf.setValue(data).addOnCompleteListener {
                    if(it.isSuccessful){
                        startActivity(Intent(this,MainActivity::class.java))
                    }
                    else {
                        Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }



        }







}