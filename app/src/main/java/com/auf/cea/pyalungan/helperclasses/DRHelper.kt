package com.auf.cea.pyalungan.helperclasses

import com.auf.cea.pyalungan.R
import kotlin.random.Random

class DRHelper {
    companion object {
        fun diceRoll():Int{
            val dice = Random.nextInt(0,7)
            return when (dice) {
                (1) -> {R.drawable.die_1}
                (2) -> {R.drawable.die_2}
                (3) -> {R.drawable.die_3}
                (4) -> {R.drawable.die_4}
                (5) -> {R.drawable.die_5}
                (6) -> {R.drawable.die_6}
                else -> {R.drawable.die_1}
            }
        }

        fun resetLuckyNumber():Int {
            return Random.nextInt(0,7)
        }

        fun evaluateResult(diceRolled:Int, luckyNumber:Int):String {
            var dice = -1

            when(diceRolled) {
                (R.drawable.die_1) -> {dice = 1}
                (R.drawable.die_2) -> {dice = 2}
                (R.drawable.die_3) -> {dice = 3}
                (R.drawable.die_4) -> {dice = 4}
                (R.drawable.die_5) -> {dice = 5}
                (R.drawable.die_6) -> {dice = 6}
            }
            return if(dice == luckyNumber) {
                "You got lucky and won!"
            } else {
                "Ouch! Sayang! Better luck next time boss!"
            }
        }
    }
}