package com.raioncommunity.android.ob2017.model.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arifinfirdaus on 9/1/17.
 */

public class OBDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "OB2017.db";

    public static final String TABLE_APP = "App";
    private static final String COL_1_COUNTER_APP_LAUNCHED = "counter_app_launched";
    private static final String COL_2_VALUE = "value";

    private Context mContext;


    public OBDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO: 9/1/17 on create code

        String queryTableApp = "CREATE TABLE " + TABLE_APP + "(" +
                COL_1_COUNTER_APP_LAUNCHED + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2_VALUE + " INTEGER " +
                ");";
        db.execSQL(queryTableApp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String queryTableApp = "DROP TABLE IF EXISTS " + TABLE_APP;
        db.execSQL(queryTableApp);

    }

    public boolean updateCounter() {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        contentValues.put(COL_2_VALUE, 0);

        long result = db.insert(TABLE_APP, null, contentValues);

        return result != -1;
    }

    public int getTableCount(String tableName) {

        if (tableName == TABLE_APP) {
            String countQuery = "SELECT  * FROM " + TABLE_APP;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            int cnt = cursor.getCount();
            cursor.close();
            return cnt;
        }
        return -1;
    }
}
