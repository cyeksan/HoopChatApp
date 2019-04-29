package io.androidedu.hoop.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calls")
data class CallEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "person_name") val personName: String,
    @ColumnInfo(name = "profile_picture") val profilePicture: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "call_type") val callType: String,
    @ColumnInfo(name = "missed_type") val missedType: String
)
