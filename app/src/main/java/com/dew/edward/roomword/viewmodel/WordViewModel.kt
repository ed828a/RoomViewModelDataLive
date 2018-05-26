package com.dew.edward.roomword.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import com.dew.edward.roomword.data.Word
import com.dew.edward.roomword.data.WordRepository

/*
 * Created by Edward on 5/25/2018.
 */

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: WordRepository = WordRepository(application)

    val allWords: LiveData<List<Word>>

    init {
        this.allWords = mRepository.allWords
    }

    fun insert(word: Word) {
        mRepository.insert(word)
    }
}
