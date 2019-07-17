package com.example.S10174164_Week11Practical;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class DbHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    // file name to be saved for the database
    private static final String DATABASE_NAME = "accountDB.dbHandler";

    // Table Name
    public static final String ACCOUNTS = "Accounts";

    // Column Name
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_PASSWORD = "Password";

    /* Will scan to see if there is any database file.
    If file cannot be found, the onCreate method would be run */
    public DbHandler(Context c, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + ACCOUNTS + " (" + COLUMN_USERNAME + "TEXT," + COLUMN_PASSWORD + "TEXT)";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        onCreate(db);
    }

    public void addAccount(Account a) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSWORD, a.getPassword());
        values.put(COLUMN_USERNAME, a.getUsername());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ACCOUNTS, null, values);
        db.close();
    }
}
