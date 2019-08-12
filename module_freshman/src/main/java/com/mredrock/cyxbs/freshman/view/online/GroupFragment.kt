package com.mredrock.cyxbs.freshman.view.online

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.freshman.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.model.item.GroupItem
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.view.goto.CopySuccessDialogFragment
import com.mredrock.cyxbs.freshman.viewmodel.online.GroupFragmentViewModel
import kotlinx.android.synthetic.main.freshman_fragment_group.*

/**
 * @date 2019-08-08
 * @author Override0330
 * @description
 */
class GroupFragment : BaseViewModelFragment<GroupFragmentViewModel>() {
    override val viewModelClass: Class<GroupFragmentViewModel>
        get() = GroupFragmentViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.freshman_fragment_group, container, false)
    }

    fun tag () = arguments?.getString("tag")

    val adapter = BaseRecyclerViewAdapter<com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemOnlineGroupBinding, GroupItem>(R.layout.freshman_recycle_item_online_group,
            BR.group,
            null)

    companion object {
        //创建包含一个Tag参数的Fragment
        fun getInstantWithTag(tag: String): GroupFragment {
            val groupFragment = GroupFragment()
            val bundle = Bundle()
            bundle.putString("tag", tag)
            groupFragment.arguments = bundle
            groupFragment.tag()
            return groupFragment
        }
    }

    override fun onStart() {
        super.onStart()
        val drawable = resources.getDrawable(R.drawable.freshman_fragment_group_search_et, null)
        drawable.setBounds(0, 0, 40, 40)
        et_search.setCompoundDrawables(drawable, null, null, null)
        rv_group.layoutManager = LinearLayoutManager(this.context)
        rv_group.adapter = adapter
        if (tag() == "学院群"){
            et_search.hint = "找不到学院？试试搜索"
            viewModel.getSchoolGroup(viewLifecycleOwner).observe{
                if (it != null){
                    adapter.submitShowList(it)
                }
            }
        }else if(tag() == "老乡群"){
            et_search.hint = "找不到老乡群？试试搜索"
            viewModel.getOldFriendGroup(viewLifecycleOwner).observe{
                if (it != null){
                    adapter.submitShowList(it)
                }
            }
        }
        adapter.onItemOnClickListener = object : BaseRecyclerViewAdapter.OnItemOnClickListener {
            override fun onItemClick(itemView: View, position: Int) {
                val copySuccessDialogFragment = CopySuccessDialogFragment()
                copySuccessDialogFragment.show(fragmentManager,"copy success")
            }
        }
    }


    fun startSearch(){
        val adapter = BaseRecyclerViewAdapter<com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemGroupSearchBinding,GroupItem>(R.layout.freshman_recycle_item_group_search,
                BR.group,
                null)
        adapter.onItemOnClickListener = object : BaseRecyclerViewAdapter.OnItemOnClickListener {
            override fun onItemClick(itemView: View, position: Int) {
                val copySuccessDialogFragment = CopySuccessDialogFragment()
                copySuccessDialogFragment.show(fragmentManager,"copy success")
            }
        }
        rv_group_search.layoutManager = LinearLayoutManager(this.context)
        rv_group_search.adapter = adapter
    }
}