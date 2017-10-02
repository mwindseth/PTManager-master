package database.CustomerDbSchema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import database.CustomerDbSchema.CustomerDbSchema.CustomerTable;


public class CustomerBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_CUSTOMERS = "customerBase.db";

    public CustomerBaseHelper(Context context) {
        super(Context, DATABASE_CUSTOMERS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CustomerTable.CUSTOMERS + "(" +
                "_id integer primary key autoincrement, " +
                CustomerTable.Cols.UUID + ", " +
                CustomerTable.Cols.NAME + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    {
       
    }

}
