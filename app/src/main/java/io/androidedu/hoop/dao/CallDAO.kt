package io.androidedu.hoop.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import io.androidedu.hoop.entity.CallEntity


@Dao
interface CallDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCall(callEntity: CallEntity)

    @Query("DELETE FROM calls")
    fun deleteAll()

    @Delete
    fun deleteItem(item: CallEntity)

    @Query("SELECT * FROM calls")
    fun getAllItem(): LiveData<List<CallEntity>>


}