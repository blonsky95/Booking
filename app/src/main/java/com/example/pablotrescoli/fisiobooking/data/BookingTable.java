package com.example.pablotrescoli.fisiobooking.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class BookingTable {


    private BookingTable() {}

    public static final String CONTENT_AUTHORITY = "com.example.pablotrescoli.fisiobooking.data";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_SCORES = "booking";

    public static final class NewBooking implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_SCORES);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SCORES;
        //
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SCORES;

        public final static String TABLE_NAME_BOOKINGS = "local_bookings";



        public final static String COLUMN_ID = "_id";

        public final static String COLUMN_NAME = "name";

        public final static String COLUMN_SLOT = "slot";

        public final static String COLUMN_DAYID = "day_id";





    }
}
