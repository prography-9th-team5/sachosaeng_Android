package com.example.sachosaeng.data.api

import com.example.sachosaeng.data.model.BaseResponse
import com.example.sachosaeng.data.model.category.AllCategoryIconResponse
import com.example.sachosaeng.data.model.category.CategoryResponse
import com.example.sachosaeng.data.remote.util.ApiResult
import retrofit2.http.GET

interface CategoryService {
    @GET("/api/v1/categories")
    suspend fun getAllCategoryList(): ApiResult<BaseResponse<List<CategoryResponse>>>

    @GET("/api/v1/categories/icon-data/all")
    suspend fun getAllCategoryIcon(): ApiResult<BaseResponse<AllCategoryIconResponse>>
}