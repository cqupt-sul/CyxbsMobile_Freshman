package com.mredrock.cyxbs.freshman.model.remote.api

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*
import kotlin.collections.ArrayList


/**
 * @date 2019-08-10
 * @author Override0330
 * @description
 */
interface GetRequestInterface{
    @GET("zsqy/json/5")
    fun getBusLineCall(): Observable<BusLineResult>

    @GET("zsqy/json/6")
    fun getCollegeSceneryCall(): Observable<CollegeSceneryResult>

}

class BusLineResult{
    lateinit var text_1:Address
    lateinit var text_2:Route
    class Address(val title:String, val message:String)
    class Route(val title:String, val message:ArrayList<BusLine>){
        class BusLine(val name:String, val route:ArrayList<String>)
    }
}

/**
 * {
"code": 200,
"info": "ok",
"text": {
"title": "重邮2D地图",
"photo": "...",
"message": [{
"name": "八十万",
"photo": "...."
},
{
"name": "仰望八教",
"photo": "...."
}
]
}
}
 */
class CollegeSceneryResult(val text:Context){
    class Context(val title: String,val photo:String,val message:ArrayList<Scenery>){
        class Scenery(val name: String,val photo:String)
    }
}
