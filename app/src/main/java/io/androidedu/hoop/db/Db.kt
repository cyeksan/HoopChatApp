package io.androidedu.hoop.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.androidedu.hoop.dao.CallDAO
import io.androidedu.hoop.dao.ChatDAO
import io.androidedu.hoop.dao.StatusDAO
import io.androidedu.hoop.entity.CallEntity
import io.androidedu.hoop.entity.ChatEntity
import io.androidedu.hoop.entity.StatusEntity

@Database(entities = [ChatEntity::class, StatusEntity::class, CallEntity::class], version = 1)
abstract class Db : RoomDatabase() {

    abstract fun chatDao(): ChatDAO
    abstract fun statusDao(): StatusDAO
    abstract fun callDao(): CallDAO

    companion object {

        private var INSTANCE: Db? = null

        fun getInstance(context: Context): Db? {

            if (INSTANCE == null) {

                synchronized(Db::class) {

                    INSTANCE = Room.databaseBuilder(context.applicationContext, Db::class.java, "database")
                        .fallbackToDestructiveMigration()
                        /*.addCallback(object: RoomDatabase.Callback() {

                            override fun onOpen(db: SupportSQLiteDatabase) {
                                super.onOpen(db)
                                PopulateDbAsync(INSTANCE!!).execute()
                            }
                        })*/
                        .build()
                }


            }
            return INSTANCE
        }
    }
}


/*
private class PopulateDbAsync internal constructor(db: ChatRoomDb): AsyncTask<Void, Void, Void>() {

    private val mDao: ChatDAO = db.chatDao()

    override fun doInBackground(vararg params: Void?): Void? {
        mDao.deleteAll()
        val chatEntity1 = ChatEntity("Cansu", "Ben Cansu", "Yesterday", R.drawable.ic_girl1)
        mDao.insertChat(chatEntity1)

        mDao.deleteAll()
        val chatEntity2 = ChatEntity("Cansu", "Ben Cansu", "Yesterday", 3)
        mDao.insertChat(chatEntity2)

        return null
    }
}*/
