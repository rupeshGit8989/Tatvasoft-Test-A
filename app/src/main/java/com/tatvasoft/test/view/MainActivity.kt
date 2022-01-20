package com.tatvasoft.test.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.tatvasoft.test.R
import com.tatvasoft.test.model.Item
import com.tatvasoft.test.viewmodel.MainActivityViewModel
import com.tatvasoft.test.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.floor
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelFactory = ViewModelFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]


    }

    /**
     * Create dynamic items according to size of array
     * @param totalItem - items of arraylist
     * @param spanCount - size of row*column (ex:-4*4)
     */
    private fun createItems(totalItem: Int,spanCount: Int) {
        rvButtons.setHasFixedSize(true)
        rvButtons.layoutManager = GridLayoutManager(this, spanCount)
        rvButtons.adapter = viewModel.itemsAdapter
        for (i in 1..totalItem) {
            viewModel.itemsAdapter.add(Item(0))
        }
    }

    /**
     * Check perfect square and return True or False
     */
    private fun checkPerfectSquare(x: Int): Boolean {
        val sq = sqrt(x.toDouble())
        return sq - floor(sq) == 0.0
    }

}
