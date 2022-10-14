package com.auf.cea.pyalungan.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.auf.cea.pyalungan.EnterUserActivity
import com.auf.cea.pyalungan.PREFERENCE_NAME
import com.auf.cea.pyalungan.R
import com.auf.cea.pyalungan.USER_NAME
import com.auf.cea.pyalungan.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var homeFragmentInterface: HomeFragmentInterface

    interface HomeFragmentInterface{
        fun openNavDrawer()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeFragmentInterface = context as HomeFragmentInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentHomeBinding.inflate(inflater,container,false)
        sharedPreferences = requireActivity().getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.welcomeMessage.text = String.format("Hi, %s! Let's Play!",sharedPreferences.getString(
            USER_NAME,""))
        binding.btnChoose.setOnClickListener(this)
        binding.btnLogout.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            (R.id.btn_choose) -> {
               homeFragmentInterface.openNavDrawer()
            }
            (R.id.btn_logout) ->{
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()

                val intent = Intent(requireContext(),EnterUserActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

            }
        }
    }
}

