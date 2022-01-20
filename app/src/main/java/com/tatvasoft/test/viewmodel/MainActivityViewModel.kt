package com.tatvasoft.test.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tatvasoft.test.view.adapter.BtnClickAction
import com.tatvasoft.test.view.adapter.ItemsAdapter

class MainActivityViewModel(mActivity: Activity) : ViewModel() {

    var itemsAdapter: ItemsAdapter
    val btnClickUpdate: MutableLiveData<Boolean> = MutableLiveData()

    init {
        itemsAdapter = ItemsAdapter(mActivity, object : BtnClickAction {
            override fun onBtnClick(isClick: Boolean) {
                btnClickUpdate.value = isClick
            }
        })
    }


}