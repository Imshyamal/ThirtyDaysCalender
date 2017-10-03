package com.imshyamal.thirtydayscalender;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> dates = new ArrayList<String>();
    ArrayList<String> dates1 = new ArrayList<String>();
    ArrayList<String> hours = new ArrayList<String>();
    ArrayList<String> hours2 = new ArrayList<String>();

    ArrayList<String> minutes = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCustomSpinner();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMM-dd");
        Date dateToday = calendar.getTime();
        String newformat = df.format(dateToday);
        initCustomTimeSpinner(newformat,0);


    }

    private void initCustomTimeSpinner(String ddateString,int position) {

        Spinner timeSpinner = (Spinner) findViewById(R.id.spinner2);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMM-dd");

        Date dateToday = calendar.getTime();
        String newformat = df.format(dateToday);

        if (newformat.equals(ddateString) && position == 0) {

            SimpleDateFormat sdf = new SimpleDateFormat("kk");
            SimpleDateFormat sdf2 = new SimpleDateFormat("mm");
            int currentHour = Integer.parseInt(sdf.format(calendar.getTime()));
            int currentMin = Integer.parseInt(sdf2.format(calendar.getTime()));

            Log.e("SSS",Integer.toString(currentHour));


            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf01 = new SimpleDateFormat("hh:mm");
            SimpleDateFormat sdf02 = new SimpleDateFormat("hh:mm:a");
            SimpleDateFormat sdf03 = new SimpleDateFormat("hh:mm:a");


            cal.set(Calendar.HOUR_OF_DAY, 21);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);

            Date endHour = cal.getTime();

            cal.set(Calendar.HOUR_OF_DAY, currentHour);
            if(currentMin > 30) {
                cal.set(Calendar.MINUTE, 0);

            }else {

            }
            cal.set(Calendar.SECOND, 0);

            do {
                String interval = sdf01.format(cal.getTime()) + " - ";
                String interval1 = sdf03.format(cal.getTime());
                cal.add(Calendar.MINUTE, 30);
                interval += sdf02.format(cal.getTime());
                hours.add(interval);
                hours2.add(interval1);
            } while (cal.getTime().before(endHour));





        }else{

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:a");
            SimpleDateFormat sdf3 = new SimpleDateFormat("hh:mm:a");


            cal.set(Calendar.HOUR_OF_DAY, 21);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);

            Date endHour = cal.getTime();

            cal.set(Calendar.HOUR_OF_DAY, 8);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);

            do {
                String interval = sdf.format(cal.getTime()) + " - ";
                String interval1 = sdf3.format(cal.getTime());
                cal.add(Calendar.MINUTE, 30);
                interval += sdf2.format(cal.getTime());

                hours.add(interval);
                hours2.add(interval1);
            } while (cal.getTime().before(endHour));




        }




        System.out.println("ArrayList " + hours);


        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(MainActivity.this, hours);
        timeSpinner.setAdapter(customSpinnerAdapter);
        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();
                String item1 = hours2.get(position);

                Toast.makeText(parent.getContext(), "Customized Spinner." + item1, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    private void initCustomSpinner() {

        Spinner spinnerCustom = (Spinner) findViewById(R.id.spinner);

        Calendar cal = Calendar.getInstance();


        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df = new SimpleDateFormat("MMM-dd");
        int today = cal.get(Calendar.DAY_OF_MONTH);
        int count = 1;
        for (int i = today; i <= maxDay; i++) {

            if (count == 1) {
                dates.add("Today");
                count++;
            } else if (count == 2) {
                dates.add("Tomorrow");
                count++;
            } else {
                dates.add(df.format(cal.getTime()));

            }
            dates1.add(df.format(cal.getTime()));
            cal.set(Calendar.DAY_OF_MONTH, i + 1);


        }

        int sPinnerDisplayedList = (maxDay - today);

        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.MONTH, cal1.get(Calendar.MONTH) + 1);
        cal1.set(Calendar.DAY_OF_MONTH, 1);
        int maxDaysInNextmonth = cal1.getActualMaximum(Calendar.DAY_OF_MONTH);
        int sPinnerRemainingDays = maxDaysInNextmonth - sPinnerDisplayedList;
        for (int i = 0; i < sPinnerRemainingDays; i++) {


            cal1.set(Calendar.DAY_OF_MONTH, i + 1);
            System.out.print(", " + df.format(cal1.getTime()));
            dates.add(df.format(cal1.getTime()));
            dates1.add(df.format(cal1.getTime()));


        }


        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(MainActivity.this, dates);
        spinnerCustom.setAdapter(customSpinnerAdapter);
        spinnerCustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();
                String item1 = dates1.get(position);

                initCustomTimeSpinner(item1,position);
                Toast.makeText(parent.getContext(), "Customized Spinner." + item1, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

        private final Context activity;
        private ArrayList<String> asr;

        public CustomSpinnerAdapter(Context context, ArrayList<String> asr) {
            this.asr = asr;
            activity = context;
        }


        public int getCount() {
            return asr.size();
        }

        public Object getItem(int i) {
            return asr.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }


        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(MainActivity.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr.get(position));
            txt.setTextColor(Color.parseColor("#000000"));
            return txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(MainActivity.this);
            txt.setGravity(Gravity.CENTER);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(16);
            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
            txt.setText(asr.get(i));
            txt.setTextColor(Color.parseColor("#000000"));
            return txt;
        }

    }


}
