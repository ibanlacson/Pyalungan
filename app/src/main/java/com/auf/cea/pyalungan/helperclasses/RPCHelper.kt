package com.auf.cea.pyalungan.helperclasses

import com.auf.cea.pyalungan.R
import kotlin.random.Random

class RPCHelper {
    companion object{

        fun getImage(): Int {
            return when (Random.nextInt(1,4)){
                (1) -> { R.drawable.ic_stone}
                (2) -> { R.drawable.ic_paper}
                (3) -> { R.drawable.ic_scissors}
                else -> { R.drawable.ic_paper}
            }
        }

        fun evaluateResult(computerPick:Int, playerPick:Int):String{
            val rock = R.drawable.ic_stone
            val paper = R.drawable.ic_paper
            val scissors = R.drawable.ic_scissors
            var result = ""

            if ((computerPick == rock && playerPick == rock) || (computerPick == paper && playerPick == paper) || (computerPick == scissors && playerPick == scissors)){
                result = "IT'S A DRAW!"
            } else if ((computerPick == rock && playerPick == paper)) {
                result = "You win! Paper defeats rock "
            } else if ((computerPick == rock && playerPick == scissors)) {
                result = "COMPUTER WINS! Rock defeats scissors"
            } else if ((computerPick == paper && playerPick == rock)) {
                result = "COMPUTER WINS! Paper defeats rock"
            } else if ((computerPick == paper && playerPick == scissors)) {
                result = "You win! Scissors defeats paper"
            } else if ((computerPick == scissors && playerPick == rock)) {
                result = "You win! Rock defeats scissors"
            } else if ((computerPick == scissors && playerPick == paper)) {
                result = "COMPUTER WINS! Scissors defeats paper"
            }
            return result
        }
    }
}