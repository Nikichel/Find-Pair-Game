package com.example.pairgame.recycleView

import android.graphics.Color

data class GameCard(val imgId: Int, var isOpen: Boolean = true, var isSelected: Boolean = false){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as GameCard
        return imgId == other.imgId
    }
}
