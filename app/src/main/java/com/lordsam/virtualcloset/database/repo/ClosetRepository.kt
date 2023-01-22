package com.lordsam.virtualcloset.database.repo

import com.lordsam.virtualcloset.data.ClosetData
import com.lordsam.virtualcloset.database.ClosetDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ClosetRepository @Inject constructor(private val closetDatabaseDao: ClosetDatabaseDao) {
    suspend fun addCloset(closet: ClosetData) = closetDatabaseDao.insert(closet)
    suspend fun updateCloset(closet: ClosetData) = closetDatabaseDao.update(closet)
    suspend fun deleteCloset(closet: ClosetData) = closetDatabaseDao.delete(closet)
    suspend fun deleteAll() = closetDatabaseDao.deleteAll()
    suspend fun getClosetsByCategory(category: String): List<ClosetData> = closetDatabaseDao.getClosetByCategory(category)
    fun getAllClosets(): Flow<List<ClosetData>> = closetDatabaseDao.getClosets().flowOn(Dispatchers.IO).conflate()
}