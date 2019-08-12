package com.mredrock.cyxbs.freshman.model.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.model.InBackgroundEvent
import com.mredrock.cyxbs.freshman.model.InitDBEvent
import org.greenrobot.eventbus.EventBus
import androidx.room.RoomDatabase.Callback as Callback1

/**
 * @date 2019-08-10
 * @author Override0330
 * @description
 */
@Database(entities = [BusLine::class, Address::class, Map::class,
    Scenery::class, SchoolGroup::class,
    FellowTownsmanGroup::class, OnlineActivity::class,
    HomeItem::class, Route::class, RequireItem::class,RequireCheck::class],version = 1)
abstract class FreshmanDataBase :RoomDatabase(){
    abstract fun freshmanDao():FreshmanDaos
    companion object{
        private var instant :FreshmanDataBase? = null
        @Synchronized
        fun getInstant():FreshmanDataBase{
            if (instant==null){
                LogUtils.d("数据库","创建数据库对象")
                instant = Room.databaseBuilder(BaseApp.context,
                        FreshmanDataBase::class.java,"freshman_database")
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                EventBus.getDefault().postSticky(InBackgroundEvent{
                                    val arrayList = ArrayList<HomeItem>()
                                    for (i in 0 until titleList.size){
                                        arrayList.add(HomeItem(titleList[i], smallList[i]))
                                    }
                                    instant!!.freshmanDao().insertHomeItem(arrayList)
                                    LogUtils.d("数据库","初始化成功")
                                })
                            }
                            override fun onOpen(db: SupportSQLiteDatabase) {
                                super.onOpen(db)
                                LogUtils.d("数据库","读取数据成功")
                            }

                        }).build()
                EventBus.getDefault().postSticky(InitDBEvent())
            }
            return instant!!
        }
    }
}

private val titleList = arrayListOf("入学必备","指路重邮","入学流程","校园指导","线上活动","更多功能","关于我们")
private val smallList = arrayListOf("报到必备 宿舍用品 学习用品",
        "重邮路线 重邮地图",
        "入学步骤 入学地点",
        "宿舍 快递指引",
        "老乡群 专业群",
        "迎新网 新生课表",
        "红岩网校")