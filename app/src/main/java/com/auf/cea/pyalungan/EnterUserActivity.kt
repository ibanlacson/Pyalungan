package com.auf.cea.pyalungan

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.auf.cea.pyalungan.databinding.ActivityEnterUserBinding
import com.auf.cea.pyalungan.databinding.ActivitySplashScreenBinding

class EnterUserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEnterUserBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        binding.buttonEnter.setOnClickListener(this)

        if(sharedPreferences.getString(USER_NAME,"").toString() != "") {
            val intent = Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {
        if(binding.txtName.text.isEmpty()){
            binding.txtName.error = "Required"
            return
        }
        val editor = sharedPreferences.edit()
        editor.putString(USER_NAME,binding.txtName.text.toString())
        editor.apply()

        val intent = Intent(this,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }

}