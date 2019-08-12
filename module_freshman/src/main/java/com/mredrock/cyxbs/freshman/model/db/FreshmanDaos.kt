package com.mredrock.cyxbs.freshman.model.db

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * @date 2019-08-10
 * @author Override0330
 * @description
 */

@Dao
interface FreshmanDaos {
    //增
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBusLine(busLine: BusLine)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBusline(busLineList: List<BusLine>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoute(route: List<Route>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAddress(address: Address)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMap(map: Map)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScenery(scenery: Scenery)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScenery(sceneryList: List<Scenery>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchoolGroup(schoolGroup: SchoolGroup)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchoolGroup(schoolGroupList: List<SchoolGroup>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFellowTownsman(fellowTownsmanGroupGroup: FellowTownsmanGroup)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFellowTownsman(fellowTownsmanGroupGroupList: List<FellowTownsmanGroup>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOnlineActivity(onlineActivity: OnlineActivity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOnlineActivity(OnlineActivityList: List<OnlineActivity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHomeItem(homeItem: HomeItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHomeItem(HomeItemList: List<HomeItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDormitory(dormitory: Dormitory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDormitory(dormitory: List<Dormitory>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCanteen(canteen: Canteen)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCanteen(canteen: List<Canteen>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpressAddress(expressAddress: ExpressAddress)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpressAddress(expressAddress: List<ExpressAddress>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSubject(subject: List<Subject>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBoyAndGirl(boyAndGirl: BoyAndGirl)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBoyAndGirl(boyAndGirl: List<BoyAndGirl>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRequireItem(requireItem: RequireItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRequireItem(RequireItemList: List<RequireItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRequireCheck(check: RequireCheck)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRequireCheck(checkList: List<RequireCheck>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrderItem(orderList: List<OrderItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrderItem(order: OrderItem)

    //删
    @Delete
    fun deleteBusLine(busLine: BusLine)

    @Delete
    fun deleteBusLine(busLineList: List<BusLine>)

    @Delete
    fun deleteAddress(address: Address)

    @Delete
    fun deleteMap(map: Map)

    @Delete
    fun deleteScenery(scenery: Scenery)

    @Delete
    fun deleteSchoolGroup(schoolGroup: SchoolGroup)

    @Delete
    fun deleteSchoolGroup(schoolGroupList: List<SchoolGroup>)

    @Delete
    fun deleteFellowTownsman(fellowTownsmanGroupGroup: FellowTownsmanGroup)

    @Delete
    fun deleteFellowTownsman(fellowTownsmanGroupGroupList: List<FellowTownsmanGroup>)

    @Delete
    fun deleteOnlineActivity(onlineActivity: OnlineActivity)

    @Delete
    fun deleteOnlineActivity(OnlineActivityList: List<OnlineActivity>)

    @Delete
    fun deleteHomeItem(homeItem: HomeItem)

    @Delete
    fun deleteHomeItem(HomeItemList: List<HomeItem>)

    @Delete
    fun deleteRequireItem(RequireItemList: List<RequireItem>)

    @Delete
    fun deleteRequireItem(requireItem: RequireItem)

    @Delete
    fun deleteRequireCheck(requireCheck: RequireCheck)

    @Delete
    fun deleteRequireCheck(requireCheckList: List<RequireCheck>)

    @Delete
    fun deleteOrderItem(orderList: List<OrderItem>)


    //改
    @Update
    fun updateBusLine(busLine: BusLine)

    @Update
    fun updateBusline(busLineList: List<BusLine>)

    @Update
    fun updateAddress(address: Address)

    @Update
    fun updateMap(map: Map)

    @Update
    fun updateScenery(scenery: Scenery)

    @Update
    fun updateScenery(sceneryList: List<Scenery>)

    @Update
    fun updateSchoolGroup(schoolGroup: SchoolGroup)

    @Update
    fun updateSchoolGroup(schoolGroupList: List<SchoolGroup>)

    @Update
    fun updateFellowTownsman(fellowTownsmanGroupGroup: FellowTownsmanGroup)

    @Update
    fun updateFellowTownsman(fellowTownsmanGroupGroupList: List<FellowTownsmanGroup>)

    @Update
    fun updateOnlineActivity(onlineActivity: OnlineActivity)

    @Update
    fun updateOnlineActivity(OnlineActivityList: List<OnlineActivity>)

    @Update
    fun updateHomeItem(homeItem: HomeItem)

    @Update
    fun updateHomeItem(HomeItemList: List<HomeItem>)

    @Update
    fun updateRequireItem(RequireItemList: List<RequireItem>)

    @Update
    fun updateRequireCheck(checkList: List<RequireCheck>)

    @Update
    fun updateOrderItem(orderList: List<OrderItem>)

    //查
    @Query("SELECT * FROM bus_line")
    fun getAllBusLine(): LiveData<List<BusLine>>

    @Query("SELECT * FROM bus_line WHERE name = :name")
    fun getBusLine(name: String): LiveData<BusLine>

    @Query("SELECT * FROM route")
    fun getAllRoute(): LiveData<List<Route>>

    @Query("SELECT * FROM address WHERE title = :name")
    fun getAddress(name: String): LiveData<Address>

    @Query("SELECT * FROM maps WHERE title = :name")
    fun getMap(name: String): LiveData<Map>

    @Query("SELECT * FROM scenery")
    fun getAllScenery(): LiveData<List<Scenery>>

    @Query("SELECT * FROM scenery WHERE name = :name")
    fun getScenery(name: String): LiveData<Scenery>

    @Query("SELECT * FROM school_group")
    fun getAllSchoolGroup(): LiveData<List<SchoolGroup>>

    @Query("SELECT * FROM school_group WHERE name = :name")
    fun getSchoolGroup(name: String): LiveData<SchoolGroup>

    @Query("SELECT * FROM fellow_towns_man")
    fun getAllFellowTownsMan(): LiveData<List<FellowTownsmanGroup>>

    @Query("SELECT * FROM fellow_towns_man WHERE name = :name")
    fun getFellowTownMan(name: String): LiveData<FellowTownsmanGroup>

    @Query("SELECT * FROM online_activity")
    fun getAllOnlineActivity(): LiveData<List<OnlineActivity>>

    @Query("SELECT * FROM online_activity WHERE name = :name")
    fun getOnlineActivity(name: String): LiveData<OnlineActivity>

    @Query("SELECT * FROM home_item")
    fun getAllHomeItem(): LiveData<List<HomeItem>>

    @Query("SELECT * FROM home_item WHERE title = :title")
    fun getHomeItem(title: String): LiveData<HomeItem>

    @Query("SELECT * FROM dormitory")
    fun getAllDormitory(): LiveData<List<Dormitory>>

    @Query("SELECT * FROM dormitory WHERE name = :name")
    fun getDormitoryByName(name: String): LiveData<Dormitory>

    @Query("SELECT * FROM canteen")
    fun getAllCanteen(): LiveData<List<Canteen>>

    @Query("SELECT * FROM canteen WHERE name = :name")
    fun getCanteenByName(name: String): LiveData<Canteen>

    @Query("SELECT * FROM express WHERE name =:name")
    fun getExpressByName(name: String): LiveData<List<ExpressAddress>>

    @Query("SELECT * FROM EXPRESS")
    fun getAllExpress(): LiveData<List<ExpressAddress>>

    @Query("SELECT * FROM subject WHERE name =:school")
    fun getSubjectBySchool(school: String): LiveData<List<Subject>>

    @Query("SELECT * FROM subject")
    fun getAllSubject(): LiveData<List<Subject>>

    @Query("SELECT * FROM boy_and_girl WHERE name =:school")
    fun getBoyAndGirlBySchool(school: String): LiveData<BoyAndGirl>

    @Query("SELECT * FROM require_item WHERE name = :name")
    fun getRequireItem(name: String): LiveData<List<RequireItem>>

    @Query("SELECT * FROM require_item WHERE title_id = :title")
    fun getRequireItemByTitle(title: String): LiveData<List<RequireItem>>

    @Query("SELECT * FROM require_item")
    fun getAllRequireItem(): LiveData<List<RequireItem>>

    @Query("SELECT * FROM require_check")
    fun getAllRequireCheck(): LiveData<List<RequireCheck>>

    @Query("SELECT * FROM order_item")
    fun getOrderItem(): LiveData<List<OrderItem>>


}