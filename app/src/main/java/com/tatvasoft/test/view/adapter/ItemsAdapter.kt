package com.tatvasoft.test.view.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tatvasoft.test.R
import com.tatvasoft.test.databinding.RowItemsBinding
import com.tatvasoft.test.model.Item
import java.util.*

@SuppressLint("NotifyDataSetChanged")
class ItemsAdapter(private val mActivity: Activity, private val btnClickAction: BtnClickAction) :
    BaseAdapter<RecyclerView.ViewHolder?, Item?>() {

    var itemList: MutableList<Item?>? = ArrayList()

    fun addAll(moveResults: List<Item>) {
        for (result in moveResults) {
            add(result)
        }
    }

    fun add(r: Item) {
        itemList!!.add(r)
        notifyDataSetChanged()
    }

    override fun setData(data: List<Item?>?) {
        this.itemList = data!!.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder.create(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as DataViewHolder

        when (itemList?.get(position)!!.status) {
            0 -> {
                vh.binding.btnClick.backgroundTintList =
                    ContextCompat.getColorStateList(mActivity, R.color.white)
            }
            1 -> {
                vh.binding.btnClick.backgroundTintList =
                    ContextCompat.getColorStateList(mActivity, R.color.red)
                vh.binding.btnClick.setOnClickListener {
                    itemList?.get(position)!!.status = 2
                    btnClickAction.onBtnClick(true)
                    notifyDataSetChanged()
                }
            }
            2 -> {
                vh.binding.btnClick.backgroundTintList =
                    ContextCompat.getColorStateList(mActivity, R.color.blue)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return itemList!!.size
    }

    class DataViewHolder(var binding: RowItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(inflater: LayoutInflater?, parent: ViewGroup?): DataViewHolder {
                val itemHomeBlogBinding = RowItemsBinding.inflate(inflater!!, parent, false)
                return DataViewHolder(itemHomeBlogBinding)
            }
        }

    }

}
