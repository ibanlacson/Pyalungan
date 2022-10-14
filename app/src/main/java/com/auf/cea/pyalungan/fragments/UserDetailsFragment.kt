package com.auf.cea.pyalungan.fragments

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.auf.cea.pyalungan.PREFERENCE_NAME
import com.auf.cea.pyalungan.R
import com.auf.cea.pyalungan.USER_NAME
import com.auf.cea.pyalungan.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment(), View.OnClickListener {
    private lateinit var binding : FragmentUserDetailsBinding
    private lateinit var userDetailsInterface: UserDetailsInterface
    private lateinit var sharedPreferences: SharedPreferences

    interface UserDetailsInterface{
        fun onEdit(username:String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        userDetailsInterface = context as UserDetailsInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailsBinding.inflate(inflater,container,false)
        sharedPreferences = requireActivity().getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtName.text = String.format("Name:  %s",sharedPreferences.getString(USER_NAME,""))
        binding.btnEditUserDetails.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            (R.id.btn_edit_user_details) -> {
                createAlertDialog()
            }
        }
    }

    private fun createAlertDialog(){
        val editLayout = LinearLayout(activity)
        editLayout.orientation = LinearLayout.VERTICAL

        val editUsername = EditText(activity)
        editUsername.setText(sharedPreferences.getString(USER_NAME,""))
        editUsername.hint = "Username"

        editLayout.addView(editUsername)
        editLayout.setPadding(50,60,50,60)


        val alertDialog : AlertDialog.Builder? = activity?.let { AlertDialog.Builder(it) }
        alertDialog?.setTitle("Edit user details")
        alertDialog?.setView(editLayout)

        alertDialog?.setPositiveButton("Save", DialogInterface.OnClickListener{dialog,_ ->
            var username = editUsername.text.toString()

            if (username.isEmpty()) {
                username = sharedPreferences.getString(USER_NAME,"").toString()
                Toast.makeText(requireContext(),"Username cannot be empty!",Toast.LENGTH_SHORT).show()
            }

            userDetailsInterface.onEdit(username)

            // Change the username on the fragment
            binding.txtName.text =  String.format("Name:  %s",username)

            // Change the username saved on sharedPreference
            val editor = sharedPreferences.edit()
            editor.putString(USER_NAME,username)
            editor.apply()

            dialog.dismiss()
        })


        alertDialog?.setNegativeButton("Cancel", DialogInterface.OnClickListener{dialog,_ ->
            dialog.dismiss()
        })

        alertDialog?.show()
    }
}