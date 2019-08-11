package com.mredrock.cyxbs.freshman.model.remote.api

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import kotlin.collections.ArrayList


/**
 * @date 2019-08-10
 * @author Override0330
 * @description
 */

val retrofit = Retrofit.Builder().baseUrl("http://129.28.185.138:9025/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
val request = retrofit.create(GetRequestInterface::class.java)

interface GetRequestInterface{
    @GET("zsqy/json/5")
    fun getBusLineCall(): Observable<BusLineResult>

    @GET("zsqy/json/6")
    fun getCollegeSceneryCall(): Observable<CollegeSceneryResult>

    @GET("zsqy/json/7")
    fun getSchoolGroupCall(): Observable<GroupResult>

    @GET("zsqy/json/77")
    fun getOldFriendGroupCall(): Observable<GroupResult>

    @GET("zsqy/json/8")
    fun getActivityCall(): Observable<ActivityResult>

}

class BusLineResult(val text_1:Address,val text_2:Route){
    class Address(val title:String, val message:String)
    class Route(val title:String, val message:ArrayList<BusLine>){
        class BusLine(val name:String, val route:ArrayList<String>)
    }
}

class CollegeSceneryResult(val text:Context){
    class Context(val title: String,val photo:String,val message:ArrayList<Scenery>){
        class Scenery(val name: String,val photo:String)
    }
}

class GroupResult(val text:List<Group>){
    class Group(val name: String, val data:String)
}

class ActivityResult(val text:List<ActivityItem>){
    class ActivityItem(val name:String,val photo:String,val message:String,val QR:String)
}

