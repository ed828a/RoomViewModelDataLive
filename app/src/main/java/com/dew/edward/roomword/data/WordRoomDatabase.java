package com.dew.edward.roomword.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/*
 * Created by Edward on 5/25/2018.
 */


@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    // make this class a singleton
    private static WordRoomDatabase INSTANCE;

    // factory method
    public static WordRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class){
                if (INSTANCE == null) {
                    // Create database here
                     INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                             WordRoomDatabase.class, "word_database")
                             .addCallback(sRoomDatabaseCallback)
                             .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        private final WordDao mDao;

        public PopulateDbAsync(WordRoomDatabase db) {
            this.mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            Word word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Room");
            mDao.insert(word);
            word = new Word("Fuck up");
            mDao.insert(word);
            word = new Word("too complicated");
            mDao.insert(word);
            return null;
        }
    }

}


