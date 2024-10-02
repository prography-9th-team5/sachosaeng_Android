package com.sachosaeng.app.data.api

import com.sachosaeng.app.data.model.BaseResponse
import com.sachosaeng.app.data.model.category.AllCategoryIconResponse
import com.sachosaeng.app.data.model.category.CategoriesResponse
import com.sachosaeng.app.data.model.category.SetCategoryRequest
import com.sachosaeng.app.data.remote.util.ApiResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface CategoryService {
    @GET("/api/v1/categories")
    suspend fun getAllCategoryList(): ApiResult<BaseResponse<CategoriesResponse>>
    @GET("/api/v1/categories/icon-data/all")
    suspend fun getAllCategoryIcon(): ApiResult<BaseResponse<AllCategoryIconResponse>>
    @GET("/api/v1/my-categories")
    suspend fun getMyCategoryList(): ApiResult<BaseResponse<CategoriesResponse>>
    @PUT("/api/v1/my-categories")
    suspend fun setMyCategoryList(
        @Body categoryIds: SetCategoryRequest
    ): ApiResult<BaseResponse<Unit>>
}