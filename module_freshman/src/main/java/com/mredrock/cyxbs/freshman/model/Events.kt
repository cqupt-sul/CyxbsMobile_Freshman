package com.mredrock.cyxbs.freshman.model

/**
 * @date 2019-08-10
 * @author Override0330
 * @description
 */

class ShowOrHindToolsBarEvent(val isShow: Boolean)

class InitDBEvent

class SetToolsBarTitle(val title:String)

class InBackgroundEvent(val event:()->Unit)

class ShowActivityDialog(val photoUrl:String,val message:String)

class DeleteRequire(val requireInfo :String)

class AddRequire(val requireInfo: String)

class CurrentDeleteRequire(val requireInfo :String)

class CurrentAddRequire(val requireInfo :String)
