package com.auf.cea.pyalungan.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auf.cea.pyalungan.LUCKY_NUMBER
import com.auf.cea.pyalungan.PREFERENCE_NAME
import com.auf.cea.pyalungan.R
import com.auf.cea.pyalungan.databinding.FragmentDiceRollerBinding
import com.auf.cea.pyalungan.helperclasses.DRHelper

class DiceRollerFragment : Fragment(), View.OnClickListener {
    private lateinit var binding : FragmentDiceRollerBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var diceRollerFragmentInterface: DiceRollerFragmentInterface
    private var luckyNumber = -1
    private var diceCounter = 0

    interface DiceRollerFragmentInterface{
        fun returnHome()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        diceRollerFragmentInterface = context as DiceRollerFragmentInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiceRollerBinding.inflate(inflater,container,false)
        sharedPreferences = requireActivity().getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        luckyNumber = sharedPreferences.getInt(LUCKY_NUMBER,2)

        binding.btnRoll.setOnClickListener(this)
        binding.btnResetLuckyNumber.setOnClickListener(this)
        binding.btnReturn.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            (R.id.btn_roll) -> {
                object : CountDownTimer(3000,200){
                    override fun onTick(p0: Long) {
                        var rollingAnimate:String = ""
                        when(diceCounter){
                            (0) -> {
                                binding.imgDice.setImageResource(R.drawable.die_1)
                                diceCounter++
                                rollingAnimate = "Rolling."
                            }
                            (1) -> {
                                binding.imgDice.setImageResource(R.drawable.die_2)
                                diceCounter++
                                rollingAnimate = "Rolling.."
                            }
                            (2) -> {
                                binding.imgDice.setImageResource(R.drawable.die_3)
                                diceCounter++
                                rollingAnimate = "Rolling..."
                            }
                            (3) -> {
                                binding.imgDice.setImageResource(R.drawable.die_4)
                                diceCounter++
                                rollingAnimate = "Rolling."
                            }
                            (4) -> {
                                binding.imgDice.setImageResource(R.drawable.die_5)
                                diceCounter++
                                rollingAnimate = "Rolling.."
                            }
                            (5) -> {
                                binding.imgDice.setImageResource(R.drawable.die_6)
                                diceCounter = 0
                                rollingAnimate = "Rolling..."
                            }
                        }
                        binding.txtResult.text=rollingAnimate
                    }

                    override fun onFinish() {
                        val diceRolled = DRHelper.diceRoll()
                        binding.imgDice.setImageResource(diceRolled)
                        val result = DRHelper.evaluateResult(diceRolled,luckyNumber)
                        binding.txtResult.text = result
                        Log.d("LUCKY NUMBER:",luckyNumber.toString())
                    }
                }.start()

            }
            (R.id.btn_reset_lucky_number) -> {
                luckyNumber = DRHelper.resetLuckyNumber()
                val editor = sharedPreferences.edit()
                editor.putInt(LUCKY_NUMBER,luckyNumber)
                editor.apply()
                Toast.makeText(requireContext(),"Lucky Number has been reset!", Toast.LENGTH_SHORT).show()
            }
            (R.id.btn_return) -> {
                diceRollerFragmentInterface.returnHome()
            }
        }
    }

}