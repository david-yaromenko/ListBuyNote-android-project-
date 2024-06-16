package com.example.listbuyapp.Utils




//4.45.


object LiniarProgress {

    fun getProgress(allItemsCount: Int, selectedItemsCount: Int): Float{
        return if(selectedItemsCount == 0){
            0.0f
        } else {
            selectedItemsCount.toFloat() / allItemsCount.toFloat()
        }
    }

}