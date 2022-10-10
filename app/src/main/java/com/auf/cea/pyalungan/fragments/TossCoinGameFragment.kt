package com.auf.cea.pyalungan.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.auf.cea.pyalungan.R
import com.auf.cea.pyalungan.databinding.FragmentTossCoinGameBinding
import com.auf.cea.pyalungan.helperclasses.TCHelper

class TossCoinGameFragment : Fragment(), View.OnClickListener {
    private lateinit var  binding : FragmentTossCoinGameBinding
    private var tossedCoin =  -1
    private var tossResult = ""
    private var fauxCoin = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTossCoinGameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tossedCoin = TCHelper.tossCoin()
        binding.imgCoin.setImageResource(tossedCoin)
        tossResult = TCHelper.evaluateResult(tossedCoin)
        binding.txtTossResult.text = tossResult

        binding.btnReplay.setOnClickListener(this)
        binding.btnReturn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            (R.id.btn_replay) -> {
                object : CountDownTimer(3000,200) {
                    override fun onTick(p0: Long) {
                        when (fauxCoin) {
                            (0) -> {
                                binding.imgCoin.setImageResource(R.drawable.ic_crown)
                                fauxCoin++
                            }
                            (1) -> {
                                binding.imgCoin.setImageResource(R.drawable.ic_square)
                                fauxCoin = 0
                            }
                        }
                        binding.txtTossResult.text = "TOSSING THE COIN"
                    }

                    override fun onFinish() {
                        tossedCoin = TCHelper.tossCoin()
                        binding.imgCoin.setImageResource(tossedCoin)
                        tossResult = TCHelper.evaluateResult(tossedCoin)
                        binding.txtTossResult.text = tossResult
                    }
                }.start()
            }
            (R.id.btn_return) -> {}
        }
    }
}