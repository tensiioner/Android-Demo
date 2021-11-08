package com.tensioner.application.Interface

interface LoginResultCallBacks {
    fun onSuccess(message:String)
    fun OnError(message:String)
    fun OnRegister()
}