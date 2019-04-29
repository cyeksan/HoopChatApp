package io.androidedu.hoop.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import io.androidedu.hoop.entity.ChatEntity

@Dao
interface ChatDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChat(chatEntity: ChatEntity)

    @Query("DELETE FROM chats")
    fun deleteAll()

    @Delete
    fun deleteItem(item: ChatEntity)

    @Query("SELECT * FROM chats")
    fun getAllItem(): LiveData<List<ChatEntity>>


}