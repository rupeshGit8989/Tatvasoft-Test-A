package com.tatvasoft.test.view.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseAdapter<T : ViewHolder?, D> : RecyclerView.Adapter<T>() {
    abstract fun setData(data: List<D>?)
}