package io.androidedu.hoop.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chats")
data class ChatEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "person_name") val personName: String,
    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "profile_picture") val profilePicture: Int
)