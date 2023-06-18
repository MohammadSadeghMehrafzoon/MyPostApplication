package ir.misterdeveloper.mypostsapplication.database;

import static ir.misterdeveloper.mypostsapplication.util.ConstansKt.DB_NAME;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Favorite.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


    public abstract DaoDatabase daoDatabase();

    private static AppDatabase instance = null;

    public static synchronized AppDatabase getInstance(Context context) {


        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries().build();
        }


        return instance;

    }


}