package com.dew.edward.roomword.data

/*
 * Created by Edward on 5/25/2018.
 */

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "word_table")
class Word(@field:PrimaryKey
           val word: String)


