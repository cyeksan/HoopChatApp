package io.androidedu.hoop.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import io.androidedu.hoop.entity.CallEntity


@Dao
interface CallDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCall(callEntity: CallEntity)

    @Query("DELETE FROM calls_table")
    fun deleteAll()

    @Delete
    fun deleteItem(item: CallEntity)

    @Query("SELECT * FROM calls_table")
    fun getAllItem(): LiveData<List<CallEntity>>


}