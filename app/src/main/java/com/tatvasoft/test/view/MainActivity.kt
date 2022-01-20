package com.tatvasoft.test.view

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.tatvasoft.test.R
import com.tatvasoft.test.databinding.DialogAlertBinding
import com.tatvasoft.test.model.Item
import com.tatvasoft.test.viewmodel.MainActivityViewModel
import com.tatvasoft.test.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelFactory = ViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]

        btnClick.setOnClickListener {
            viewModel.randomPositionsList.clear()
            viewModel.itemsAdapter.setData(ArrayList())
            val a = editTextNumberSigned.text.toString()
            if (a.isNotEmpty()) {
                val num = a.toInt()
                if (num > 3) {
                    val isSquare = viewModel.checkPerfectSquare(num)
                    if (isSquare) {
                        Log.e("TAG", "true : " + viewModel.squareRoot(num))
                        viewModel.inputNum = num
                        for (i in 0 until num) {
                            viewModel.randomPositionsList.add(i)
                        }
                        createItems(num)
                    } else {
                        viewModel.itemsAdapter.setData(ArrayList())
                        dialogAlert("Please enter square number")
                    }
                }
            }
        }

        viewModel.btnClickUpdate.observe(this, {
            viewModel.activateRandomBtn()
        })

        viewModel.success.observe(this, {
            if (it) {
                dialogAlert("Wow!, You did it!")
            }
        })
    }

    /**
     * Create dynamic items according to size of array
     * @param totalItem - items of arraylist
     */
    private fun createItems(totalItem: Int) {
        viewModel.positionsList.clear()
        rvButtons.setHasFixedSize(true)
        rvButtons.layoutManager = GridLayoutManager(this, viewModel.squareRoot(totalItem).toInt())
        rvButtons.adapter = viewModel.itemsAdapter
        for (i in 1..totalItem) {
            viewModel.itemsAdapter.add(Item(0))
        }

        viewModel.activateRandomBtn()
    }

    private fun dialogAlert(msg: String) {
        val dialogBinding: DialogAlertBinding =
            DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialog_alert, null, false)
        val dialogAlert = Dialog(this)
        dialogAlert.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogAlert.setContentView(dialogBinding.root)

        dialogAlert.setCanceledOnTouchOutside(true)
        dialogAlert.setCancelable(true)
        if (dialogAlert.window != null) dialogAlert.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        dialogBinding.tvMsg.text = msg

        dialogBinding.tvOk.setOnClickListener { v ->
            dialogAlert.dismiss()
        }
        val window = dialogAlert.window
        window!!.setLayout(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        dialogAlert.show()
    }

}
