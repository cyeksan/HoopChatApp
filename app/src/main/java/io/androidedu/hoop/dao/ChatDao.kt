package io.androidedu.hoop.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import io.androidedu.hoop.entity.ChatEntity

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChat(chatEntity: ChatEntity)

    @Query("DELETE FROM chats_table")
    fun deleteAll()

    @Delete
    fun deleteItem(item: ChatEntity)

    @Query("SELECT * FROM chats_table")
    fun getAllItem(): LiveData<List<ChatEntity>>


}