package com.dew.edward.roomword.data

/*
 * Created by Edward on 5/25/2018.
 */

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface WordDao {

    @get:Query("SELECT * FROM word_table")
    val allWords: LiveData<List<Word>>  // whenever words changes, the function will execute

    @Query("DELETE FROM word_table")
    fun deleteAll()

    //    @Query("SELECT * FROM word_table where firstName LIKE :first AND lastName LIKE :last LIMIT 5")
    //    Word findByFirstNameAndLastName(String first, String last);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)

    @Update
    fun update(word: Word)

    @Delete
    fun deleteWord(word: Word)

}
