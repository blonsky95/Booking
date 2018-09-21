package com.example.pablotrescoli.fisiobooking;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView verHorario;
    int spinner1int, spinner2int;
    Spinner spinner1, spinner2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.matriz_meses, R.layout.spinner_layout2);
        adapter1.setDropDownViewResource(R.layout.spinner_layout1);
        spinner1.setAdapter(adapter1);


        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.matriz_dias, R.layout.spinner_layout2);
        adapter2.setDropDownViewResource(R.layout.spinner_layout1);
        spinner2.setAdapter(adapter2);

        verHorario = findViewById(R.id.texto4);
        verHorario.setOnClickListener(goDaySchedule);

        final TextView localBookings = findViewById(R.id.texto5);
        localBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, local_bookings.class);
                startActivity(intent);
            }
        });

    }

    View.OnClickListener goDaySchedule = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isNetworkAvailable()) {
                spinner1int = spinner1.getSelectedItemPosition();
                spinner2int = spinner2.getSelectedItemPosition();

                Intent intent = new Intent(MainActivity.this, DaySchedule.class);
                int day_id_int = dayIdentifier(spinner1int, spinner2int);

                String day_id_str = Integer.toString(day_id_int);

                intent.putExtra("day_id", day_id_str);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "No internet connection - database inaccesible",
                        Toast.LENGTH_LONG).show();
            }
        }
    };

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public int dayIdentifier(int x, int y) {
        int z = 0;
        if (x == 0) {
            z = y + 1;
        }
        if (x == 1) {
            z = 31 + y + 1;
        }
        if (x == 2) {
            z = 31 + 28 + y + 1;
        }
        if (x == 3) {
            z = 31 + 28 + 31 + y + 1;
        }
        if (x == 4) {
            z = 31 + 28 + 31 + 30 + y + 1;
        }
        if (x == 5) {
            z = 31 + 28 + 31 + 30 + 31 + y + 1;
        }
        if (x == 6) {
            z = 31 + 28 + 31 + 30 + 31 + 30 + y + 1;
        }
        if (x == 7) {
            z = 31 + 28 + 31 + 30 + 31 + 30 + 31 + y + 1;
        }
        if (x == 8) {
            z = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + y + 1;
        }
        if (x == 9) {
            z = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + y + 1;
        }
        if (x == 10) {
            z = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + y + 1;
        }
        if (x == 11) {
            z = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + y + 1;
        }

        return z;
    }
}
