package com.mredrock.cyxbs.freshman.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemHomeBinding

/**
 * @date 2019-08-03
 * @author Override0330
 * @description 初版HomeRecyclerViewAdapter
 */

class HomeRecyclerViewAdapter (private var showList:ArrayList<HomeItem>?): RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>(), View.OnClickListener {
    private lateinit var itemOnClickListener: ItemOnClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.freshman_recycle_item_home,parent,false)
        viewDataBinding.root.setOnClickListener(this)
        return ViewHolder(viewDataBinding)
    }

    override fun getItemCount(): Int {
        return showList?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        showList?.get(position)?.let { holder.bindTo(it) }
        holder.itemView.tag = position
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(homeItem: HomeItem){
            binding.setVariable(BR.item,homeItem)
        }
    }

    override fun onClick(p0: View?) {
        if (p0!=null){
            itemOnClickListener.onItemClick(p0,p0.tag as? Int ?: -1 )
        }
    }

    interface ItemOnClickListener{
        fun onItemClick(view: View, position: Int)
    }

}