package io.androidedu.hoop.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "status")
data class StatusEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "person_name") val personName: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "profile_picture") val profilePicture: Int
)