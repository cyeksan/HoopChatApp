package io.androidedu.hoop.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.androidedu.hoop.dao.CallDao
import io.androidedu.hoop.dao.ChatDao
import io.androidedu.hoop.dao.StatusDao
import io.androidedu.hoop.entity.CallEntity
import io.androidedu.hoop.entity.ChatEntity
import io.androidedu.hoop.entity.StatusEntity

@Database(entities = [ChatEntity::class, StatusEntity::class, CallEntity::class], version = 1)
abstract class Db : RoomDatabase() {

    abstract fun chatDao(): ChatDao
    abstract fun statusDao(): StatusDao
    abstract fun callDao(): CallDao

    companion object {

        private var INSTANCE: Db? = null

        fun getInstance(context: Context): Db? {

            if (INSTANCE == null) {

                synchronized(Db::class) {

                    INSTANCE = Room.databaseBuilder(context.applicationContext, Db::class.java, "chat_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }


            }
            return INSTANCE
        }
    }
}
