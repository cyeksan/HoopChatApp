package io.androidedu.hoop.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import io.androidedu.hoop.entity.StatusEntity

@Dao
interface StatusDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStatus(statusEntity: StatusEntity)

    @Query("DELETE FROM status_table")
    fun deleteAll()

    @Delete
    fun deleteItem(item: StatusEntity)

    @Query("SELECT * FROM status_table")
    fun getAllItem(): LiveData<List<StatusEntity>>


}