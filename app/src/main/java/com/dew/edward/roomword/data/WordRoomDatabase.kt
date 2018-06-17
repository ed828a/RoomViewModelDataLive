package com.dew.edward.roomword.data


import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask

/*
 * Created by Edward on 5/25/2018.
 */


@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    // operating database are done asynchronously
    private class PopulateDbAsync(db: WordRoomDatabase) : AsyncTask<Void, Void, Void>() {

        private var mDao: WordDao = db.wordDao()

        override fun doInBackground(vararg voids: Void): Void? {
            mDao.deleteAll()
            var word = Word("Hello")
            mDao.insert(word)
            word = Word("World")
            mDao.insert(word)
            word = Word("Room")
            mDao.insert(word)
            word = Word("Fuck up")
            mDao.insert(word)
            word = Word("too complicated")
            mDao.insert(word)
            return null
        }
    }

    companion object {

        // make this class a singleton
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        // factory method
        fun getDatabase(context: Context): WordRoomDatabase =
                INSTANCE ?: synchronized(WordRoomDatabase::class.java) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, WordRoomDatabase::class.java, "word_database.db")
                        .addCallback(sRoomDatabaseCallback) // for initial data purpose, just Singleton doesn't need this.
                        .build()


        // for the purpose of initialization
        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                PopulateDbAsync(INSTANCE!!).execute()
            }
        }
    }

}

