package com.example.workoutme

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Update
    suspend fun update(historyEntity: HistoryEntity)

    @Delete
    suspend fun delete(historyEntity: HistoryEntity)

    @Query("SELECT * FROM `history-table`")
    fun fetchAllDates(): Flow<List<HistoryEntity>>

    @Query("SELECT * FROM `history-table` WHERE date=:id")
    fun fetchHistoryByDate(id: Int): Flow<HistoryEntity>
}