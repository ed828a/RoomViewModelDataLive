package com.dew.edward.roomword.data

/*
 * Created by Edward on 5/25/2018.
 */

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import org.jetbrains.anko.doAsync

class WordRepository(application: Application) {

    private val mWordDao: WordDao
    val allWords: LiveData<List<Word>>
    lateinit var db: WordRoomDatabase

    init {
        db = WordRoomDatabase.getDatabase(application.applicationContext)
        mWordDao = db.wordDao()
        allWords = mWordDao.allWords
    }

    fun insert(word: Word) {
        InsertAsyncTask(mWordDao).execute(word)
    }

    // every time when executing Dao insert method, this AsyncTask will be launched
    class InsertAsyncTask(private val mAsyncTaskDao: WordDao) : AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg words: Word): Void? {
            mAsyncTaskDao.insert(words[0])
            return null
        }
    }
}
