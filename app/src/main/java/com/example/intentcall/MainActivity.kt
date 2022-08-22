package com.example.intentcall

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.intentcall.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        InitView()
    }
   private fun  InitView(){



       binding.btn.setOnClickListener {
           val edit = binding.edit.text.toString().trim()
           val edit2 = binding.edit2.text.toString().trim()
           val edit3 = binding.edit3.text.toString().trim()
           when{
               edit.isEmpty() ->{
                   binding.edit.error = "Enter email!"
               }
               edit2.isEmpty() ->{
                   binding.edit2.error = "Enter title!"
               }

               edit3.isEmpty() ->{
                   binding.edit3.error = "Enter Discription!"
               }
               else ->{
                   sendEmail(edit,edit2,edit3)
               }
           }
       }
   }

    private fun sendEmail(edit:String,edit2:String,edit3:String){
        val intent = Intent(Intent.ACTION_SEND).apply {
            data = Uri.parse("mailto:")
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL,edit)
            putExtra(Intent.EXTRA_SUBJECT,edit2)
            putExtra(Intent.EXTRA_TEXT,edit3)
        }
        try {
            startActivity(Intent.createChooser(intent,"Share"))
        }catch (e:Exception){
            Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()
        }
    }
}