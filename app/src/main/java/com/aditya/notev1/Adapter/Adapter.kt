package com.aditya.notev1.Adapter


import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.format.Time
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import android.widget.ImageView
import android.widget.TextClock

import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast


import androidx.recyclerview.widget.RecyclerView

import com.aditya.notev1.MainActivity2
import com.aditya.notev1.Model.Data
import com.aditya.notev1.R
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.Clock
import java.time.LocalTime
import java.util.Date
import kotlin.io.path.fileVisitor


class Adapter(val dataList: List<Data>,val context: Context):RecyclerView.Adapter<Adapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val txt=itemView.findViewById<TextView>(R.id.txt_Rec)
        val delBtn=itemView.findViewById<ImageView>(R.id.delete_btn)
        val editBtn=itemView.findViewById<ImageView>(R.id.edit_btn)
        val time=itemView.findViewById<TextView>(R.id.time)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(

           LayoutInflater.from(parent.context).inflate(R.layout.rc_des,parent,false)

       )
    }

    override fun getItemCount(): Int {
       return dataList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {



        holder.txt.setText(dataList[position].data)
        holder.time.text= dataList[position].currentD

        holder.delBtn.setOnClickListener {
            val  key = dataList[position].key
            val dB=Firebase.database.getReference("Notes").child(key!!)
            dB.removeValue().addOnCompleteListener {

                if(it.isSuccessful){
                    notifyDataSetChanged()

                    Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show()
                }
            }

            }

               holder.editBtn.setOnClickListener {

                   val i= Intent(context, MainActivity2::class.java)

                  i.putExtra("MODE","UPDATE")
                   i.putExtra("KEY",dataList[position].key)
                   i.putExtra("DATA",dataList[position].data)
                   context.startActivity(i)

               }



            }

        }





