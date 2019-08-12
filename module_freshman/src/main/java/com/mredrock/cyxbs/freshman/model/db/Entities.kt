package com.mredrock.cyxbs.freshman.model.db


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * @date 2019-08-10
 * @author Override0330
 * @description
 */

@Entity(tableName = "bus_line")
class BusLine(@PrimaryKey var name: String, var route: String)

@Entity(tableName = "route")
class Route(@PrimaryKey var id: Int, var route: String)

@Entity(tableName = "address")
class Address(@PrimaryKey var title: String, var address: String)

@Entity(tableName = "maps")
class Map(@PrimaryKey var title: String,
          @ColumnInfo(name = "photo_url") var photoUrl: String)

@Entity(tableName = "scenery")
class Scenery(@PrimaryKey var name: String,
              @ColumnInfo(name = "photo_url") var photoUrl: String)

@Entity(tableName = "school_group")
class SchoolGroup(@PrimaryKey var name: String,
                  @ColumnInfo(name = "group_id") var groupId: String)

@Entity(tableName = "fellow_towns_man")
class FellowTownsmanGroup(@PrimaryKey var name: String,
                          @ColumnInfo(name = "group_id") var groupId: String)

@Entity(tableName = "online_activity")
class OnlineActivity(@PrimaryKey var name: String,
                     @ColumnInfo(name = "photo_url") var photoUrl: String,
                     var message: String,
                     @ColumnInfo(name = "qr_url") var qrUrl: String)

@Entity(tableName = "home_item")
class HomeItem(@PrimaryKey var title: String, var content: String)

@Entity(tableName = "dormitory")
class Dormitory(@PrimaryKey var name: String,
                var detail: String,
                var url: String)

@Entity(tableName = "canteen")
class Canteen(@PrimaryKey var name: String,
              var detail: String,
              var url: String)

@Entity(tableName = "express", primaryKeys = ["name", "title"])
class ExpressAddress(var name: String,
                     var title: String,
                     var detail: String,
                     var photoUrl: String)

@Entity(tableName = "subject", primaryKeys = ["name", "subject"])
class Subject(var name: String,
              var subject: String,
              var data: String)

@Entity(tableName = "boy_and_girl")
class BoyAndGirl(@PrimaryKey var name: String,
                 var boy: String,
                 var girl: String)


@Entity(tableName = "require_item")
class RequireItem(@PrimaryKey var name: String,
                  @ColumnInfo(name = "title_id") var title: String,
                  @ColumnInfo(name = "detail_id") var detail: String)

@Entity(tableName = "require_check")
class RequireCheck(@PrimaryKey var name: String,
                   @ColumnInfo(name = "check") var check: Boolean)

@Entity(tableName = "order_item")
class OrderItem(@PrimaryKey var title: String,
                @ColumnInfo(name = "message") var message: String,
                @ColumnInfo(name = "photo") var photo: String,
                @ColumnInfo(name = "detail") var detail: String)