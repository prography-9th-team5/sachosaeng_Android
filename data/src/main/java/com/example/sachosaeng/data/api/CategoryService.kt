package com.example.sachosaeng.data.api

import com.example.sachosaeng.data.model.category.CategoryResponse
import retrofit2.http.GET

interface CategoryService {
    @GET("/api/v1/categories")
    fun getAllCategoryList() : Result<List<CategoryResponse>>
}