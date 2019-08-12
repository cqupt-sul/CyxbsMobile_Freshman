package com.mredrock.cyxbs.freshman.view.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseViewModelFragment
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.BR
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.databinding.FreshmanRecycleItemDataDisclosureBinding
import com.mredrock.cyxbs.freshman.model.bean.InstituteData
import com.mredrock.cyxbs.freshman.view.adapter.BaseRecyclerViewAdapter
import com.mredrock.cyxbs.freshman.viewmodel.guide.DataDisclosureFragmentViewModel
import kotlinx.android.synthetic.main.freshman_fragment_data_disclosure.*

/**
 * Created by yyfbe on 2019-08-05
 */
class DataDisclosureFragment : BaseViewModelFragment<DataDisclosureFragmentViewModel>() {
    override val viewModelClass: Class<DataDisclosureFragmentViewModel>
        get() = DataDisclosureFragmentViewModel::class.java

    val adapter = BaseRecyclerViewAdapter<FreshmanRecycleItemDataDisclosureBinding, InstituteData>(
            R.layout.freshman_recycle_item_data_disclosure, BR.instituteItem, null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel.getSchoolListLiveData(viewLifecycleOwner).observe{ list ->
            LogUtils.d("回调","触发更新Recyclerview $list")
            if (adapter.itemCount==0&&list!=null){
                LogUtils.d("回调","更新Recyclerview $list")
                adapter.submitShowList(list.map { InstituteData(it) })
                adapter.onItemOnClickListener = object :BaseRecyclerViewAdapter.OnItemOnClickListener{
                    override fun onItemClick(itemView: View, position: Int) {
                        //跳转，拿参数
                        val args = CampusGuideFragmentArgs(list[position])
                        findNavController().navigate(R.id.freshman_action_freshman_campusguidefragment_to_freshman_datadisclosuredetailfragment,
                                args.toBundle())
                    }
                }
            }
        }
        return inflater.inflate(R.layout.freshman_fragment_data_disclosure, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.d("生命周期","onViewCreated")
        rv_data_disclosure.adapter = adapter
        rv_data_disclosure.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d("生命周期","onStart")
    }
}