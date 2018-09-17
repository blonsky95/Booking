package com.example.pablotrescoli.fisiobooking;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablotrescoli.fisiobooking.data.BookingDbHelper;
import com.example.pablotrescoli.fisiobooking.data.BookingTable;

public class local_bookings extends AppCompatActivity {

    String LOG_TAG = "LOG TAG: ";

    public static int score_max;


    private static String bookingNameStr = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_booking);
        loadBookings();
    }

    BookingDbHelper mDbHelper = new BookingDbHelper(this);

    private void loadBookings() {


        String countNumberBookings = "SELECT " + BookingTable.NewBooking.COLUMN_ID + ", " + BookingTable.NewBooking.COLUMN_DAYID + ", "
                + BookingTable.NewBooking.COLUMN_SLOT + ", " + BookingTable.NewBooking.COLUMN_NAME + " FROM " + BookingTable.NewBooking.TABLE_NAME_BOOKINGS;
        SQLiteDatabase dbr = mDbHelper.getReadableDatabase();
        Cursor cursor = dbr.rawQuery(countNumberBookings, null);

        if (cursor.getCount() == 0) {
            LinearLayout linearLayout = findViewById(R.id.booking_list);
            TextView noScoresTV = new TextView(this);
            noScoresTV.setText(getResources().getString(R.string.no_scores));
            noScoresTV.setTextSize(22);
            noScoresTV.setGravity(Gravity.CENTER_HORIZONTAL);
            linearLayout.addView(noScoresTV);

        }

        if (cursor != null && cursor.getCount() > 0)

            score_max = cursor.getCount();
        {
            int numberScores = cursor.getCount();
        //    Log.e("LOG TAG LOAD SCREEN: ", Integer.toString(numberScores));

            LinearLayout parentLinearLayout = (LinearLayout) findViewById(R.id.booking_list);

            while (cursor.moveToNext()) {
                LayoutInflater linf = LayoutInflater.from(this);

                View newScoreInflated = linf.inflate(R.layout.single_booking, parentLinearLayout, false);
                parentLinearLayout.addView(newScoreInflated);

                TextView nameTV = newScoreInflated.findViewById(R.id.name_tv);
                TextView dayTV = newScoreInflated.findViewById(R.id.day_tv);
                TextView monthTV = newScoreInflated.findViewById(R.id.month_tv);
                TextView slotTV = newScoreInflated.findViewById(R.id.slot_tv);

                int bookingID=cursor.getColumnIndex(BookingTable.NewBooking.COLUMN_ID);

                int bookingName = cursor.getColumnIndex(BookingTable.NewBooking.COLUMN_NAME);
                bookingNameStr = cursor.getString(bookingName);
                nameTV.setText(bookingNameStr);

                int bookingDayId = cursor.getColumnIndex(BookingTable.NewBooking.COLUMN_DAYID);
                int DayIdStr = Integer.parseInt(cursor.getString(bookingDayId));
           //     Log.e(LOG_TAG, "dayidstr: "+DayIdStr);
                String dayNumStr="";
                String monthStr="";
                // convertir ID a dia y mes
                if (DayIdStr<=31) {
                    dayNumStr=String.valueOf(DayIdStr);
                    monthStr=getResources().getString(R.string.month1)+" ";
                }
                if (DayIdStr<=(31+28)&&DayIdStr>31) {
                    dayNumStr=String.valueOf(DayIdStr-31);
                    monthStr=getResources().getString(R.string.month2)+" ";
                }
                if (DayIdStr<=(31+28+31)&&DayIdStr>(31+28)) {
                    dayNumStr=String.valueOf(DayIdStr-31-28);
                    monthStr=getResources().getString(R.string.month3)+" ";
                }
                if (DayIdStr<=(31+28+31+30)&&DayIdStr>(31+28+31)) {
                    dayNumStr=String.valueOf(DayIdStr-31-28-31);
                    monthStr=getResources().getString(R.string.month4)+" ";
                }
                if (DayIdStr<=(31+28+31+30+31)&&DayIdStr>(31+28+31+30)) {
                    dayNumStr=String.valueOf(DayIdStr-31-28-31-30);
                    monthStr=getResources().getString(R.string.month5)+" ";
                }
                if (DayIdStr<=(31+28+31+30+31+30)&&DayIdStr>(31+28+31+30+31)) {
                    dayNumStr=String.valueOf(DayIdStr-31-28-31-30-31);
                    monthStr=getResources().getString(R.string.month6)+" ";
                }
                if (DayIdStr<=(31+28+31+30+31+30+31)&&DayIdStr>(31+28+31+30+31+30)) {
                    dayNumStr=String.valueOf(DayIdStr-31-28-31-30-31-30);
                    monthStr=getResources().getString(R.string.month7)+" ";
                }
                if (DayIdStr<=(31+28+31+30+31+30+31+31)&&DayIdStr>(31+28+31+30+31+30+31)) {
                    dayNumStr=String.valueOf(DayIdStr-31-28-31-30-31-30-31);
                    monthStr=getResources().getString(R.string.month8)+" ";
                }
                if (DayIdStr<=(31+28+31+30+31+30+31+31+30)&&DayIdStr>(31+28+31+30+31+30+31+31)) {
                    dayNumStr=String.valueOf(DayIdStr-31-28-31-30-31-30-31-31);
                    monthStr=getResources().getString(R.string.month9)+" ";
                }
                if (DayIdStr<=(31+28+31+30+31+30+31+31+30+31)&&DayIdStr>(31+28+31+30+31+30+31+31+30)) {
                    dayNumStr=String.valueOf(DayIdStr-31-28-31-30-31-30-31-31-30);
                    monthStr=getResources().getString(R.string.month10)+" ";
                }
                if (DayIdStr<=(31+28+31+30+31+30+31+31+30+31+30)&&DayIdStr>(31+28+31+30+31+30+31+31+30+31)) {
                    dayNumStr=String.valueOf(DayIdStr-31-28-31-30-31-30-31-31-30-31);
                    monthStr=getResources().getString(R.string.month11)+" ";
                }
                if (DayIdStr<=(31+28+31+30+31+30+31+31+30+31+30+31)&&DayIdStr>(31+28+31+30+31+30+31+31+30+31+30)) {
                    dayNumStr=String.valueOf(DayIdStr-31-28-31-30-31-30-31-31-30-31-30);
                    monthStr=getResources().getString(R.string.month12)+" ";
                }
                dayTV.setText(dayNumStr);
                monthTV.setText(monthStr);

                int bookingSlot = cursor.getColumnIndex(BookingTable.NewBooking.COLUMN_SLOT);
                int slotInt = Integer.parseInt(cursor.getString(bookingSlot));
                String slotStr="";
                if (slotInt == 1) {
                    slotStr=getResources().getString(R.string.slot1);
                }
                if (slotInt == 2) {
                    slotStr=getResources().getString(R.string.slot2);
                }
                if (slotInt == 3) {
                    slotStr=getResources().getString(R.string.slot3);
                }
                if (slotInt == 4) {
                    slotStr=getResources().getString(R.string.slot4);
                }
                slotTV.setText(slotStr);

                int selected_booking_id=cursor.getPosition()+1;
                ImageView delete_btn = newScoreInflated.findViewById(R.id.delete_btn);
                delete_btn.setOnClickListener(new deleteBooking(selected_booking_id));


            }

        }
        cursor.close();

    }

    public class deleteBooking implements View.OnClickListener {

        int myLovelyVariable;

        private deleteBooking(int myLovelyVariable) {
            this.myLovelyVariable = myLovelyVariable;
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(local_bookings.this);


            builder.setMessage(getString(R.string.delete_req));
            builder.setPositiveButton(getString(R.string.del_confirm), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    BookingDbHelper mDbHelper = new BookingDbHelper(local_bookings.this);
                    SQLiteDatabase db = mDbHelper.getWritableDatabase();
                    String selection = BookingTable.NewBooking.COLUMN_ID + "=" + myLovelyVariable;
                 //   Log.e(LOG_TAG, "DELETED ID: " + selection);

                    db.delete(BookingTable.NewBooking.TABLE_NAME_BOOKINGS, selection, null);
                    resetIds(myLovelyVariable);
                    Toast.makeText(getApplicationContext(), getString(R.string.booking_deleted), Toast.LENGTH_SHORT).show();

                    Intent refreshScreen = new Intent(local_bookings.this, local_bookings.class);
                    startActivity(refreshScreen);
                }
            });

            builder.setNegativeButton(getString(R.string.del_deny), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            builder.show();


        }


    }

    public void resetIds(int deletedID) {
        if (deletedID < score_max) {
            int i = score_max - deletedID;
            int x = 1;
            // Log.e(LOG_TAG, "value deleted: " + deletedID);
            BookingDbHelper mDbHelper = new BookingDbHelper(local_bookings.this);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            while (x <= i) {
                ContentValues values = new ContentValues();
                int newID = deletedID - 1 + x;
                values.put(BookingTable.NewBooking.COLUMN_ID, newID);

                int oldID = deletedID + x;
                String selection = BookingTable.NewBooking.COLUMN_ID + "=" + oldID;
             //   Log.e(LOG_TAG, "old id " + oldID + " and new id is " + newID);
                db.update(BookingTable.NewBooking.TABLE_NAME_BOOKINGS, values, selection, null);
                x++;

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.home_button) {
            Intent backHome = new Intent(local_bookings.this, MainActivity.class);

            startActivity(backHome);
        }


        return super.onOptionsItemSelected(item);


    }
}
