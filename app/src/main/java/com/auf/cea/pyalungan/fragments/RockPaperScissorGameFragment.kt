package com.auf.cea.pyalungan.fragments

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.auf.cea.pyalungan.R
import com.auf.cea.pyalungan.databinding.FragmentRockPaperScissorGameBinding
import com.auf.cea.pyalungan.helperclasses.RPCHelper

class RockPaperScissorGameFragment : Fragment(), View.OnClickListener {

    private lateinit var binding : FragmentRockPaperScissorGameBinding
    private lateinit var rockPaperScissorsGameFragmentInterface: RockPaperScissorGameFragmentInterface
    private var computerPick:Int = -1
    private var playerPickID:Int = -1

    interface RockPaperScissorGameFragmentInterface {
        fun returnHome()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        rockPaperScissorsGameFragmentInterface = context as RockPaperScissorGameFragmentInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRockPaperScissorGameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPaper.setOnClickListener(this)
        binding.btnScissors.setOnClickListener(this)
        binding.btnStone.setOnClickListener(this)
        binding.btnReturn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            (R.id.btn_paper) -> {
                computerPicks("Paper")
            }
            (R.id.btn_scissors) -> {
                computerPicks("Scissors")
            }
            (R.id.btn_stone) -> {
                computerPicks("Rock")
            }
            (R.id.btn_return) -> {
                rockPaperScissorsGameFragmentInterface.returnHome()
            }
        }
    }
    private fun updateDisplay(playerPick:String):Int {
        when(playerPick) {
            ("Rock") -> {
                binding.imgPlayerPlay.setImageResource(R.drawable.ic_stone)
                return R.drawable.ic_stone
            }
            ("Paper") -> {
                binding.imgPlayerPlay.setImageResource(R.drawable.ic_paper)
                return R.drawable.ic_paper
            }
            ("Scissors") -> {
                binding.imgPlayerPlay.setImageResource(R.drawable.ic_scissors)
                return R.drawable.ic_scissors
            }
            else -> {
                binding.imgPlayerPlay.setImageResource(R.drawable.ic_stone)
                return R.drawable.ic_stone
            }
        }
    }

    private fun computerPicks(playerPick: String){
        object : CountDownTimer(3000, 200){
            override fun onTick(p0: Long) {
                val imgID = RPCHelper.getImage()
                binding.imgComputerPlay.setImageResource(imgID)
                computerPick = imgID

                binding.btnStone.isEnabled = false
                binding.btnPaper.isEnabled = false
                binding.btnScissors.isEnabled = false

            }

            override fun onFinish() {
                updateDisplay(playerPick)
                playerPickID = (updateDisplay(playerPick))
                val result = RPCHelper.evaluateResult(computerPick,playerPickID)
                //Log.d("RESULT:", result)
                binding.txtResult.text = result
                binding.btnStone.isEnabled = true
                binding.btnPaper.isEnabled = true
                binding.btnScissors.isEnabled = true
            }
        }.start()
    }
}