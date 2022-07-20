package com.pioneers.domain.common


sealed class Resource<T>constructor(val  data: T?=null,val message:String?=null) {
    class Success<T>(data: T):Resource<T>(data)
    class Error<T>(data: T?=null,message: String,):Resource<T>(message=message)
    class Loading<T>(data: T?=null):Resource<T>(data=null)
}