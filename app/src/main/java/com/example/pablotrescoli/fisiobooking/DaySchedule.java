package com.example.pablotrescoli.fisiobooking;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablotrescoli.fisiobooking.data.BookingDbHelper;
import com.example.pablotrescoli.fisiobooking.data.BookingTable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DaySchedule extends AppCompatActivity {

    EditText slot1et, slot2et, slot3et, slot4et;
    TextView slot1name, slot2name, slot3name, slot4name;
    TextView slot1tick, slot2tick, slot3tick, slot4tick;
    int day_id;
    String slot1strA, slot2strA, slot3strA, slot4strA;


    // IP de mi Url
    String IP = "http://pabspotatoe.000webhostapp.com";
    // Rutas de los Web Services
    String GET = IP + "/Obtain_days.php";
    String GET_BY_ID = IP + "/Select_day_id.php";
    String UPDATE = IP + "/Update_day.php";
    String DELETE = IP + "/Delete_day.php";
    String INSERT = IP + "/Insert_day.php";
    String day_id_str;


    ObtainWebService threadConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_layout);
        Intent intent = getIntent();
        day_id_str = intent.getStringExtra("day_id");
        day_id = Integer.parseInt(day_id_str);
        Log.e("day_id_str", day_id_str);

        loadCurrentDay(day_id_str);

        slot1name = findViewById(R.id.slot1name);
        slot2name = findViewById(R.id.slot2name);
        slot3name = findViewById(R.id.slot3name);
        slot4name = findViewById(R.id.slot4name);

        slot1et = findViewById(R.id.slot1et);
        slot2et = findViewById(R.id.slot2et);
        slot3et = findViewById(R.id.slot3et);
        slot4et = findViewById(R.id.slot4et);


        slot1tick = findViewById(R.id.slot1tick);
        slot1tick.setOnClickListener(new updateSlotX(1));

        slot2tick = findViewById(R.id.slot2tick);
        slot2tick.setOnClickListener(new updateSlotX(2));

        slot3tick = findViewById(R.id.slot3tick);
        slot3tick.setOnClickListener(new updateSlotX(3));

        slot4tick = findViewById(R.id.slot4tick);
        slot4tick.setOnClickListener(new updateSlotX(4));


    }

    BookingDbHelper mDbHelper = new BookingDbHelper(this);
    public static int score_num = 0;


    public class updateSlotX implements View.OnClickListener {
        int slotX;

        private updateSlotX(int slotX) {
            this.slotX = slotX;
        }


        @Override
        public void onClick(View v) {
            String oldnameX = slot1name.getText().toString();
            String oldnameY = slot2name.getText().toString();
            String oldnameZ = slot3name.getText().toString();
            String oldnameW = slot4name.getText().toString();


            String column = "";
            threadConnect = new ObtainWebService();

            String countNumberScores = "SELECT  * FROM " + BookingTable.NewBooking.TABLE_NAME_BOOKINGS;
            SQLiteDatabase dbr = mDbHelper.getReadableDatabase();
            Cursor cursor = dbr.rawQuery(countNumberScores, null);
            if (cursor != null && cursor.getCount() > 0) {
                score_num = cursor.getCount();
            }
            cursor.close();

            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(BookingTable.NewBooking.COLUMN_ID, score_num + 1);
            values.put(BookingTable.NewBooking.COLUMN_SLOT, slotX);
            values.put(BookingTable.NewBooking.COLUMN_DAYID, day_id);


            if (slotX == 1) {
                String newnameX = slot1et.getText().toString();
                values.put(BookingTable.NewBooking.COLUMN_NAME, newnameX);

                column = Integer.toString(slotX);
                threadConnect.execute(UPDATE, "4", day_id_str, newnameX, oldnameY, oldnameZ, oldnameW);

            } else if (slotX == 2) {
                String newnameY = slot2et.getText().toString();
                values.put(BookingTable.NewBooking.COLUMN_NAME, newnameY);

                column = Integer.toString(slotX);
                threadConnect.execute(UPDATE, "4", day_id_str, oldnameX, newnameY, oldnameZ, oldnameW);


            } else if (slotX == 3) {
                String newnameZ = slot3et.getText().toString();
                values.put(BookingTable.NewBooking.COLUMN_NAME, newnameZ);

                column = Integer.toString(slotX);
                threadConnect.execute(UPDATE, "4", day_id_str, oldnameX, oldnameY, newnameZ, oldnameW);


            } else if (slotX == 4) {
                String newnameW = slot4et.getText().toString();
                values.put(BookingTable.NewBooking.COLUMN_NAME, newnameW);

                column = Integer.toString(slotX);
                threadConnect.execute(UPDATE, "4", day_id_str, oldnameX, oldnameY, oldnameZ, newnameW);


            }

            db.insert(BookingTable.NewBooking.TABLE_NAME_BOOKINGS, null, values);


        }
    }

    public void loadCurrentDay(String day_id) {

        threadConnect = new ObtainWebService();
        String chainCall = GET_BY_ID + "?id_day=" + day_id;
        Log.e("webservicecall", chainCall);

        threadConnect.execute(chainCall, "2");

    }

    String conf = "confu";


    public class ObtainWebService extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog = new ProgressDialog(DaySchedule.this);


        @Override
        protected String doInBackground(String... params) {
            String chain = params[0];
            URL url = null;
            String result = "";


            if (params[1].equals("2")) {
                try {

                    url = new URL(chain);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //       connection.setRequestProperty("User-Agent",);
                    int response = connection.getResponseCode();
                    StringBuilder resultstr = new StringBuilder();
                    if (response == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String line;

                        while ((line = bufferedReader.readLine()) != null) {
                            resultstr.append(line);
                        }

                        JSONObject responseJSON = new JSONObject(resultstr.toString());
                        String resultJSON = responseJSON.getString("estado");

                        if (resultJSON.matches("1")) {
                            result = result + responseJSON.getJSONObject("day").getString("id_day") + "xxxx" +
                                    responseJSON.getJSONObject("day").getString("10_12") + "vvvv" +
                                    responseJSON.getJSONObject("day").getString("12_2") + "nnnn" +
                                    responseJSON.getJSONObject("day").getString("4_6") + "qqqq" +
                                    responseJSON.getJSONObject("day").getString("6_8");
                        } else if (resultJSON.matches("2")) {
                            result = "No hay dias";
                        }
                        result = "1" + result;


                    } else {
                        Toast.makeText(getApplicationContext(), "No internet connection",
                                Toast.LENGTH_LONG).show();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
              //  Log.e("Async Task result: ", result);

                return result;

            } else if (params[1].matches("4")) {
                try {
                  //  Log.e("UPDATE", "called");
                    HttpURLConnection urlConn;
                    DataOutputStream printout;
                    DataInputStream input;
                    url = new URL(chain);
                    urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(true);
                    urlConn.setUseCaches(false);
                    urlConn.setRequestProperty("Content-Type", "application/json");
                    urlConn.setRequestProperty("Accept", "application/json");
                    urlConn.connect();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id_day", params[2]);
                    jsonObject.put("10_12", params[3]);
                    jsonObject.put("12_2", params[4]);
                    jsonObject.put("4_6", params[5]);
                    jsonObject.put("6_8", params[6]);


                    OutputStream outputStream = urlConn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    bufferedWriter.write(jsonObject.toString());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                   // Log.e("UPLOAD", "SUCCESS");
                    int response = urlConn.getResponseCode();

                    StringBuilder resulting = new StringBuilder();

                    if (response == HttpURLConnection.HTTP_OK) {
                        String line;
                      //  Log.e("UPDATE RESP CONNECTION", "SUCCESS");

                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                        while ((line = bufferedReader.readLine()) != null) {
                            resulting.append(line);

                        }
                        JSONObject responseJSON = new JSONObject(resulting.toString());
                        String resultJSON = responseJSON.getString("estado");
                        if (resultJSON.matches("1")) {
                            result = "Dia actualizado correctamente";
                        } else if (resultJSON.matches("2")) {
                            result = "Unsuccesfull";
                        }
                        result = "2" + result;
                      //  Log.e("result of update", result);

                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return result;
            }
            return null;
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onPostExecute(String s) {
           // Log.e("Post Execute String: ", s);
            if (s.length() > 0) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                String type = s.substring(0, 1);
                String resulting = s.substring(1, s.length());
                Log.e("TYPE", type);
                if (type.matches("1")) {
                    loadDayAndDisplay(resulting);
                }
                if (type.matches("2")) {
                    displayToastAndUpdateTable(resulting);
                    //Log.e("type 2 so toast ", "YES");

                }
            }
            else {
                Toast.makeText(getApplicationContext(), "No connection", Toast.LENGTH_SHORT).show();
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Cargando");
            this.dialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    public void loadDayAndDisplay(String res) {
        if (res.length() > 16) {
            slot1strA = res.substring(res.indexOf("xxxx") + 4, res.indexOf("vvvv"));
            if (slot1strA.matches("null") || slot1strA.trim().length() < 1) {
                slot1name.setText(getApplicationContext().getString(R.string.nadie));
            } else {
                slot1name.setText(slot1strA);
            }
            slot2strA = res.substring(res.indexOf("vvvv") + 4, res.indexOf("nnnn"));
            if (slot2strA.matches("null") || slot2strA.trim().length() < 1) {
                slot2name.setText(getApplicationContext().getString(R.string.nadie));
            } else {
                slot2name.setText(slot2strA);
            }
            slot3strA = res.substring(res.indexOf("nnnn") + 4, res.indexOf("qqqq"));
            if (slot3strA.matches("null") || slot3strA.trim().length() < 1) {
                slot3name.setText(getApplicationContext().getString(R.string.nadie));
            } else {
                slot3name.setText(slot3strA);
            }
            slot4strA = res.substring(res.indexOf("qqqq") + 4, res.length());
            if (slot4strA.matches("null") || slot4strA.trim().length() < 1) {
                slot4name.setText(getApplicationContext().getString(R.string.nadie));
            } else {
                slot4name.setText(slot4strA);
            }
        }
    }

    public void displayToastAndUpdateTable(String res) {
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
        loadCurrentDay(day_id_str);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.home_button) {
            Intent backHome = new Intent(DaySchedule.this, MainActivity.class);

            startActivity(backHome);
        }


        return super.onOptionsItemSelected(item);


    }
}