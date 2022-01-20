package com.tatvasoft.test.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tatvasoft.test.view.adapter.BtnClickAction
import com.tatvasoft.test.view.adapter.ItemsAdapter
import java.util.*
import kotlin.math.floor
import kotlin.math.sqrt

class MainActivityViewModel(mActivity: Activity) : ViewModel() {

    var itemsAdapter: ItemsAdapter
    val btnClickUpdate: MutableLiveData<Boolean> = MutableLiveData()
    val success: MutableLiveData<Boolean> = MutableLiveData()
    var randomPositionsList: ArrayList<Int> = ArrayList()
    var positionsList: ArrayList<Int> = ArrayList()
    var inputNum: Int = 0

    init {
        itemsAdapter = ItemsAdapter(mActivity, object : BtnClickAction {
            override fun onBtnClick(isClick: Boolean) {
                btnClickUpdate.value = isClick
            }
        })
    }


    /**
     * Generate Random position to activate button
     * Number between min and max
     * @param min - Start from min
     * @param max - End to max
     */
    fun generateRandomPosition(min: Int, max: Int): Int {
        val r = Random()
        return r.nextInt(max - min + 1) + min
    }

    /**
     * Check perfect square and return True or False
     */
    fun checkPerfectSquare(x: Int): Boolean {
        val sq = sqrt(x.toDouble())
        return sq - floor(sq) == 0.0
    }

    /**
     * Check square root of input number
     */
    fun squareRoot(num: Int): Double {
        var t: Int
        var sqrtroot = num / 2
        do {
            t = sqrtroot
            sqrtroot = (t + num / t) / 2
        } while (t - sqrtroot != 0)
        return sqrtroot.toDouble()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun activateRandomBtn() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (randomPositionsList.size > 0) {
                val pos = generateRandomPosition(0, randomPositionsList.size - 1)
                positionsList.add(randomPositionsList[pos])
                itemsAdapter.itemList?.get(randomPositionsList[pos])!!.status = 1
                itemsAdapter.notifyDataSetChanged()
                randomPositionsList.remove(randomPositionsList[pos])
            } else {
                success.value = true
            }
        }, 500)
    }

}