package com.mredrock.cyxbs.freshman.view.adapter

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.mredrock.cyxbs.common.utils.extensions.setImageFromUrl
import com.mredrock.cyxbs.freshman.model.remote.api.basePicUrl
import com.youth.banner.loader.ImageLoader

/**
 * Created by yyfbe on 2019-08-12
 */
class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        imageView?.setImageFromUrl((path.toString()))
    }
}