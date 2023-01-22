package com.lordsam.virtualcloset.database

import androidx.room.*
import com.lordsam.virtualcloset.data.ClosetData
import kotlinx.coroutines.flow.Flow

@Dao
interface ClosetDatabaseDao {
    @Query("SELECT * from closet_table")
    fun getClosets(): Flow<List<ClosetData>>

    @Query("SELECT * from closet_table where id =:id")
    suspend fun getClosetById(id: String): ClosetData

    @Query("SELECT * from closet_table where category =:category")
    suspend fun getClosetByCategory(category: String): List<ClosetData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(closet: ClosetData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(closet: ClosetData)

    @Query("DELETE from closet_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(closet: ClosetData)
}