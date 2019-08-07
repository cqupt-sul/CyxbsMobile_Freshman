package com.mredrock.cyxbs.freshman.viewmodel.helper

import android.util.Log
import android.widget.ImageView

import androidx.databinding.BindingAdapter

import com.mredrock.cyxbs.common.utils.extensions.setImageFromUrl


/**
 * Created by yyfbe on 2019-08-05
 */
class DataBindingImageLoaderHelper {

    companion object{
        @JvmStatic
        @BindingAdapter("picUrl")
        fun loadImage(imageView: ImageView, url: String?) {
            imageView.setImageFromUrl(url)
        }
    }

}