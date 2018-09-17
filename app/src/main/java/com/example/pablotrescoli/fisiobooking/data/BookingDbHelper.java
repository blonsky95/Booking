package com.example.pablotrescoli.fisiobooking.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookingDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = BookingDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "score_table.db";

    private static final int DATABASE_VERSION = 1;

    public BookingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_BOOKING_TABLE = "CREATE TABLE " + BookingTable.NewBooking.TABLE_NAME_BOOKINGS + " ("
                + BookingTable.NewBooking.COLUMN_ID + " TEXT NOT NULL, "
                + BookingTable.NewBooking.COLUMN_NAME + " TEXT NOT NULL, "
                + BookingTable.NewBooking.COLUMN_SLOT + " TEXT NOT NULL, "
                + BookingTable.NewBooking.COLUMN_DAYID + " TEXT NOT NULL);";


        sqLiteDatabase.execSQL(SQL_CREATE_BOOKING_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
