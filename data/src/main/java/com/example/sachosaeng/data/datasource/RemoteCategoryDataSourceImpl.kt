package com.example.sachosaeng.data.datasource

import com.example.sachosaeng.data.api.CategoryService
import javax.inject.Inject

class RemoteCategoryDataSourceImpl @Inject constructor(
    private val categoryService: CategoryService
) : RemoteCategoryDataSource {

}