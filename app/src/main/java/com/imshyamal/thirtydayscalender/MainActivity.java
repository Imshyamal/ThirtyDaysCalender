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

public class MainActivity extends AppCompatActivity {
    ArrayList<String> dates = new ArrayList<String>();
    ArrayList<String> hours = new ArrayList<String>();
    ArrayList<String> minutes = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCustomSpinner();
        initCustomTimeSpinner();



    }

    private void initCustomTimeSpinner() {

        Spinner spinnerCustom = (Spinner) findViewById(R.id.spinner2);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:a");

        SimpleDateFormat startHourFormat = new SimpleDateFormat("HH");
        cal.set(Calendar.HOUR_OF_DAY, 8);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        int startHour = Integer.parseInt(startHourFormat.format(cal.getTime()));
        Log.e("startHour",Integer.toString(startHour));

        Log.e("withot format Starttime",cal.getTime().toString());

        SimpleDateFormat endHourFormat = new SimpleDateFormat("HH");
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        int endHour = Integer.parseInt(endHourFormat.format(cal.getTime()));
        Log.e("endHour",Integer.toString(endHour));

        Log.e("withot format End time",cal.getTime().toString());

        String test = sdf.format(cal.getTime());
        Log.e("TEST", test);

        for (int i = startHour;i <= endHour; i++) {

            cal.set(Calendar.HOUR_OF_DAY, i );
            hours.add(sdf.format(cal.getTime()));
            System.out.println("ArrayList"+hours);
        }









        String result = sdf.format(Calendar.getInstance().getTime());
      //  System.out.println(result);





    }

    private void initCustomSpinner() {

        Spinner spinnerCustom= (Spinner) findViewById(R.id.spinner);
        // Spinner Drop down elements
     /*   ArrayList<String> languages = new ArrayList<String>();
        languages.add("Andorid");
        languages.add("IOS");
        languages.add("PHP");
        languages.add("Java");
        languages.add(".Net");
*/
/*

        String Tag="";
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        cal.set(Calendar.MONTH, 8);
        cal.set(Calendar.DAY_OF_MONTH, 28);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df = new SimpleDateFormat("MMM dd");
        System.out.print(df.format(cal.getTime()));
        for (int i = 1; i < maxDay; i++) {

            dates.add(df.format(cal.getTime()));
           cal.set(Calendar.DAY_OF_MONTH, i + 1);
            Log.d("",df.format(cal.getTime()));

        }
*/
    /*    Calendar calendar = Calendar.getInstance();

        SimpleDateFormat mdformat = new SimpleDateFormat("MMM dd ");
        SimpleDateFormat month1 = new SimpleDateFormat("MMM ");
        SimpleDateFormat day1 = new SimpleDateFormat("dd");
        String strDate =  mdformat.format(calendar.getTime());
        String month2=month1.format(calendar.getTime());
        String day2=day1.format(calendar.getTime());

        dates.add(strDate);
        dates.add(month2);
        dates.add(day2);*/

        Calendar cal = Calendar.getInstance();
        // int day = cal.get(Calendar.DAY_OF_MONTH);

        // cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        //  cal.set(Calendar.DAY_OF_MONTH, 1);

        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat df = new SimpleDateFormat("MMM-dd");
        int today=cal.get(Calendar.DAY_OF_MONTH);
        for (int i = today;i <= maxDay; i++) {

            dates.add( df.format(cal.getTime()));
            cal.set(Calendar.DAY_OF_MONTH, i + 1);

        }

        int sPinnerDisplayedList= (maxDay-today)+1;

        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.MONTH, cal1.get(Calendar.MONTH)+1);
        cal1.set(Calendar.DAY_OF_MONTH, 1);
        int maxDaysInNextmonth = cal1.getActualMaximum(Calendar.DAY_OF_MONTH);
        int sPinnerRemainingDays=maxDaysInNextmonth-sPinnerDisplayedList;
        for (int i =0;i <= sPinnerRemainingDays; i++) {

            System.out.print(", " + df.format(cal1.getTime()));
            dates.add( df.format(cal1.getTime()));
            cal1.set(Calendar.DAY_OF_MONTH, i + 1);


        }


        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(MainActivity.this,dates);
        spinnerCustom.setAdapter(customSpinnerAdapter);
        spinnerCustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();

                Toast.makeText(parent.getContext(), "Customized Spinner." + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

        private final Context activity;
        private ArrayList<String> asr;

        public CustomSpinnerAdapter(Context context,ArrayList<String> asr) {
            this.asr=asr;
            activity = context;
        }



        public int getCount()
        {
            return asr.size();
        }

        public Object getItem(int i)
        {
            return asr.get(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }



        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(MainActivity.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr.get(position));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(MainActivity.this);
            txt.setGravity(Gravity.CENTER);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(16);
            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
            txt.setText(asr.get(i));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

    }



}
