package com.mredrock.cyxbs.freshman.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemHomeBinding

class HomeRecyclerViewAdapter (private var showList:ArrayList<HomeItem>?): RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.freshman_recycle_item_home,parent,false)
        return ViewHolder(viewDataBinding)
    }

    override fun getItemCount(): Int {
        return showList?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        showList?.get(position)?.let { holder.bindTo(it) }
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(homeItem: HomeItem){
            (binding as FreshmanRecycleItemHomeBinding).item
            binding.setVariable(BR.item,homeItem)
        }
    }
}