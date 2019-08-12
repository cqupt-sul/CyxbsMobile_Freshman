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

val retrofit = Retrofit.Builder().baseUrl("http://129.28.185.138:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
val request = retrofit.create(GetRequestInterface::class.java)

val imageBaseUrl = "http://129.28.185.138:8080/zsqy/image/"

interface GetRequestInterface{

    @GET("zsqy/json/3")
    fun getDormitoryCall(): Observable<DormitoryResult>

    @GET("zsqy/json/33")
    fun getExpressCall(): Observable<ExpressResult>

    @GET("zsqy/json/4")
    fun getSubjectCall(): Observable<SubjectResult>

    @GET("zsqy/json/44")
    fun getBoyAndGirlCall(): Observable<BoyAndGirlResult>

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

    @GET("zsqy/json/1")
    fun getRequireCall(): Observable<RequirementResult>

}

class BusLineResult(val text_1:Address,val text_2:Route){
    class Address(val title:String, val message:String)
    class Route(val title:String, val message:ArrayList<BusLine>){
        class BusLine(val name:String, val route:ArrayList<String>){
            fun getRoute():String{
                var route = ""
                for (i in 0 until this.route.size){
                    route += this.route[i]
                    if (i!=(this.route.size-1)) {
                        route += "\n\n"
                    }
                }
                return route
            }
        }
    }
}

class CollegeSceneryResult(val text: Context) {
    class Context(val title: String, val photo: String, val message: ArrayList<Scenery>) {
        class Scenery(val name: String, val photo: String)
    }
}

class GroupResult(val text: List<Group>) {
    class Group(val name: String, val data: String)
}

class ActivityResult(val text: List<ActivityItem>) {
    class ActivityItem(val name: String, val photo: String, val message: String, val QR: String)
}

class RequirementResult(val text: List<Group>) {
    class Group(val title: String, val data: List<Group2>) {
        class Group2(val name: String, val detail: String)
    }
}

class DormitoryResult(val text:List<Content>){
    class Content(val title:String,val message:List<Item>){
        class Item(val name:String,val detail:String,val photo:List<String>){
            fun getPhoto():String{
                var url = ""
                for (i in 0 until photo.size){
                    url += imageBaseUrl+photo[i]
                    if (i!=(photo.size-1)) {
                        url += ","
                    }
                }
                return url
            }
        }
    }
}

class ExpressResult(val text:List<Company>){
    class Company(val name:String, val message: List<Address>){
        class Address(val title: String,val detail: String,val photo: String){}
    }
}

class SubjectResult(val text:List<Content>){
    class Content(val name: String,val message: List<Subject>){
        class Subject(val subject:String,val data:String)
    }
}

class BoyAndGirlResult(val text:List<Content>){
    class Content(val name: String,val boy:String,val girl:String)
}

