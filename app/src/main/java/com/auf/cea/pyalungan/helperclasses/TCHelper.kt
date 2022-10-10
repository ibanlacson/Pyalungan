package com.auf.cea.pyalungan.helperclasses

import com.auf.cea.pyalungan.R
import kotlin.random.Random

class TCHelper {
    companion object {
        fun  tossCoin():Int {
            return when(Random.nextInt(1,3)){
                (1) -> R.drawable.ic_crown
                (2) -> R.drawable.ic_square
                else -> R.drawable.ic_square
            }
        }

        fun evaluateResult(coin:Int):String {
            var result = ""
            when(coin) {
                (R.drawable.ic_crown) -> {
                    result = "You got a CROWN!"
                }
                (R.drawable.ic_square) -> {
                    result = "You got a SQUARE!"
                }
            }
            return result
        }
    }
}