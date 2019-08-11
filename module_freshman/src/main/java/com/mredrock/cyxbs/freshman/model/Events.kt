package com.mredrock.cyxbs.freshman.model

/**
 * @date 2019-08-10
 * @author Override0330
 * @description
 */

class ShowOrHindToolsBarEvent(val isShow: Boolean)

class InitDBEvent

class InBackgroundEvent(val event:()->Unit)
