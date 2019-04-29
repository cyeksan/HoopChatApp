package io.androidedu.hoop.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import io.androidedu.hoop.entity.StatusEntity

@Dao
interface StatusDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStatus(statusEntity: StatusEntity)

    @Query("DELETE FROM status")
    fun deleteAll()

    @Delete
    fun deleteItem(item: StatusEntity)

    @Query("SELECT * FROM status")
    fun getAllItem(): LiveData<List<StatusEntity>>


}