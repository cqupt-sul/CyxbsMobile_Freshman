package com.mredrock.cyxbs.freshman.model.remote.api

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


/**
 * @date 2019-08-10
 * @author Override0330
 * @description
 */
interface GetRequestInterface{
    @GET("zsqy/json/5")
    fun getCall(): Observable<BusLineResult>

}
class BusLineResult{
    lateinit var text_1:Address
    lateinit var text_2:Route
    class Address(val title:String, val message:String)
    class Route(val title:String, val message:ArrayList<BusLine>){
        class BusLine(val name:String, val route:ArrayList<String>)
    }
}
