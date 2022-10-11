package com.auf.cea.pyalungan.fragments

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.auf.cea.pyalungan.R
import com.auf.cea.pyalungan.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment(), View.OnClickListener {
    private lateinit var binding : FragmentUserDetailsBinding
    private lateinit var userDetailsInterface: UserDetailsInterface

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        editUsername.hint = "Username"

        editLayout.addView(editUsername)
        editLayout.setPadding(50,60,50,60)


        val alertDialog : AlertDialog.Builder? = activity?.let { AlertDialog.Builder(it) }
        alertDialog?.setTitle("Edit user details")
        alertDialog?.setView(editLayout)

        alertDialog?.setPositiveButton("Save", DialogInterface.OnClickListener{dialog,_ ->
            val username = editUsername.text.toString()

            userDetailsInterface.onEdit(username)
            dialog.dismiss()
        })

        alertDialog?.setNegativeButton("Cancel", DialogInterface.OnClickListener{dialog,_ ->
            dialog.dismiss()
        })

        alertDialog?.show()
    }
}